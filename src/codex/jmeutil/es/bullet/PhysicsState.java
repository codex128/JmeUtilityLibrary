/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.bullet;

import codex.jmeutil.es.ESAppState;
import codex.jmeutil.es.components.Physics;
import codex.jmeutil.es.factory.PhysicsFactory;
import codex.jmeutil.es.factory.PhysicsManufactureTools;
import com.jme3.app.Application;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * System for creating and managing physics bodies for entities.
 * 
 * @author gary
 */
public class PhysicsState extends ESAppState {
	
	private final HashMap<EntityId, EntityPhysics> bodies = new HashMap<>();	
	protected EntitySet phys;
	protected PhysicsFactory factory = PhysicsFactory.DEFAULT_FACTORY;
	
	public PhysicsState() {}
	
	@Override
	protected void init(Application app) {
		if (bullet == null) {
			throw new IllegalStateException("Requires BulletAppState!");
		}
		phys = ed.getEntities(Physics.class);
	}
	@Override
	protected void cleanup(Application app) {
		phys.release();
		phys = null;
		bodies.clear();
	}	
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	@Override
	public void update(float tpf) {
		if (phys.applyChanges()) {
			for (Entity e : phys.getAddedEntities()) {
				createPhysics(e);
			}
			for (Entity e : phys.getRemovedEntities()) {
				destroyPhysics(e);
			}
		}
	}
	
	private void createPhysics(Entity e) {
		Spatial spatial = visuals.getSpatial(e.getId());
		if (spatial == null) return;
		EntityPhysics ep = factory.manufactureEntityPhysics(
				new PhysicsManufactureTools(ed, e.getId(), spatial));
		bodies.put(e.getId(), ep);
		getPhysicsSpace().add(ep);
	}
	private void destroyPhysics(Entity e) {
		EntityPhysics p = bodies.remove(e.getId());
	}
	private PhysicsSpace getPhysicsSpace() {
		return bullet.getPhysicsSpace();
	}
	
	public EntityPhysics getEntityPhysics(EntityId id) {
		return bodies.get(id);
	}
	
}
