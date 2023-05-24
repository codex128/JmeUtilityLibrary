/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.bullet;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 * Implementation of {@link EntityPhysics} for {@link BetterCharacterControl}.
 * 
 * @author gary
 */
public class EntityCharacterControl extends BetterCharacterControl implements EntityPhysics<PhysicsRigidBody> {
		
	@Override
	public PhysicsRigidBody getPhysicsBody() {
		return getRigidBody();
	}	
	@Override
	public Vector3f getEntityPhysicsPosition() {
		return getRigidBody().getPhysicsLocation();
	}
	@Override
	public Quaternion getEntityPhysicsRotation() {
		return getRigidBody().getPhysicsRotation();
	}
	@Override
	public void setEntityPhysicsPosition(Vector3f position) {
		warp(position);
	}
	@Override
	public void setEntityPhysicsRotation(Quaternion rotation) {}
	@Override
	public Vector3f getEntityLinearVelocity() {
		return getRigidBody().getLinearVelocity();
	}
	@Override
	public void setEntityLinearVelocity(Vector3f vel) {
		getRigidBody().setLinearVelocity(vel);
	}
	@Override
	public Vector3f getEntityAngularVelocity() {
		return getRigidBody().getAngularVelocity();
	}
	@Override
	public void setEntityAngularVelocity(Vector3f vel) {}
	
}
