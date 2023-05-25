/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.ESAppState;
import codex.jmeutil.es.bullet.EntityPhysics;
import com.jme3.app.Application;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;
import com.simsilica.es.EntitySet;
import codex.jmeutil.es.components.UpdatableComponent;
import com.simsilica.es.EntityData;
import java.util.LinkedList;

/**
 * System for updating entity, spatial, and physics properties.
 * 
 * @author gary
 * @param <T>
 */
public class ComponentUpdateState extends ESAppState {
	
	LinkedList<UpdateSuite> update = new LinkedList<>();
	ComponentUpdater[] preBucket;
	
	public ComponentUpdateState() {}
	public ComponentUpdateState(ComponentUpdater... bucket) {
		preBucket = bucket;
	}
	
	@Override
	protected void init(Application app) {
		if (preBucket != null) {
			addAll(preBucket);
			preBucket = null;
		}
	}
	@Override
	protected void cleanup(Application app) {
		for (UpdateSuite s : update) {
			s.set.release();
			s.set = null;
		}
		update.clear();
	}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	@Override
	public void update(float tpf) {
		for (UpdateSuite suite : update) {
			update(suite);
		}
	}	
	private void update(UpdateSuite suite) {
		suite.set.applyChanges();
		for (Entity e : suite.set) {
			Spatial spatial = visuals.getSpatial(e.getId());
			EntityPhysics p = getEntityPhysics(e.getId(), null);
			UpdatableComponent c = e.get(suite.updater.getComponentType());
			if ((p == null || !suite.updater.updatePhysics(p, e))
					&& (spatial == null || !suite.updater.updateSpatial(spatial, e))) {
				suite.updater.updateEntity(e, spatial, p);
			}
			c.getUpdateDirection().increment();
			ed.getComponent(e.getId(), suite.updater.getComponentType()).inheritUpdateDirection(c);
		}
	}
	
	public void add(ComponentUpdater updater) {
		update.addLast(new UpdateSuite(ed, updater));
	}
	public void addAll(ComponentUpdater... updaters) {
		for (ComponentUpdater u : updaters) {
			add(u);
		}
	}
	
	private UpdateSuite getSuite(Class<? extends UpdatableComponent> type) {
		for (UpdateSuite s : update) {
			if (s.updater.getComponentType().equals(type)) {
				return s;
			}
		}
		return null;
	}
	public ComponentUpdater getUpdater(Class<? extends UpdatableComponent> type) {
		UpdateSuite suite = getSuite(type);
		if (suite != null) return suite.updater;
		else return null;
	}
	public EntitySet getSet(Class<? extends UpdatableComponent> type) {
		UpdateSuite suite = getSuite(type);
		if (suite != null) return suite.set;
		else return null;
	}
	
	private static class UpdateSuite {
		
		EntitySet set;
		ComponentUpdater updater;
		
		private UpdateSuite(EntityData ed, ComponentUpdater updater) {
			set = ed.getEntities(updater.getComponentType());
			this.updater = updater;
		}
		
	}
	
}
