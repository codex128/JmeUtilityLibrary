/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.factory;

import codex.jmeutil.Timer;
import codex.jmeutil.TimerListener;
import codex.jmeutil.listen.Listenable;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.simsilica.es.EntityId;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author gary
 */
public class EntitySpawner extends Node implements TimerListener, Listenable<EntitySpawnListener> {
	
	public enum LocationType {
		Volume, Boundary;
	}
	
	EntityFactory factory;
	SpawnShape shape;
	LocationType type;
	Timer spawnrate = new Timer(1f);
	LinkedList<EntitySpawnListener> listeners = new LinkedList<>();
	int size = 1;
	
	public EntitySpawner(EntityFactory factory, SpawnShape shape) {
		this(factory, shape, LocationType.Volume);
	}
	public EntitySpawner(EntityFactory factory, SpawnShape shape, LocationType type) {
		setSpawnFactory(factory);
		setSpawnShape(shape);
		setSpawnLocationType(type);
		spawnrate.addListener(this);
		spawnrate.setCycleMode(Timer.CycleMode.INFINITE);
		spawnrate.start();
	}
	
	@Override
	public void updateLogicalState(float tpf) {
		super.updateLogicalState(tpf);
		spawnrate.update(tpf);
	}
	@Override
	public void onTimerFinish(Timer timer) {
		createEntities(size);
	}
	
	public EntityId[] createEntities(int n) {
		Vector3f base = getWorldTranslation();
		EntityId[] ents = new EntityId[n];
		for (int i = 0; i < n; i++) {
			EntityId id = factory.createEntity(this, base,
					(type == LocationType.Boundary ? shape.getRandomEdgePoint() : shape.getRandomVolumePoint()));
			notifyListeners(l -> l.onEntitySpawn(id));
			ents[i] = id;
		}
		return ents;
	}
	public void setSpawnFactory(EntityFactory factory) {
		if (factory == null) {
			throw new IllegalArgumentException("Factory cannot be null!");
		}
		this.factory = factory;
	}
	public void setSpawnShape(SpawnShape shape) {
		this.shape = shape;
	}
	public void setSpawnLocationType(LocationType type) {
		this.type = type;
	}
	public void setBatchSize(int size) {
		this.size = Math.abs(size);
	}
	
	public EntityFactory getSpawnFactory() {
		return factory;
	}
	public SpawnShape getSpawnShape() {
		return shape;
	}
	public LocationType getSpawnLocationType() {
		return type;
	}
	public Timer getSpawnTimer() {
		return spawnrate;
	}
	public int getBatchSize() {
		return size;
	}
	@Override
	public Collection<EntitySpawnListener> getListeners() {
		return listeners;
	}
	
}
