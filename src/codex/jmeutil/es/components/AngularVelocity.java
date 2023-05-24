/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.components;

import codex.jmeutil.math.Velocity;
import com.jme3.math.Vector3f;

/**
 *
 * @author gary
 */
public class AngularVelocity extends AbstractUpdateComponent implements Velocity {
	
	private final Vector3f velocity;
	
	public AngularVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}
	
	@Override
	public void setVelocity(Vector3f velocity) {}
	@Override
	public Vector3f getVelocity() {
		return velocity;
	}
	@Override
	public String toString() {
		return "AngularVelocity["+velocity+"]";
	}
	
}
