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
import java.util.Set;

/**
 * Keeps a stack of animation states and runs the top ones with <code>AnimComposer</code>.
 * 
 * <code>AnimState</code>s are stored in an <code>AnimStack</code>. Higher ones (higher index) are considered a priority
 * to lower states, and will always be used instead of lower states if they are enabled.
 * 
 * @author gary
 */
public class AnimationStackControl extends SubControl<AnimComposer> {
	
	AnimStack stack = new AnimStack();
	HashMap<String, CurrentAnim> current = new HashMap<>();
	HashMap<String, AnimGroup> groups = new HashMap<>();
	
	public AnimationStackControl() {
		super(AnimComposer.class);
	}
	
	public AnimState set(AnimState state, int index) {
		stack.set(index, state);
		refreshCurrentActions();
		return state;
	}
	public AnimState add(AnimState state) {
		stack.add(state);
		refreshCurrentActions();
		return state;
	}
	public AnimState add(AnimState state, int index) {
		stack.add(index, state);
		refreshCurrentActions();
		return state;
	}
	public void clearAnimStack() {
		stack.clear();
		refreshCurrentActions();
	}
	
	public AnimGroup add(AnimGroup group) {
		groups.put(group.getName(), group);
		return group;
	}
	public AnimGroup remove(String name) {
		return groups.remove(name);
	}
	public void clearGroups() {
		groups.clear();
	}
	
	public AnimState get(int index) {
		return stack.get(index);
	}
	public AnimState get(String statename) {
		for (AnimState state : stack) {
			if (state.getName().equals(statename)) {
				return state;
			}
		}
		return null;
	}
	public AnimGroup getGroup(String groupname) {
		return groups.get(groupname);
	}
	public void enableState(String statename, boolean enable) {
		AnimState state = get(statename);
		if (state != null && state.isEnabled() != enable) {
			System.out.println("enable state ("+state.getName()+") = "+enable);
			state.enable(enable);
			refreshCurrentActions();
		}
	}
	public void enableGroup(String groupname, boolean enable) {
		AnimGroup group = groups.get(groupname);
		if (group != null && group.isEnabled() != enable) {
			group.enable(enable);
			refreshCurrentActions();
		}
	}
	
	/**
	 * Refreshes the current actions used.
	 * Does not interrupt animations that should be continued.
	 */
	public void refreshCurrentActions() {
		if (!hasDependency()) return;
		Set<String> layers = dependency.getLayerNames();
		for (String layer : layers) {
			for (AnimState state : stack) {
				if (!state.isEnabled()) {
					continue;
				}
				AnimKey key = state.getKeyForLayer(layer);
				if (key != null) {
					CurrentAnim c = current.get(layer);
					if (c == null || !c.key.getAction().equals(key.getAction())) {
						if (!state.contains(key) || !key.play()) {
							continue;
						}
						state.playKey(key, dependency);
						for (AnimState s : stack) {
							if (s != state) {
								s.stopKeyInLayer(layer);
							}
						}
						current.put(layer, new CurrentAnim(state, key));
					}
					break;
				}
			}
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
	
	public HashMap<String, AnimGroup> getGroups() {
		return groups;
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
	
	
	private static class CurrentAnim {
		AnimState state;
		AnimKey key;
		CurrentAnim(AnimState state, AnimKey key) {
			this.state = state;
			this.key = key;
		}
	}
	
}
