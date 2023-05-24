/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.factory;

import com.jme3.scene.Spatial;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

/**
 *
 * @author gary
 */
public class PhysicsManufactureTools extends EntityInfo {
	
	public final Spatial spatial;
	
	public PhysicsManufactureTools(EntityData ed, EntityId entity, Spatial spatial) {
		super(ed, entity);
		this.spatial = spatial;
	}
	
	public Spatial getSpatial() {
		return spatial;
	}
	
}
