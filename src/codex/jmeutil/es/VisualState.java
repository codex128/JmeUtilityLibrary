/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.es.factory.ModelFactory;
import codex.jmeutil.es.components.Visual;
import codex.jmeutil.es.factory.ModelTools;
import codex.jmeutil.es.factory.SimpleModelFactory;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Box;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;
import java.util.HashMap;
import java.util.Set;

/**
 * Handles spatials for entities.
 * 
 * @author gary
 */
public class VisualState extends BaseAppState {
	
	private static final ModelFactory
			DEF_MODEL_FAC = new SimpleModelFactory();
	public static final String
			ROOTNODE = "rootNode",
			GUINODE = "guiNode",
			SPATIAL_ID = "spatial_id",
			DEFAULT_MODEL_FACTORY = "default_model_factory";
	
	EntityData ed;
	EntitySet visuals;
	HashMap<String, ModelFactory> factories = new HashMap<>();
	HashMap<EntityId, Spatial> spatials = new HashMap<>();
	HashMap<EntityId, SceneGraphVisitor> visitors = new HashMap<>();
	HashMap<String, Node> scenes = new HashMap<>();
	
	public VisualState() {}
	
	@Override
	protected void initialize(Application app) {
		ed = getState(EntityState.class).getEntityData();
		visuals = ed.getEntities(Visual.class);
		registerModelFactory(DEFAULT_MODEL_FACTORY, DEF_MODEL_FAC);
		registerScene(ROOTNODE, ((SimpleApplication)app).getRootNode(), null);
		registerScene(GUINODE,  ((SimpleApplication)app).getGuiNode() , null);
	}
	@Override
	protected void cleanup(Application app) {
		visuals.release();
		visuals = null;
		factories.clear();
		spatials.clear();
		scenes.clear();
	}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	@Override
	public void update(float tpf) {
		if (visuals.applyChanges()) {
			addVisualEntities(visuals.getAddedEntities());
			removeVisualEntities(visuals.getRemovedEntities());
		}
	}
	
	private AssetManager getAssetManager() {
		return getApplication().getAssetManager();
	}
	
	private void addVisualEntities(Set<Entity> entities) {
		for (Entity e : entities) {
			createEntityVisual(e);
		}
	}
	private Spatial createEntityVisual(Entity e) {
		Spatial spatial = spatials.get(e.getId());
		Visual v = e.get(Visual.class);
		if (spatial == null) {
			// create spatial
			if (v.getModel() != null) {
				ModelFactory factory = factories.get(v.getFactory());
				if (factory == null) {
					factory = factories.get(DEFAULT_MODEL_FACTORY);
				}
				spatial = factory.createModel(new ModelTools(ed, getApplication().getAssetManager(), e.getId()));
			}
			if (spatial == null) {
				spatial = createSimpleCube(getAssetManager(), 1f);
			}
			link(e.getId(), spatial);
		}
		// attach spatial to scene graph
		if (spatial.getParent() == null) {
			if (v.getParent() != null) {
				scenes.get(v.getParent()).attachChild(spatial);
			}
			else {
				scenes.get(ROOTNODE).attachChild(spatial);
			}
		}
		// traverse the spatial
		SceneGraphVisitor visitor = visitors.remove(e.getId());
		if (visitor != null) {
			spatial.depthFirstTraversal(visitor);
		}
		// store entity id in spatial data
		spatial.setUserData(SPATIAL_ID, e.getId().getId());
		return spatial;
	}
	private void removeVisualEntities(Set<Entity> entities) {
		for (Entity e : entities) {
			removeVisualEntity(e);
		}
	}
	private void removeVisualEntity(Entity e) {
		Spatial s = spatials.remove(e.getId());
		if (s != null) s.removeFromParent();
	}
	
	/**
	 * Links the id and the spatial together.
	 * When and if the id is processed in this VisualState, then that entity will
	 * control that spatial. This is useful is the model already exists, but you
	 * want to attach it to an entity.
	 * <p>
	 * <strong>Warning:</strong> this method works even if the entity does not have a
	 * <code>Visual</code> component.
	 * @param id
	 * @param spatial
	 * @return spatial previously associated with the id, or null if none was previously associated.
	 */
	public Spatial link(EntityId id, Spatial spatial) {
		if (spatial == null) return null;
		return spatials.put(id, spatial);
	}
	/**
	 * Add a scene graph visitor.
	 * When the spatial for the id is processed, the scene graph will be traversed
	 * using the visitor. The visitor will be removed after the scene is traversed.
	 * @param id
	 * @param visitor
	 * @return visitor previously associated with the id, or null if none was previously associated.
	 */
	public SceneGraphVisitor addScanner(EntityId id, SceneGraphVisitor visitor) {
		return visitors.put(id, visitor);
	}
	
