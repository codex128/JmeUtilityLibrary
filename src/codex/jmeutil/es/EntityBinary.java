/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.es.components.EntityBinaryComponent;
import codex.jmeutil.logic.Binary;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

/**
 * Represents an entity for <code>Logic</code>.
 * 
 * @author gary
 * @param <T> type of component which determines the logical state
 */
public class EntityBinary <T extends EntityBinaryComponent> implements Binary {
	
	protected EntityData ed;
	protected EntityId id;
	protected Class<T> component;
	
	/**
	 * @param ed
	 * @param id
	 * @param component component which determines this instance's logical state
	 */
	public EntityBinary(EntityData ed, EntityId id, Class<T> component) {
		this.ed = ed;
		this.id = id;
		this.component = component;
	}
	
	@Override
	public boolean getBinaryState() {
		T c = ed.getComponent(id, component);
		return (c != null ? c.getBinaryState() : false);
	}
	
	public EntityId getEntityId() {
		return id;
	}
	public Class<T> getComponentType() {
		return component;
	}
	
}
