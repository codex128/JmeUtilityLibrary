/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.components.Scale;
import codex.jmeutil.es.components.UpdatableComponent;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 * System for updating spatial and entity scale.
 * 
 * @author gary
 */
public class ScaleUpdater implements ComponentUpdater {

	@Override
	public Class<? extends UpdatableComponent> getComponentType() {
		return Scale.class;
	}
	@Override
	public boolean updatePhysics(EntityPhysics p, Entity e) {
		if (p == null || !isEntityToWorld(e)) {
			return false;
		}
		p.setEntityPhysicsScale(e.get(Scale.class).getScale());
		return true;
	}
	@Override
	public boolean updateSpatial(Spatial s, Entity e) {
		if (s == null || !isEntityToWorld(e)) {
			return false;
		}
		s.setLocalScale(e.get(Scale.class).getScale());
		return true;
	}
	@Override
	public void updateEntity(Entity e, Spatial s, EntityPhysics p) {
		if (p != null) {
			Vector3f ps = p.getEntityPhysicsScale();
			if (ps != null) {
				e.set(new Scale(ps));
				return;
			}
		}
		if (s != null) {
			e.set(new Scale(s.getLocalScale()));
		}
	}
		
}
