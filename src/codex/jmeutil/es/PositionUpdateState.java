/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.es.components.Position;
import codex.jmeutil.es.components.Visual;
import com.jme3.app.Application;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 * System for updating spatial and entity positions.
 * 
 * @author gary
 */
public class PositionUpdateState extends TransformUpdateState {
	
	@Override
	protected void init(Application app) {
		updateSpatial = ed.getEntities(
				getAutoTransformFilter(true),
				Visual.class, Position.class);
		updateEntity = ed.getEntities(
				getAutoTransformFilter(false),
				Visual.class, Position.class);
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
	public void updateEntity(Entity e, Spatial s) {
		e.set(new Position(s.getLocalTranslation()));
	}
	
}
