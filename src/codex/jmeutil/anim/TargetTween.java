/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.anim;

import com.jme3.anim.tween.AbstractTween;

/**
 *
 * @author gary
 * @param <T>
 */
public abstract class TargetTween <T> extends AbstractTween {
	
	private T target;
	
	public TargetTween(T target) {
		super(0);
		this.target = target;
	}
	
	@Override
	protected void doInterpolate(double t) {
		invoke(target);
	}	
	protected abstract void invoke(T target);
	
}
