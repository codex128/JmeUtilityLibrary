/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.bullet;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Implementation of {@link EntityPhysics} for {@link RigidBodyControl}.
 * 
 * @author gary
 */
public class EntityRigidBodyControl extends RigidBodyControl implements EntityPhysics<EntityRigidBodyControl> {

	public EntityRigidBodyControl() {
		super();
	}
	public EntityRigidBodyControl(float mass) {
		super(mass);
	}
	public EntityRigidBodyControl(CollisionShape shape) {
		super(shape);
	}
	public EntityRigidBodyControl(CollisionShape shape, float mass) {
		super(shape, mass);
	}	
	
	@Override
	public EntityRigidBodyControl getPhysicsBody() {
		return this;
	}
	@Override
	public Vector3f getEntityPhysicsPosition() {
		return getPhysicsLocation();
	}
	@Override
	public Quaternion getEntityPhysicsRotation() {
		return getPhysicsRotation();
	}
	@Override
	public void setEntityPhysicsPosition(Vector3f position) {
		setPhysicsLocation(position);
	}
	@Override
	public void setEntityPhysicsRotation(Quaternion rotation) {
		setPhysicsRotation(rotation);
	}
	@Override
	public Vector3f getEntityLinearVelocity() {
		return getLinearVelocity();
	}
	@Override
	public void setEntityLinearVelocity(Vector3f vel) {
		setLinearVelocity(vel);
	}
	@Override
	public Vector3f getEntityAngularVelocity() {
		return getAngularVelocity();
	}
	@Override
	public void setEntityAngularVelocity(Vector3f vel) {
		setAngularVelocity(vel);
	}
	
}