	/**
	 * Register a model factory.
	 * @param key
	 * @param factory 
	 */
	public void registerModelFactory(String key, ModelFactory factory) {
		if (factory == null) {
			throw new IllegalArgumentException("Factory cannot be null!");
		}
		factories.put(key, factory);
	}
	/**
	 * Remove the model factory stored at the key.
	 * @param key
	 * @return 
	 */
	public ModelFactory removeModelFactory(String key) {
		return factories.remove(key);
	}
	/**
	 * Get the model factory stored at the key.
	 * @param key
	 * @return 
	 */
	public ModelFactory getModelFactory(String key) {
		return factories.get(key);
	}
	
	/**
	 * Get the entity's spatial.
	 * @param id id representing the entity
	 * @return
	 */
	public Spatial getSpatial(EntityId id) {
		return spatials.get(id);
	}
	/**
	 * Get a control of type T attached to the entity's spatial.
	 * @param <T> control type
	 * @param id id representing the entity
	 * @param type class of the control type
	 * @return control of type T, if exists
	 */
	public <T extends Control> T getControl(EntityId id, Class<T> type) {
		return getSpatial(id).getControl(type);
	}
	
	/**
	 * Get the scene represented by the asserted scene string.
	 * @param scene
	 * @return scene node
	 */
	public Node getScene(String scene) {
		return scenes.get(scene);
	}
	/**
	 * Register a scene node under a name.
	 * Attaches the scene node to the scene registered under parent, if parent is not null.
	 * @param scenename registry name
	 * @param scene 
	 * @param parent 
	 */
	public void registerScene(String scenename, Node scene, String parent) {
		scenes.put((scenename != null ? scenename : scene.getName()), scene);
		if (parent != null) {
			scenes.get(parent).attachChild(scene);
		}
	}
	/**
	 * Remove the scene stored at the scene string.
	 * Detaches the scene node from its parent node (if possible).
	 * @param scene 
	 */
	public void removeScene(String scene) {
		Node n = scenes.remove(scene);
		if (n != null) {
			n.removeFromParent();
		}
	}
	
	/**
	 * Fetches the entity id associated with this spatial.
	 * All spatials generated by a VisualState get the appropriate entity id
	 * attached in the user data of the spatial.
	 * <p>
	 * The depth value determines how far the algorithm should search up the scene graph
	 * for the entity id.<br>For example, ray picking only produces the collision geometry. To
	 * fetch the correct id, the algorithm must travel up the scene graph to the correct spatial.
	 * <br>The depth value limits how far this process is able to go.
	 * 
	 * @param spatial the spatial to fetch from
	 * @param depth the depth at which the algorithm is able to search. Negative values produce no limit.
	 * @return 
	 */
	public static EntityId getSpatialId(Spatial spatial, int depth) {
		while (spatial != null && depth != 0) {
			Long id = spatial.getUserData(SPATIAL_ID);
			if (id != null) {
				return new EntityId(id);
			}
			spatial = spatial.getParent();
			depth--;
		}
		return null;
	}	
	/**
	 * Cast a ray and get the closest collision.
	 * Collisions can be limited to a set distance.
	 * @param origin origin of the ray
	 * @param direction direction of the ray
	 * @param limit the maximum distance at which the closest collision is returned
	 * @param detect spatial to collide with
	 * @return CollisionResult representing the closest collision (null if no collision, or closest collision is too far away)
	 */
	public static CollisionResult castRay(Vector3f origin, Vector3f direction, float limit, Spatial detect) {
		Ray ray = new Ray(origin, direction.normalizeLocal());
		//if (limit > 0f) ray.setLimit(limit);
		CollisionResults results = new CollisionResults();
		detect.collideWith(ray, results);
		if (results.size() > 0) {
			CollisionResult closest = results.getClosestCollision();
			if (limit < 0f || closest.getContactPoint().distanceSquared(origin) <= limit*limit) {
				return closest;
			}
		}
		return null;
	}
	
	/**
	 * Create a simple cube geometry of a given size.
	 * Uses <code>createSimpleMaterial(...)</code> to create an unshaded material
	 * with a random color.
	 * @param assets
	 * @param size diameter of cube
	 * @return cube geometry
	 */
	public static Geometry createSimpleCube(AssetManager assets, float size) {
		Box mesh = new Box(size/2, size/2, size/2);
		mesh.scaleTextureCoordinates(new Vector2f(size, size));
		Geometry g = new Geometry("cube", mesh);
		g.setMaterial(VisualState.createSimpleMaterial(assets));
		return g;
	}
	/**
	 * Create an unshaded material with a random color.
	 * @param assets
	 * @return 
	 */
	public static Material createSimpleMaterial(AssetManager assets) {
		return createSimpleMaterial(assets, ColorRGBA.randomColor());
	}
	/**
	 * Create an unshaded material with the given color.
	 * @param assets
	 * @param color
	 * @return 
	 */
	public static Material createSimpleMaterial(AssetManager assets, ColorRGBA color) {
		Material m = new Material(assets, "Common/MatDefs/Misc/Unshaded.j3md");
		m.setColor("Color", ColorRGBA.randomColor());
		return m;
	}
	
}
