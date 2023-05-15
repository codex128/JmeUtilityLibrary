/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.factory;

import codex.jmeutil.es.factory.EntitySpawner;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityId;

/**
 *
 * @author gary
 */
public interface EntityFactory {
	
	public abstract EntityId createEntity(EntitySpawner spawner, Vector3f baselocation, Vector3f offset);
	
}
