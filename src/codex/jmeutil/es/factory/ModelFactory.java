/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.factory;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import com.simsilica.es.EntityComponent;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

/**
 * Interface for constructing spatials for entities.
 * 
 * @author gary
 */
public interface ModelFactory {
	
	/**
	 * The default factory used by VisualState.
	 */
	public static final SimpleModelFactory
			DEFAULT_FACTORY = new SimpleModelFactory();
	
	/**
	 * Create a model.Returns null if this model factory cannot construct a model for the entity.
	 * @param tools tools needed to create models
	 * @return model, or null if unable
	 */
	public abstract Spatial createModel(ModelManufactureTools tools);
	
	/**
	 * Get a component under the given id.
	 * Used to get components containing data important for model construction.
	 * @param <T> type of component
	 * @param tools
	 * @param defaultvalue default component if no component is found under the id
	 * @return argument component, or the given default component if none is found
	 */
	public static <T extends EntityComponent> T getArgumentComponent(ModelManufactureTools tools, T defaultvalue) {
		return getArgumentComponent(tools.ed, tools.entity, defaultvalue);
	}
	/**
	 * Get a component under the given id.
	 * Used to get components containing data important for model construction.
	 * @param <T> type of component
	 * @param ed 
	 * @param entity 
	 * @param defaultvalue default component if no component is found under the id
	 * @return argument component, or the given default component if none is found
	 */
	public static <T extends EntityComponent> T getArgumentComponent(EntityData ed, EntityId entity, T defaultvalue) {
		T component = ed.getComponent(entity, (Class<T>)defaultvalue.getClass());
		if (component != null) return component;
		else return defaultvalue;
	}
	
}
