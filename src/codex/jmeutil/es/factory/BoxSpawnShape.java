/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.factory;

import codex.jmeutil.math.AxisConstraint.Axis;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import java.util.function.Function;

/**
 *
 * @author gary
 */
public class BoxSpawnShape implements SpawnShape {
	
	Vector3f size;
	
	public BoxSpawnShape(Vector3f size) {
		this.size = size;
	}
	public BoxSpawnShape(float x, float y, float z) {
		size = new Vector3f(x, y, z);
	}
	
	@Override
	public Vector3f getRandomVolumePoint() {
		return new Vector3f(random(-size.x, size.x), random(-size.y, size.y), random(-size.z, size.z));
	}
	@Override
	public Vector3f getRandomEdgePoint() {
		Vector3f ratios = new Vector3f(random(-1f, 1f), random(-1f, 1f), random(-1f, 1f));
		Axis max = getMaximumMagnitudeAxis(ratios);
		calculateAxis(ratios, max, (v) -> {
			return FastMath.sign(v);
		});
		return size.mult(ratios);
	}
	
	private static Axis getMaximumMagnitudeAxis(Vector3f vec) {
		Vector3f v = new Vector3f(FastMath.abs(vec.x), FastMath.abs(vec.y), FastMath.abs(vec.z));
		if (v.x > v.y && v.x > v.z) {
			return Axis.X;
		}
		else if (v.y > v.x && v.y > v.z) {
			return Axis.Y;
		}
		else {
			return Axis.Z;
		}
	}
	private static float random(float a, float b) {
		return a+FastMath.rand.nextFloat()*(b-a);
	}
	private static void calculateAxis(Vector3f vec, Axis axis, Function<Float, Float> calc) {
		switch (axis) {
			case X:
				vec.x = calc.apply(vec.x);
			case Y:
				vec.y = calc.apply(vec.y);
			default:
				vec.z = calc.apply(vec.z);
		}
	}
	
}
