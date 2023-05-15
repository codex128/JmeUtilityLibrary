/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil;

import codex.jmeutil.listen.Listenable;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author gary
 */
public class GameTickGroup implements Listenable<GameTickListener> {
	
	String name;
	float timefactor = 1f;
	LinkedList<GameTickListener> listeners = new LinkedList<>();
	LinkedList<GameTickGroup> groups = new LinkedList<>();
	
	public GameTickGroup(String name) {
		this.name = name;
	}
	
	public void update(float tpf) {
		for (GameTickListener listener : listeners) {
			listener.gameTick(this, tpf*timefactor, tpf);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setTimeFactor(float factor) {
		timefactor = factor;
	}
	public float getTimeFactor() {
		return timefactor;
	}
	
	@Override
	public Collection<GameTickListener> getListeners() {
		return listeners;
	}	
	@Override
	public void addListener(GameTickListener listener) {
		listeners.add(listener);
		listener.setAssociatedTickGroup(this);
	}
	@Override
	public void removeListener(GameTickListener listener) {
		listeners.remove(listener);
		listener.setAssociatedTickGroup(null);
	}

}
