/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.listen.Listenable;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.simsilica.es.EntityData;
import com.simsilica.es.base.DefaultEntityData;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Handles EntityData.
 * 
 * If used, should be attached to the state manager before all other states.
 * 
 * @author gary
 */
public class EntityState extends BaseAppState {
	
	EntityData ed;
	
	public EntityState() {
		this(new DefaultEntityData());
	}
	public EntityState(EntityData ed) {
		this.ed = ed;
	}
	
	@Override
	protected void initialize(Application app) {}
	@Override
	protected void cleanup(Application app) {}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	
	public EntityData getEntityData() {
		return ed;
	}
	
}
