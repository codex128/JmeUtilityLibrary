/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

/**
 * Component representing the entity's scale.
 * 
 * @author gary
 */
public class Scale implements EntityComponent {
	
	private final Vector3f scale = new Vector3f(1f, 1f, 1f);
	
	public Scale() {}
	public Scale(Vector3f scale) {
		this.scale.set(scale);
	}
	public Scale(float scalar) {
		this.scale.set(scalar, scalar, scalar);
	}
	
	public Vector3f getScale() {
		return scale;
	}
	@Override
	public String toString() {
		return "Scale["+scale+"]";
	}
	
}
