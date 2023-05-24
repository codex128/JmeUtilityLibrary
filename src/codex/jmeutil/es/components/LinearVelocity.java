/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import codex.jmeutil.math.Velocity;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

/**
 *
 * @author gary
 */
public class LinearVelocity extends AbstractUpdateComponent implements Velocity {
	
	private final Vector3f velocity;
	
	public LinearVelocity() {
		this(new Vector3f());
	}
	public LinearVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}
	
	@Override
	public Vector3f getVelocity() {
		return velocity;
	}
	@Override
	public void setVelocity(Vector3f velocity) {}
	@Override
	public String toString() {
		return "LinearVelocity["+velocity+"]";
	}
	
}
