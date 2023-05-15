/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.es.components.Rotation;
import codex.jmeutil.es.components.Visual;
import com.jme3.app.Application;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 * System for updating spatial and entity rotation.
 * 
 * @author gary
 */
public class RotationUpdateState extends TransformUpdateState {

	@Override
	protected void init(Application app) {
		updateSpatial = ed.getEntities(
				getAutoTransformFilter(true),
				Visual.class, Rotation.class);
		updateEntity = ed.getEntities(
				getAutoTransformFilter(false),
				Visual.class, Rotation.class);
	}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	
	@Override
	public void updateSpatial(Spatial s, Entity e) {
		s.setLocalRotation(e.get(Rotation.class).getRotation());
	}
	@Override
	public void updateEntity(Entity e, Spatial s) {
		e.set(new Rotation(s.getLocalRotation()));
	}
	
}
