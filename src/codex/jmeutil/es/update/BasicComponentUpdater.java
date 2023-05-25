/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.components.Position;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 *
 * @author gary
 */
public abstract class BasicComponentUpdater implements ComponentUpdater {

	@Override
	public boolean updatePhysics(EntityPhysics p, Entity e) {
		if (p == null || !isEntityToWorld(e)) {
			return false;
		}
		setPhysicsFromEntity(p, e);
		return true;
	}
	@Override
	public boolean updateSpatial(Spatial s, Entity e) {
		if (s == null || !isEntityToWorld(e)) {
			return false;
		}
		setSpatialFromEntity(s, e);
		return true;
	}
	@Override
	public void updateEntity(Entity e, Spatial s, EntityPhysics p) {
		if (p != null) {
			setEntityFromPhysics(e, p);
		}
		else if (s != null) {
			setEntityFromSpatial(e, s);
		}
	}
	
	protected abstract void setPhysicsFromEntity(EntityPhysics p, Entity e);
	protected abstract void setSpatialFromEntity(Spatial s, Entity e);
	protected abstract void setEntityFromPhysics(Entity e, EntityPhysics p);
	protected abstract void setEntityFromSpatial(Entity e, Spatial s);
	
}
