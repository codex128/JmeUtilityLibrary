/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.es.components.Scale;
import codex.jmeutil.es.components.Visual;
import com.jme3.app.Application;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 * System for updating spatial and entity scale.
 * 
 * @author gary
 */
public class ScaleUpdateState extends TransformUpdateState {

	@Override
	protected void init(Application app) {
		updateSpatial = ed.getEntities(
				getAutoTransformFilter(true),
				Visual.class, Scale.class);
		updateEntity = ed.getEntities(
				getAutoTransformFilter(false),
				Visual.class, Scale.class);
	}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	
	@Override
	public void updateSpatial(Spatial s, Entity e) {
		s.setLocalScale(e.get(Scale.class).getScale());
	}
	@Override
	public void updateEntity(Entity e, Spatial s) {
		e.set(new Scale(s.getLocalScale()));
	}

	
}
