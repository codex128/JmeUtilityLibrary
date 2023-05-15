/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.jme3.collision.Collidable;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.simsilica.es.EntityComponent;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

/**
 *
 * @author gary
 */
public interface ColliderComponent extends EntityComponent {
	
	public abstract Collidable getCollidable(EntityData ed, EntityId id, Spatial spatial, Vector3f position);
	
}
