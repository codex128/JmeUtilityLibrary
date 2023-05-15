/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.es.components.Visual;
import com.jme3.app.Application;
import com.jme3.scene.Spatial;
import com.simsilica.es.ComponentFilter;
import com.simsilica.es.Entity;
import com.simsilica.es.EntitySet;
import com.simsilica.es.Filters;

/**
 * System for updating spatial and entity transforms.
 * 
 * @author gary
 */
public abstract class TransformUpdateState extends ESAppState {
	
	protected EntitySet updateSpatial, updateEntity;
	
	@Override
	protected void cleanup(Application app) {
		if (updateSpatial == null) return;
		updateSpatial.release();
		updateSpatial = null;
	}
	@Override
	public void update(float tpf) {
		if (updateSpatial == null) return;
		Spatial s;
		if (updateSpatial.applyChanges()) for (Entity e : updateSpatial) {
			s = visuals.getSpatial(e.getId());
			if (s == null) continue;
			updateSpatial(s, e);
		}
		if (updateEntity.applyChanges()) {
			for (Entity e : updateEntity.getAddedEntities()) {
				s = visuals.getSpatial(e.getId());
				if (s == null) continue;
				updateSpatial(s, e);
			}
		}
		for (Entity e : updateEntity) {
			s = visuals.getSpatial(e.getId());
			if (s == null) continue;
			updateEntity(e, s);
		}
	}
	
	/**
	 * Updates spatial transform based on entity transform.
	 * Only entities that have auto transform enabled will be cycled through this method.
	 * @param s spatial
	 * @param e entity
	 */
	public abstract void updateSpatial(Spatial s, Entity e);
	/**
	 * Updates entity transform based on spatial transform.
	 * Only entities that have auto transform disabled will be cycled through this method.
	 * @param e entity
	 * @param s spatial
	 */
	public abstract void updateEntity(Entity e, Spatial s);
	
	protected ComponentFilter<Visual> getAutoTransformFilter(boolean autoTransform) {
		return Filters.fieldEquals(Visual.class, "autoTransform", autoTransform);
	}
	
}
