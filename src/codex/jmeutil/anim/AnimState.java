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
	boolean enabled = true;
	AnimGroup group;
	
	public AnimState(String name) {
		setName(name);
	}
	public AnimState(String name, boolean enabled) {
		setName(name);
		enable(enabled);
	}
	
	public boolean add(String targetlayer, String action) {
		return add(createAnimKey(targetlayer, action));
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
		return new AnimKey(targetlayer, action);
	}
	
	protected void playKey(AnimKey key, AnimComposer anim) {
		System.out.println(this+"  "+key);
		anim.setCurrentAction(key.getAction(), key.getTargetLayer());
	}
	protected void stopKeyInLayer(String layer) {
		AnimKey key = getKeyForLayer(layer);
		if (key != null) key.stop();
	}
	
	protected void setAffiliatedGroup(AnimGroup group) {
		this.group = group;
	}
	public void joinGroup(AnimGroup group) {
		group.add(this);
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
		return enabled && (group == null || group.isEnabled());
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+"[size="+size()+"]";
	}
	
}
