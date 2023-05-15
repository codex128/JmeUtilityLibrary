/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.math;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

/**
 * Constrains axis values of vectors.
 * 
 * @author gary
 */
public class AxisConstraint {
	
	public enum Axis {
		X, Y, Z;
	}
	
	public static final AxisConstraint
			ALL  = new AxisConstraint(),
			NONE = new AxisConstraint(false, false, false),
			 X = new AxisConstraint(true, false, false),
			 Y = new AxisConstraint(false, true, false),
			 Z = new AxisConstraint(false, false, true),
			NX = X.negate(),
			NY = Y.negate(),
			NZ = Z.negate();
	
	public boolean
			x = true,
			y = true,
			z = true;
	
	public AxisConstraint() {}
	public AxisConstraint(AxisConstraint ac) {
		set(ac);
	}
	public AxisConstraint(boolean x, boolean y, boolean z) {
		set(x, y, z);
	}
	
	public AxisConstraint set(AxisConstraint ac) {
		return set(ac.x, ac.y, ac.z);
	}
	public AxisConstraint set(boolean x, boolean y, boolean z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	/**
	 * Returns an AxisConstraint with flipped constraints.
	 * @return 
	 */
	public AxisConstraint negate() {
		return new AxisConstraint(!x, !y, !z);
	}
	/**
	 * Flips all constraints of this constraint.
	 * @return 
	 */
	public AxisConstraint negateLocal() {
		return set(!x, !y, !z);
	}
	/**
	 * Crosses constraints.
	 * For example, if either x or the given x is true, the returned constraint's x is true.
	 * @param ac
	 * @return 
	 */
	public AxisConstraint cross(AxisConstraint ac) {
		return new AxisConstraint(x||ac.x, y||ac.y, z||ac.z);
	}
	public AxisConstraint crossLocal(AxisConstraint ac) {
		return set(x||ac.x, y||ac.y, z||ac.z);
	}
	
	public Vector3f apply(Vector3f vec, float def) {
		return new Vector3f(
				(x ? vec.x : def),
				(y ? vec.y : def),
				(z ? vec.z : def));
	}
	public Vector2f apply(Vector2f vec, float def) {
		return new Vector2f(
				(x ? vec.x : def),
				(y ? vec.y : def));
	}
	public Vector3f apply(Vector3f vec, float def, Vector3f store) {
		store.x = (x ? vec.x : def);
		store.y = (y ? vec.y : def);
		store.z = (z ? vec.z : def);
		return store;
	}
	public Vector2f apply(Vector2f vec, float def, Vector2f store) {
		store.x = (x ? vec.x : def);
		store.y = (y ? vec.y : def);
		return store;
	}
	public Vector3f apply(Vector3f vec, Vector3f def) {
		return new Vector3f(
				(x ? vec.x : def.x),
				(y ? vec.y : def.y),
				(z ? vec.z : def.z));
	}
	public Vector3f apply(Vector3f vec, Vector3f def, Vector3f store) {
		store.x = (x ? vec.x : def.x);
		store.y = (y ? vec.y : def.y);
		store.z = (z ? vec.z : def.z);
		return store;
	}
	public Vector2f apply(Vector2f vec, Vector2f def) {
		return new Vector2f(
				(x ? vec.x : def.x),
				(y ? vec.y : def.y));
	}
	public Vector2f apply(Vector2f vec, Vector2f def, Vector2f store) {
		store.x = (x ? vec.x : def.x);
		store.y = (y ? vec.y : def.y);
		return store;
	}
	
	public boolean getX() {
		return x;
	}
	public boolean getY() {
		return y;
	}
	public boolean getZ() {
		return z;
	}	
	@Override
	public String toString() {
		return "Axis("+x+", "+y+", "+z+")";
	}
	
	public static AxisConstraint getForAxis(Axis axis) {
		switch (axis) {
			case X: return X;
			case Y: return Y;
			case Z: return Z;
			default:
				return null;
		}
	}
	
}
