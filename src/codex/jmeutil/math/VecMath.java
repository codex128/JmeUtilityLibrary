/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.math;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author gary
 */
public class VecMath {
	
	/**
	 * Reflects the given direction on a plane defined by the given normal.
	 * @param direction
	 * @param normal
	 * @return the reflected direction
	 */
	public static Vector3f reflect(Vector3f direction, Vector3f normal) {
		direction.normalizeLocal();
		normal.normalizeLocal();
		return direction.subtract(normal.mult(2*direction.dot(normal)));
	}
	
	/**
	 * Get a vector pointing to the left of vec.
	 * @param vec
	 * @param up
	 * @return left-pointing vector
	 */
	public static Vector3f getLeft(Vector3f vec, Vector3f up) {
		return new Quaternion().lookAt(vec, up).getRotationColumn(0);
	}
	
}
