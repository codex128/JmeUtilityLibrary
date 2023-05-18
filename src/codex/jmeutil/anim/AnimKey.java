/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.anim;

/**
 *
 * @author gary
 */
public class AnimKey {
	
	protected final String targetlayer;
	protected final String action;
	protected final AnimState state;
	private boolean playing = false;
	private boolean enabled = true;
	
	public AnimKey(AnimState state, String targetlayer, String action) {
		this.state = state;
		this.state.add(this);
		this.targetlayer = targetlayer;
		this.action = action;
	}
	public AnimKey(AnimState state, String targetlayer, String action, boolean enabled) {
		this(state, targetlayer, action);
		enable(enabled);
	}
	
	protected boolean play() {
		setPlaying(true);
		return true;
	}
	protected boolean stop() {
		setPlaying(false);
		return true;
	}
	protected void setPlaying(boolean playing) {
		this.playing = playing;
	}
	public void enable(boolean enable) {
		enabled = enable;
	}
	
	public AnimState getState() {
		return state;
	}
	public String getTargetLayer() {
		return targetlayer;
	}
	public String getAction() {
		return action;
	}
	public boolean isPlaying() {
		return playing;
	}
	public boolean isEnabled() {
		return enabled && state.isEnabled();
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+"["+targetlayer+","+action+"]";
	}
	
}
