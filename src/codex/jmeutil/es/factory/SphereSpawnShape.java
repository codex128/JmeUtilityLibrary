/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.factory;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;

/**
 *
 * @author gary
 */
public class SphereSpawnShape implements SpawnShape {

	float radius;
	
	public SphereSpawnShape(float radius) {
		setRadius(radius);
	}
	
	@Override
	public Vector3f getRandomVolumePoint() {
		float length = FastMath.rand.nextFloat()*radius;
		return getRandomEdgePoint().multLocal(length);
	}
	@Override
	public Vector3f getRandomEdgePoint() {
		float y = FastMath.rand.nextFloat()*FastMath.TWO_PI;
		float xz = FastMath.rand.nextFloat()*FastMath.TWO_PI;
		float cos = FastMath.cos(y)*radius;
		return new Vector3f(FastMath.cos(xz)*cos, FastMath.sin(y)*radius, FastMath.sin(xz)*cos);
	}
	
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = FastMath.abs(radius);
	}
	
}
