/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.es.components.SpatialCullHint;
import com.jme3.app.Application;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;
import com.simsilica.es.EntitySet;

/**
 *
 * @author gary
 */
public class SpatialCullState extends ESAppState {

	EntitySet hints;
	
	@Override
	protected void init(Application app) {
		hints = ed.getEntities(SpatialCullHint.class);
	}
	@Override
	protected void cleanup(Application app) {}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	@Override
	public void update(float tpf) {
		if (hints.applyChanges()) {
			for (Entity e : hints.getAddedEntities()) {
				apply(e);
			}
			for (Entity e : hints.getChangedEntities()) {
				apply(e);
			}
		}
	}
	
	private void apply(Entity e) {
		Spatial s = visuals.getSpatial(e.getId());
		if (s == null) return;
		s.setCullHint(e.get(SpatialCullHint.class).getHint());
	}
	
}
