/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.scene;

import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import java.util.LinkedList;

/**
 *
 * @author gary
 */
public class WorldBuilder implements SceneGraphVisitor {
	
	private static final LinkedList<SceneKeywordFunction> globalkeywords = new LinkedList<>();	
	public static void addGlobalKeywordFunction(SceneKeywordFunction function) {
		globalkeywords.add(function);
	}
	public static void addGlobalKeywordFunctions(SceneKeywordFunction... functions) {
		for (SceneKeywordFunction function : functions) {
			addGlobalKeywordFunction(function);
		}
	}
	
	private final LinkedList<SceneKeywordFunction> localkeywords = new LinkedList<>();
	public void addLocalKeywordFunction(SceneKeywordFunction function) {
		localkeywords.add(function);
	}
	public void addLocalKeywordFunctions(SceneKeywordFunction... functions) {
		for (SceneKeywordFunction function : functions) {
			addLocalKeywordFunction(function);
		}
	}
	
	private static final WorldBuilder globalbuilder = new WorldBuilder();
	public static WorldBuilder getGlobalBuilder() {
		return globalbuilder;
	}
	
	public void build(Spatial world) {
		world.depthFirstTraversal(this);
	}
	@Override
	public void visit(Spatial spatial) {
		for (SceneKeywordFunction function : globalkeywords) {
			function.test(spatial);
		}
		for (SceneKeywordFunction function : localkeywords) {
			function.test(spatial);
		}
	}
	
}
