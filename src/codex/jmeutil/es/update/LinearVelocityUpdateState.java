/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.components.LinearVelocity;
import com.jme3.math.Transform;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 *
 * @author gary
 */
public class LinearVelocityUpdateState extends ComponentUpdateState {

	public LinearVelocityUpdateState() {
		super(LinearVelocity.class);
	}
	
	@Override
	protected void updateSpatial(Spatial s, Entity e) {}
	@Override
	protected void updateEntity(Entity e, Spatial s, EntityPhysics p) {}
	@Override
	protected void updatePhysics(EntityPhysics p, Entity e) {}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	
}
