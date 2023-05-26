/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.bullet;

import com.simsilica.es.EntityId;

/**
 *
 * @author gary
 * @param <T>
 */
public interface PhysicsClient <T extends EntityPhysics> {
	
	/**
	 * The type of entity physics this client is listening for.
	 * @return 
	 */
	public Class<T> getPhysicsType();
	public void physicsCreated(EntityId id, T physics);
	public void physicsDestroyed(EntityId id, T physics);
	
}
