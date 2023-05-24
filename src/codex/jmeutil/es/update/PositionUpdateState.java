/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.bullet.PhysicsTransform;
import codex.jmeutil.es.components.Position;
import com.jme3.math.Transform;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 * System for updating spatial and entity positions.
 * 
 * @author gary
 */
public class PositionUpdateState extends ComponentUpdateState {
	
	public PositionUpdateState() {
		super(Position.class);
	}
	
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	
	@Override
	public void updateSpatial(Spatial s, Entity e) {
		s.setLocalTranslation(e.get(Position.class).getPosition());
	}
	@Override
	public void updateEntity(Entity e, Spatial s, EntityPhysics p) {
		e.set(new Position(s.getLocalTranslation()));
	}
	@Override
	public void updatePhysics(EntityPhysics p, Entity e) {
		p.setEntityPhysicsPosition(e.get(Position.class).getPosition());
	}
	
}
