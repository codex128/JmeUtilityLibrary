/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.anim;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Contains a group of <code>AnimState</code>s dedicated to the same kind of action.
 * 
 * @author gary
 */
public class AnimGroup {
	
	LinkedList<AnimState> states = new LinkedList<>();
	String name;
	boolean enabled = true;
	
	public AnimGroup(String name) {
		setName(name);
	}
	public AnimGroup(String name, boolean enabled) {
		setName(name);
		enable(enabled);
	}
	
	public void add(AnimState state) {
		states.add(state);
		state.setAffiliatedGroup(this);
	}
	public void addAll(AnimState... states) {
		this.states.addAll(Arrays.asList(states));
	}
	public void remove(AnimState state) {
		if (states.remove(state)) {
			state.setAffiliatedGroup(null);
		}
	}
	public void clear() {
		for (AnimState state : states) {
			state.setAffiliatedGroup(null);
		}
		states.clear();
	}
	public AnimState get(String name) {
		for (AnimState state : states) {
			if (state.getName().equals(name)) {
				return state;
			}
		}
		return null;
	}
	
	public void enable(String name, boolean enable) {
		AnimState state = get(name);
		if (state != null) state.enable(enable);
	}
	public void enableAll(boolean enable) {
		for (AnimState state : states) {
			state.enable(enable);
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void enable(boolean enable) {
		enabled = enable;
	}
	
	public String getName() {
		return name;
	}
	public boolean isEnabled() {
		return enabled;
	}
	
}
