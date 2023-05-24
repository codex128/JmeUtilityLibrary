/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.anim;

import codex.jmeutil.control.SubControl;
import com.jme3.anim.AnimComposer;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * Keeps a stack of animation states and runs the top ones with <code>AnimComposer</code>.
 * 
 * <code>AnimState</code>s are stored in an <code>AnimStack</code>. Higher ones (higher index) are considered a priority
 * to lower states, and will always be used instead of lower states if they are enabled.
 * 
 * @author gary
 */
public class AnimStackControl extends SubControl<AnimComposer> {
	
	HashMap<String, AnimStack> layers = new HashMap<>();
	HashMap<String, AnimKey> current = new HashMap<>();
	LinkedList<AnimState> states = new LinkedList<>();
	
	public AnimStackControl() {
		super(AnimComposer.class);
	}
	
	public AnimKey add(AnimKey key) {
		fetchStack(key.getTargetLayer()).addLast(key);
		refreshCurrentActions();
		return key;
	}
	public AnimKey add(AnimKey key, int index) {
		fetchStack(key.getTargetLayer()).add(index, key);
		refreshCurrentActions();
		return key;
	}
	public void addAll(AnimKey... keys) {
		for (AnimKey k : keys) {
			fetchStack(k.getTargetLayer()).addLast(k);
		}
		refreshCurrentActions();
	}
	
	public AnimState add(AnimState state) {
		return add(state, true);
	}
	public AnimState add(AnimState state, boolean addKeys) {
		states.add(state);
		if (addKeys) for (AnimKey key : state) {
			fetchStack(key.getTargetLayer()).addLast(key);
		}
		refreshCurrentActions();
		return state;
	}
	public void addAll(AnimState... states) {
		addAll(true, states);
	}
	public void addAll(boolean addKeys, AnimState... states) {
		for (AnimState s : states) {
			add(s, addKeys);
		}
	}
	
	public void clearStacks() {
		layers.clear();
	}
	public void clearStates() {
		states.clear();
	}
	
	public AnimState getState(String statename) {
		return states.stream().filter(s -> s.getName().equals(statename)).findFirst().orElse(null);
	}
	public AnimStack getStack(String layer) {
		return layers.get(layer);
	}
	/**
	 * Get a key to the layer that performs the action.
	 * Uses AnimStates to locate the key. If the key's state is not added
	 * to this AnimStackControl, then the key is unlocatable.
	 * @param layer
	 * @param action
	 * @return a key matching the criteria
	 */
	public AnimKey getKey(String layer, String action) {
		for (AnimState s : states) {
			return s.stream().filter(key -> key.getTargetLayer().equals(layer) && key.getAction().equals(action)).findFirst().orElse(null);
		}
		return null;
	}
	
	public void enableState(String statename, boolean enable) {
		getState(statename).enable(enable);
		refreshCurrentActions();
	}	
	protected AnimStack fetchStack(String layer) {
		AnimStack stack = layers.get(layer);
		if (stack == null) {
			stack = new AnimStack(layer);
			layers.put(stack.getTargetLayer(), stack);
		}
		return stack;
	}
	
	/**
	 * Refreshes the current actions used.
	 * Does not interrupt animations that should be continued.
	 */
	public void refreshCurrentActions() {
		if (!hasDependency()) return;
		Set<String> names = dependency.getLayerNames();
		main: for (String layer : names) {
			AnimStack stack = getStack(layer);
			if (stack == null) continue;
			for (AnimKey key : stack) {
				if (!key.isEnabled()) {
					continue;
				}
				AnimKey prev = current.get(layer);
				if (prev != key) {
					if (prev != null) prev.setPlaying(false);
					key.setPlaying(true);
					key.getState().playKey(key, dependency);
					current.put(layer, key);
				}
				continue main;
			}
			System.out.println("stop action on layer "+layer);
			AnimKey prev = current.remove(layer);
			if (prev != null) prev.setPlaying(false);
			dependency.removeCurrentAction(layer);
		}
	}
	/**
	 * Resets all current actions.
	 * All animations already playing will be interrupted.
	 */
	public void resetCurrentActions() {
		current.clear();
		refreshCurrentActions();
	}
	
	public HashMap<String, AnimKey> getCurrentKeys() {
		return current;
	}
	public LinkedList<AnimState> getStates() {
		return states;
	}
	
	@Override
	protected void subControlUpdate(float tpf) {}
	@Override
	protected void subControlRender(RenderManager rm, ViewPort vp) {}
	@Override
	protected void onDependencyCaptured() {
		refreshCurrentActions();
	}
	@Override
	protected void onDependencyReleased() {}
	
}
