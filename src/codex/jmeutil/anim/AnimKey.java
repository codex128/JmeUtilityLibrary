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
	private boolean playing = false;
	
	AnimKey(String targetlayer, String action) {
		this.targetlayer = targetlayer;
		this.action = action;
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
	
	public String getTargetLayer() {
		return targetlayer;
	}
	public String getAction() {
		return action;
	}
	public boolean isPlaying() {
		return playing;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+"["+targetlayer+","+action+"]";
	}
	
}
