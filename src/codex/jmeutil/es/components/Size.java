/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

/**
 * Component representing the entity's size.
 * 
 * Used primarily as an argument for model factories.
 * 
 * @author gary
 */
public class Size implements EntityComponent {
	
	private final Vector3f size = new Vector3f();
	
	public Size() {}
	public Size(float s) {
		size.set(s, s, s);
	}
	public Size(Vector3f size) {
		this.size.set(size);
	}
	public Size(float w, float h, float d) {
		size.set(w, h, d);
	}
	
	public Vector3f getSize() {
		return size;
	}
	@Override
	public String toString() {
		return "Size["+size+"]";
	}
	
}
