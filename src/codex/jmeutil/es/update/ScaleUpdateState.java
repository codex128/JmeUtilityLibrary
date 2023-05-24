/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.components.Scale;
import com.jme3.math.Transform;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 * System for updating spatial and entity scale.
 * 
 * @author gary
 */
public class ScaleUpdateState extends ComponentUpdateState {

	public ScaleUpdateState() {
		super(Scale.class);
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
	public void updateEntity(Entity e, Spatial s, EntityPhysics p) {
		e.set(new Scale(s.getLocalScale()));
	}
	@Override
	public void updatePhysics(EntityPhysics p, Entity e) {
		p.setEntityPhysicsScale(e.get(Scale.class).getScale());
	}

	
}
