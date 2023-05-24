/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.ESAppState;
import codex.jmeutil.es.bullet.EntityPhysics;
import com.jme3.app.Application;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;
import com.simsilica.es.EntitySet;
import com.jme3.math.Transform;
import codex.jmeutil.es.components.UpdatableComponent;

/**
 * System for updating entity, spatial, and physics properties.
 * 
 * @author gary
 * @param <T>
 */
public abstract class ComponentUpdateState extends ESAppState {
	
	protected EntitySet update;
	private final Class<? extends UpdatableComponent> type;
	
	protected ComponentUpdateState(Class<? extends UpdatableComponent> type) {
		this.type = type;
	}
	
	@Override
	protected void init(Application app) {
		update = ed.getEntities(type);
	}
	@Override
	protected void cleanup(Application app) {
		if (update == null) return;
		update.release();
		update = null;
	}
	@Override
	public void update(float tpf) {
		if (update == null) return;
		int direction;
		for (Entity e : update) {
			Spatial spatial = visuals.getSpatial(e.getId());
			EntityPhysics ep = physics.getEntityPhysics(e.getId());
			direction = e.get(type).getUpdateDirection();
			if (direction > UpdatableComponent.MIDPOINT) {
				if (ep != null) updatePhysics(ep, e);
				else if (spatial != null) updateSpatial(spatial, e);
			}
			else {
				updateEntity(e, spatial, ep);
			}
			if (!UpdatableComponent.isStable(direction)) {
				ed.getComponent(e.getId(), type).setUpdateDirection(direction, true);
			}
		}
	}
	
	/**
	 * Updates spatial transform based on entity transform.
	 * @param s spatial
	 * @param e entity
	 */
	protected abstract void updateSpatial(Spatial s, Entity e);
	/**
	 * Updates entity transform based on spatial transform.
	 * @param e entity
	 * @param s spatial
	 * @param p entity physics
	 */
	protected abstract void updateEntity(Entity e, Spatial s, EntityPhysics p);
	/**
	 * Updates physics transform based on entity transform.
	 * @param p entity physics
	 * @param e entity
	 */
	protected abstract void updatePhysics(EntityPhysics p, Entity e);
	
}
