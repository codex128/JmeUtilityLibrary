/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.components.LinearVelocity;
import codex.jmeutil.es.components.UpdatableComponent;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 *
 * @author gary
 */
public class LinearVelocityUpdater implements ComponentUpdater {

	@Override
	public Class<? extends UpdatableComponent> getComponentType() {
		return LinearVelocity.class;
	}
	@Override
	public boolean updatePhysics(EntityPhysics p, Entity e) {
		if (p == null || !isEntityToWorld(e)) {
			return false;
		}
		p.setEntityLinearVelocity(e.get(LinearVelocity.class).getVelocity());
		return true;
	}
	@Override
	public boolean updateSpatial(Spatial s, Entity e) {
		return false;
	}
	@Override
	public void updateEntity(Entity e, Spatial s, EntityPhysics p) {
		if (p == null) return;
		e.set(new LinearVelocity(p.getEntityLinearVelocity()));
	}
	
}
