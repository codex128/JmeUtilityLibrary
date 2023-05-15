/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.collision;

import codex.jmeutil.es.ESAppState;
import codex.jmeutil.es.components.BoxCollider;
import codex.jmeutil.es.components.ColliderComponent;
import codex.jmeutil.es.components.MeshCollider;
import codex.jmeutil.es.components.Position;
import codex.jmeutil.es.factory.ModelFactory;
import com.jme3.app.Application;
import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResults;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author gary
 */
public class CollisionDetectState extends ESAppState {
	
	CollisionSet[] colliders;
	HashMap<EntityId, ProbeGroup> probes = new HashMap<>();
	
	@Override
	protected void init(Application app) {
		initializeCollisionSets();
	}
	@Override
	protected void cleanup(Application app) {}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	@Override
	public void update(float tpf) {}
	
	protected void initializeCollisionSets() {
		colliders = new CollisionSet[2];
		colliders[0] = new CollisionSet(ed, MeshCollider.class);
		colliders[1] = new CollisionSet(ed, BoxCollider.class);
	}
	
	public CollisionResults collide(Collidable collider, EntityId... exceptions) {
		CollisionResults res = new CollisionResults();
		for (CollisionSet set : colliders) {
			collide(collider, set, res, exceptions);
		}
		return res;
	}
	protected void collide(Collidable collider, CollisionSet<? extends ColliderComponent> set, CollisionResults res, EntityId[] exceptions) {
		set.set.applyChanges();
		main: for (Entity e : set.set) {
			for (EntityId ex : exceptions) {
				if (ex.equals(e.getId())) {
					continue main;
				}
			}
			Collidable c = e.get(set.type).getCollidable(ed, e.getId(), visuals.getSpatial(e.getId()),
					ModelFactory.getArgumentComponent(ed, e.getId(), Position.ZERO).getPosition());
			c.collideWith(collider, res);
		}
	}
	
	protected ProbeGroup getProbeGroup(EntityId entity) {
		return probes.get(entity);
	}
	protected ProbeData getProbeData(String name, EntityId entity) {
		ProbeGroup group = getProbeGroup(entity);
		if (group == null) {
			return null;
		}
		return group.get(name);
	}
	public CollisionResults getProbeCollision(String name, EntityId entity) {
		ProbeData data = getProbeData(name, entity);
		if (data != null) return data.collision;
		else return null;
	}
	public CollisionResults probe(String name, EntityId entity, Collidable collider, EntityId... exceptions) {
		CollisionResults res = getProbeCollision(name, entity);
		if (res != null) {
			return res;
		}
		res = collide(collider, exceptions);
		ProbeGroup group = getProbeGroup(entity);
		if (group == null) {
			group = new ProbeGroup();
		}
		group.addIfAbsent(new ProbeData(name, res));
		return res;
	}
	public CollisionResults probe(boolean replace, String name, EntityId entity, Collidable collider, EntityId... exceptions) {
		if (!replace) return probe(name, entity, collider, exceptions);
		CollisionResults res = collide(collider, exceptions);
		ProbeGroup group = getProbeGroup(entity);
		if (group == null) {
			group = new ProbeGroup();
		}
		group.add(new ProbeData(name, res));
		return res;
	}
	
	protected static class CollisionSet <T extends ColliderComponent> {		
		EntitySet set;
		Class<T> type;
		private CollisionSet(EntityData ed, Class<T> type) {
			set = ed.getEntities(type);
			this.type = type;
		}
	}
	protected static class ProbeGroup extends LinkedList<ProbeData> {
		public ProbeData get(String name) {
			for (ProbeData data : this) {
				if (data.name.equals(name)) {
					return data;
				}
			}
			return null;
		}
		public CollisionResults getCollision(String name) {
			ProbeData data = get(name);
			if (data != null) return data.collision;
			else return null;
		}
		public boolean addIfAbsent(ProbeData data) {
			if (get(data.name) != null) {
				return false;
			}
			add(data);
			return true;
		}
	}
	protected static class ProbeData {
		String name;
		CollisionResults collision;
		protected ProbeData(String name, CollisionResults collision) {
			this.name = name;
			this.collision = collision;
		}
	}
	
}
