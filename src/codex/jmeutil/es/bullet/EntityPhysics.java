/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.bullet;

import com.jme3.bullet.objects.PhysicsBody;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Interface for porting physics information to entity components and vise versa.All physics bodies attached to an entity should implement this.
 *  
 * 
 * @author gary
 * @param <T>
 */
public interface EntityPhysics <T extends PhysicsBody> {
	
	/**
	 * Get the physics body this is associated with.
	 * @return 
	 */
	public T getPhysicsBody();
	
	/**
	 * Returns the position of the entity physics body in space.
	 * @return 
	 */
	public Vector3f getEntityPhysicsPosition();
	/**
	 * Sets the position of the entity physics body.
	 * @param position
	 */
	public void setEntityPhysicsPosition(Vector3f position);
	
	/**
	 * Returns the rotation of the entity physics body in space.
	 * @return 
	 */
	public Quaternion getEntityPhysicsRotation();	
	/**
	 * Sets the rotation of the entity physics body.
	 * @param rotation
	 */
	public void setEntityPhysicsRotation(Quaternion rotation);
	
	public Vector3f getEntityLinearVelocity();
	public void setEntityLinearVelocity(Vector3f vel);
	
	public Vector3f getEntityAngularVelocity();
	public void setEntityAngularVelocity(Vector3f vel);
	
	/**
	 * Returns the scale of the entity physics body.
	 * This method returns null by default, but should be overriden if
	 * the body deals with scaling.
	 * @return 
	 */
	public default Vector3f getEntityPhysicsScale() {
		return null;
	}
	/**
	 * Sets the scale of the entity physics body.
	 * This method does not set the scale by default, but should be overriden
	 * if the body deals with scaling.
	 * @param scale 
	 */
	public default void setEntityPhysicsScale(Vector3f scale) {}
	
}
