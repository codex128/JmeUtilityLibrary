/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.components.AngularVelocity;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 *
 * @author gary
 */
public class AngularVelocityUpdateState extends ComponentUpdateState {

	public AngularVelocityUpdateState() {
		super(AngularVelocity.class);
	}
	
	@Override
	protected void updateSpatial(Spatial s, Entity e) {}
	@Override
	protected void updateEntity(Entity e, Spatial s, EntityPhysics p) {
		e.set(new AngularVelocity(p.getEntityAngularVelocity()));
	}
	@Override
	protected void updatePhysics(EntityPhysics p, Entity e) {
		p.setEntityAngularVelocity(e.get(AngularVelocity.class).getVelocity());
	}

	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	
}
