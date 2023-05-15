/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.math;

import com.jme3.math.Vector3f;

/**
 *
 * @author gary
 */
public class Threshold3f {
	
	public Float x, y, z;
	public int dx, dy, dz;
	
	public Threshold3f() {}
	public Threshold3f(Threshold3f threshold) {
		set(threshold);
	}
	public Threshold3f(Float x, int dx, Float y, int dy, Float z, int dz) {
		set(x, dx, y, dy, z, dz);
	}
	
	public Threshold3f set(Threshold3f threshold) {
		return set(threshold.x, threshold.dx, threshold.y, threshold.dy, threshold.z, threshold.dz);
	}
	public Threshold3f set(Float x, int dx, Float y, int dy, Float z, int dz) {
		this.x = x;
		this.dx = dx;
		this.y = y;
		this.dy = dy;
		this.z = z;
		this.dz = dz;
		return this;
	}
	
	public boolean isBeyond(Vector3f vec) {
		return (isX() || isY() || isZ()) &&
				(!isX() || ((vec.x >= x && dx > 0) || (vec.x <= x && dx < 0) || (vec.x == x && dx == 0))) &&
				(!isY() || ((vec.y >= y && dy > 0) || (vec.y <= y && dy < 0) || (vec.y == y && dy == 0))) &&
				(!isZ() || ((vec.z >= z && dz > 0) || (vec.z <= z && dz < 0) || (vec.z == z && dz == 0)));
	}
	
	public boolean isX() {
		return x != null;
	}
	public boolean isY() {
		return y != null;
	}
	public boolean isZ() {
		return z != null;
	}
	public Float getX() {
		return x;
	}
	public Float getY() {
		return y;
	}
	public Float getZ() {
		return z;
	}
	public int getDirectionX() {
		return dx;
	}
	public int getDirectionY() {
		return dy;
	}
	public int getDirectionZ() {
		return dz;
	}
	
	public Vector3f toVector3f() {
		return toVector3f(0f);
	}
	public Vector3f toVector3f(float defaultvalue) {
		return new Vector3f((isX()?x:defaultvalue), (isY()?y:defaultvalue), (isZ()?z:defaultvalue));
	}
	
	@Override
	public String toString() {
		return "("+x+", "+y+", "+z+")";
	}
	
}
