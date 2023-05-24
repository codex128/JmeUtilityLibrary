/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.anim;

import com.jme3.anim.AnimComposer;
import java.util.HashSet;

/**
 * Data set for matching animation layer names to action names.
 * 
 * @author gary
 */
public class AnimState extends HashSet<AnimKey> {
	
	String name;
	boolean enabled = false;
	
	public AnimState(String name) {
		setName(name);
	}
	public AnimState(String name, boolean enabled) {
		setName(name);
		enable(enabled);
	}
	
	public AnimKey add(String targetlayer, String action) {
		AnimKey key = createAnimKey(targetlayer, action);
		add(key);
		return key;
	}
	public String getActionForLayer(String layer) {
		for (AnimKey key : this) {
			if (key.getTargetLayer().equals(layer)) {
				return key.action;
			}
		}
		return null;
	}
	public AnimKey getKeyForLayer(String layer) {
		for (AnimKey key : this) {
			if (key.getTargetLayer().equals(layer)) {
				return key;
			}
		}
		return null;
	}
	protected AnimKey createAnimKey(String targetlayer, String action) {
		return new AnimKey(this, targetlayer, action);
	}
	
	protected void playKey(AnimKey key, AnimComposer anim) {
		anim.setCurrentAction(key.getAction(), key.getTargetLayer(), true);
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
	public String[] getActions() {
		String[] actions = new String[size()];
		if (!isEmpty()) {
			int index = 0;
			for (AnimKey key : this) {
				actions[index++] = key.action;
			}
		}
		return actions;
	}
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+"[size="+size()+"]";
	}
	
}
