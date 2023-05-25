/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.update;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.components.UpdatableComponent;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;

/**
 *
 * @author gary
 */
public interface ComponentUpdater {
	
	/**
	 * Returns the type of component this updater handles.
	 * @return 
	 */
	public Class<? extends UpdatableComponent> getComponentType();
	
	public boolean updatePhysics(EntityPhysics p, Entity e);
	public boolean updateSpatial(Spatial s, Entity e);
	public void updateEntity(Entity e, Spatial s, EntityPhysics p);
	
	public default boolean updateEntityToWorld(Entity e, Spatial s, EntityPhysics p) {
		return e.get(getComponentType()).getUpdateDirection().isEntityToWorld();
	}	
	public default boolean isEntityToWorld(Entity e) {
		return e.get(getComponentType()).getUpdateDirection().isEntityToWorld();
	}	
	
}
