/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.es.bullet.PhysicsState;
import codex.jmeutil.es.bullet.EntityPhysics;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector2f;
import com.jme3.scene.Node;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.input.InputMapper;

/**
 * Provides more streamlined methods for getting application and entity information.
 * 
 * It is suggested that all states in a Zay-ES project should extend this instead of BaseAppState.
 * 
 * @author gary
 */
public abstract class ESAppState extends BaseAppState {
	
	protected Application app;
	protected EntityData ed;
	protected EntityState entityState;
	protected VisualState visuals;
	protected BulletAppState bullet;
	protected PhysicsState physics;
	protected AssetManager assetManager;
	protected Vector2f windowSize;
	
	@Override
	protected void initialize(Application app) {
		this.app = app;
		entityState = getState(EntityState.class, true);
		ed = entityState.getEntityData();
		visuals = getState(VisualState.class, true);
		bullet = getState(BulletAppState.class);
		physics = getState(PhysicsState.class);
		assetManager = this.app.getAssetManager();
		windowSize = new Vector2f(
				app.getContext().getSettings().getWidth(),
				app.getContext().getSettings().getHeight());
		init(app);
	}
	protected abstract void init(Application app);
	
	/**
	 * Fetches the first app state of the given type.
	 * Use <code>getState(state, failOnMiss)</code> instead.
	 * @throws IllegalStateException if no app state is found
	 * @param <T>
	 * @param type
	 * @return first app state of the given type
	 */
	@Deprecated
	public <T extends AppState> T requireState(Class<T> type) {
		T state = getState(type);
		if (state == null) {
			throw new IllegalStateException(getClass().getName()+" requires "+type.getName()+"!");
		}
		return state;
	}
	public EntityPhysics getEntityPhysics(EntityId id, EntityPhysics defaultvalue) {
		if (physics == null) return defaultvalue;
		EntityPhysics p = physics.getEntityPhysics(id);
		if (p != null) return p;
		return defaultvalue;
	}
	
	public EntityData getEntityData() {
		return ed;
	}
	public EntityState getEntityState() {
		return entityState;
	}
	public VisualState getVisualState() {
		return visuals;
	}
	public BulletAppState getBulletState() {
		return bullet;
	}
	public PhysicsState getPhysicsState() {
		return physics;
	}
	public AssetManager getAssetManager() {
		return assetManager;
	}
	public InputMapper getInputMapper() {
		return GuiGlobals.getInstance().getInputMapper();
	}
	public Vector2f getWindowSize() {
		return windowSize;
	}
	public Node getRootNode() {
		return ((SimpleApplication)getApplication()).getRootNode();
	}
	public Node getGuiNode() {
		return ((SimpleApplication)getApplication()).getGuiNode();
	}
	
	public boolean hasBulletPhysics() {
		return bullet != null;
	}
	public boolean hasESPhysics() {
		return physics != null;
	}
	
}
