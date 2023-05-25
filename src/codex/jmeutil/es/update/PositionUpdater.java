/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.components.Position;
import codex.jmeutil.es.components.UpdatableComponent;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 * System for updating spatial and entity positions.
 * 
 * @author gary
 */
public class PositionUpdater extends BasicComponentUpdater {

	@Override
	public Class<? extends UpdatableComponent> getComponentType() {
		return Position.class;
	}
	@Override
	protected void setPhysicsFromEntity(EntityPhysics p, Entity e) {
		p.setEntityPhysicsPosition(e.get(Position.class).getPosition());
	}
	@Override
	protected void setSpatialFromEntity(Spatial s, Entity e) {
		s.setLocalTranslation(e.get(Position.class).getPosition());
	}
	@Override
	protected void setEntityFromPhysics(Entity e, EntityPhysics p) {
		e.set(new Position(p.getEntityPhysicsPosition()));
	}
	@Override
	protected void setEntityFromSpatial(Entity e, Spatial s) {
		e.set(new Position(s.getLocalTranslation()));
	}
	
}
