/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import groovyjarjarantlr4.v4.misc.OrderedHashMap;
import java.util.LinkedHashMap;
import java.util.function.Consumer;

/**
 * Not finished yet.
 * 
 * @author gary
 */
public class GameTickManager extends BaseAppState {
	
	public static final String DEFAULT_GROUP = "{default_group}";
	
	private final LinkedHashMap<String, GameTickGroup> groups = new LinkedHashMap<>();
	
	@Override
	protected void initialize(Application app) {
		createGroup(DEFAULT_GROUP);
	}
	@Override
	protected void cleanup(Application app) {
		groups.clear();
	}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	@Override
	public void update(float tpf) {
		for (GameTickGroup g : groups.values()) {
			g.update(tpf);
		}
	}
	
	public GameTickGroup createGroup(String name) {
		GameTickGroup gtg = new GameTickGroup(name);
		if (groups.putIfAbsent(name, gtg) == null) {
			return gtg;
		}
		else {
			return null;
		}
	}
	public void addGroup(GameTickGroup group) {
		groups.putIfAbsent(group.getName(), group);
	}
	public GameTickGroup removeGroup(String name) {
		return groups.remove(name);
	}
	public GameTickGroup removeGroup(GameTickGroup group) {
		return groups.remove(group.getName());
	}
	
	public void forEach(Consumer<GameTickGroup> foreach) {
		for (GameTickGroup group : groups.values()) {
			foreach.accept(group);
		}
	}
	public void forEachNamed(Consumer<GameTickGroup> foreach, String... names) {
		for (String name : names) {
			GameTickGroup group = getGroup(name);
			if (group != null) {
				foreach.accept(group);
			}
		}
	}
	
	public GameTickGroup getGroup(String name) {
		return groups.get(name);
	}	
	public LinkedHashMap<String, GameTickGroup> getGroups() {
		return groups;
	}
	
}
