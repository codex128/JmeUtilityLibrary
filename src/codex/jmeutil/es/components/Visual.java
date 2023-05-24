/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import codex.jmeutil.es.VisualState;
import com.simsilica.es.EntityComponent;

/**
 * Component representing an entity's visuals (spatials).
 * 
 * @author gary
 */
public class Visual implements EntityComponent {
	
	private static String default_factory = VisualState.DEFAULT_MODEL_FACTORY;
	
	private String model;
	private String parent;
	private String factory = default_factory;
	private boolean autoTransform = true;
	
	public Visual() {}
	public Visual(String model) {
		this.model = model;
	}
	public Visual(String model, String parent) {
		this(model);
		this.parent = parent;
	}
	public Visual(String model, String parent, String factory) {
		this.model = model;
		this.parent = parent;
		this.factory = factory;
		if (this.factory == null) {
			this.factory = default_factory;
		}
	}
	/**
	 * Copies all fields of the given Visual component except auto transform.
	 * Used for changing auto transform.
	 * @param visual
	 * @param autoTransform 
	 */
	public Visual(Visual visual, boolean autoTransform) {
		model = visual.model;
		parent = visual.parent;
		factory = visual.factory;
		this.autoTransform = autoTransform;
	}
	
	// for initialization only
	/**
	 * Enable auto transform.
	 * If auto transform is enabled, the spatial's transform will be updated to match this entity's transform.
	 * If auto transform is disabled, this entity's transform will be updated to match the spatial's transform.
	 * <br>default=true
	 * <p>
	 * In general auto transform should be enabled, but there are cases where it should be disabled.
	 * For example, if a physics control is updating the spatial transform.
	 * <p>
	 * Only use this method during initialization. Changes to auto transform may
	 * not be detected by the transform update systems.
	 * 
	 * @param auto enable/disable auto transform
	 * @return this Visual instance
	 */
	@Deprecated
	public Visual enableAutoTransform(boolean auto) {
		autoTransform = auto;
		return this;
	}
	
	/**
	 * String representing which model this entity should use.
	 * @return 
	 */
	public String getModel() {
		return model;
	}
	/**
	 * String representing which scene this entity's spatial should be parented to.
	 * If null, the spatial will not be parented.
	 * @return 
	 */
	public String getParent() {
		return parent;
	}
	/**
	 * String representing which model factory this entity should use which generating the spatial.
	 * The default is <code>Visual.getDefaultFactory()</code> (which is initialized to <code>VisualState.DEFAULT_MODEL_FACTORY</code>.
	 * To change the default, use <code>Visual.setDefaultFactory()</code>.
	 * @return 
	 */
	public String getFactory() {
		return factory;
	}
	/**
	 * Auto transform is enabled for this entity.
	 * @return 
	 */
	@Deprecated
	public boolean autoTransformEnabled() {
		return autoTransform;
	}	
	@Override
	public String toString() {
		return "Visual["+model+", "+parent+", "+autoTransform+"]";
	}
	
	/**
	 * Set the default model factory all entities use.
	 * @param defaultfactory 
	 */
	public static void setDefaultFactory(String defaultfactory) {
		default_factory = defaultfactory;
	}
	/**
	 * Get the default model factory all entities use.
	 * @return 
	 */
	public static String getDefaultFactory() {
		return default_factory;
	}
	
}
