/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.anim;

import com.jme3.anim.AnimComposer;

/**
 * Plays actions in sync.
 * 
 * All actions must be the same length and speed.
 * 
 * @author gary
 */
public class SynchronizedAnimState extends AnimState {
	
	public SynchronizedAnimState(String name) {
		super(name);
	}
	public SynchronizedAnimState(String name, boolean enabled) {
		super(name, enabled);
	}
	
	@Override
	protected void playKey(AnimKey key, AnimComposer anim) {
		double time = 0d;
		for (AnimKey k : this) if (!k.getTargetLayer().equals(key.getTargetLayer()) && k.isPlaying()) {
			time = anim.getTime(k.getTargetLayer());
			break;
		}
		System.out.println("play synced key: "+key+"  at time "+time);
		anim.setCurrentAction(key.getAction(), key.getTargetLayer());
		anim.setTime(key.getTargetLayer(), time);
	}
	
}
