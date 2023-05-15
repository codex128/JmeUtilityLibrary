/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

/**
 * Component representing the entity's rotation.
 * 
 * @author gary
 */
public class Rotation implements EntityComponent {
	
	private final Quaternion rotation = new Quaternion();
	
	public Rotation() {}
	public Rotation(Quaternion rotation) {
		this.rotation.set(rotation);
	}
	public Rotation(Vector3f lookat, Vector3f up) {
		rotation.lookAt(lookat, up);
	}
	public Rotation(Vector3f axis, float angle) {
		rotation.fromAngleAxis(angle, axis);
	}
	public Rotation(float x, float y, float z) {
		rotation.fromAngles(x, y, z);
	}
	
	public Quaternion getRotation() {
		return rotation;
	}
	@Override
	public String toString() {
		return "Rotation["+rotation+"]";
	}
	
}
