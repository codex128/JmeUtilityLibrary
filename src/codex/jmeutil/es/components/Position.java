/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

/**
 * Component representing the entity's position.
 * 
 * @author gary
 */
public class Position implements EntityComponent {
	
	public static final Position ZERO = new Position();
	
	private final Vector3f position = new Vector3f();
	
	public Position() {}
	public Position(Vector3f position) {
		this.position.set(position);
	}
	public Position(float x, float y, float z) {
		this.position.set(x, y, z);
	}
	
	public Vector3f getPosition() {
		return position;
	}
	@Override
	public String toString() {
		return "Position["+position+"]";
	}
	
}
