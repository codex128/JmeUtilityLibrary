/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.components.Rotation;
import com.jme3.bullet.objects.PhysicsBody;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.math.Transform;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 * System for updating spatial and entity rotation.
 * 
 * @author gary
 */
public class RotationUpdateState extends ComponentUpdateState {
	
	public RotationUpdateState() {
		super(Rotation.class);
	}
	
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	
	@Override
	public void updateSpatial(Spatial s, Entity e) {
		s.setLocalRotation(e.get(Rotation.class).getRotation());
	}
	@Override
	public void updateEntity(Entity e, Spatial s, EntityPhysics p) {
		e.set(new Rotation(s.getLocalRotation()));
	}
	@Override
	public void updatePhysics(EntityPhysics p, Entity e) {
		p.setEntityPhysicsRotation(e.get(Rotation.class).getRotation());
	}
	
}
