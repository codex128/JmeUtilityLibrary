/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.factory;

import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

/**
 *
 * @author gary
 */
public class EntityInfo {
	
	public final EntityData ed;
	public final EntityId entity;
	
	public EntityInfo(EntityData ed, EntityId entity) {
		this.ed = ed;
		this.entity = entity;
	}
	
	public EntityData getEntityData() {
		return ed;
	}
	public EntityId getEntity() {
		return entity;
	}
	
}
