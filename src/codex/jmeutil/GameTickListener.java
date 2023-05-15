/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil;

/**
 *
 * @author gary
 */
public interface GameTickListener {
	
	/**
	 * Called whenever a game tick occurs for the group
	 * this listener is associated with.
	 * To apply group time factor, use rate instead of tpf.
	 * @param group the group this listener is associated with
	 * @param rate the time per frame with group time factor applied
	 * @param tpf the real time per frame
	 */
	public abstract void gameTick(GameTickGroup group, float rate, float tpf);
	
	public default void setAssociatedTickGroup(GameTickGroup group) {}
	public default GameTickGroup getAssociatedTickGroup() {
		return null;
	}
	
}
