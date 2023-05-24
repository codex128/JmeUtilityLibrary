/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import codex.jmeutil.math.Positionable;
import com.jme3.math.Vector3f;

/**
 * Component representing the entity's position.
 * 
 * @author gary
 */
public class Position extends AbstractUpdateComponent implements Positionable {
	
	public static final Position ZERO = new Position();
	
	private final Vector3f position = new Vector3f();
	
	public Position() {}
	public Position(Vector3f position) {
		this.position.set(position);
	}
	public Position(float x, float y, float z) {
		this.position.set(x, y, z);
	}
	
	@Override
	public Vector3f getPosition() {
		return position;
	}	
	@Override
	public void setPosition(Vector3f position) {}
	
	@Override
	public String toString() {
		return "Position["+position+"]";
	}
	
}
