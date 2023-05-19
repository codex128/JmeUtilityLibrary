/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.scene;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Iterates through the scene graph starting with a particular spatial.
 * 
 * @author gary
 */
public class SceneGraphIterator implements Iterable<Spatial>, Iterator<Spatial> {

	LinkedList<PathNode> path = new LinkedList<>();
	LinkedList<Spatial> detach = new LinkedList<>();
	
	public SceneGraphIterator(Spatial main) {
		if (main instanceof Node) {
			path.add(new PathNode((Node)main));
		}
	}
	
	@Override
	public Iterator<Spatial> iterator() {
		return this;
	}
	@Override
	public boolean hasNext() {
		trim();
		return !path.isEmpty();
	}
	@Override
	public Spatial next() {
		Spatial spatial = path.getLast().node.getChild(path.getLast().childIndex++);
		if (spatial instanceof Node) {
			path.addLast(new PathNode((Node)spatial));
		}
		return spatial;
	}
	
	/**
	 * Makes this iterator ignore all children of the current spatial.
	 * The children of the current spatial will not be iterated through.
	 */
	public void ignoreChildren() {
		path.removeLast();
	}
	
	/**
	 * Trims the path to the first available node.
	 */
	private void trim() {
		if (!path.isEmpty() && path.getLast().childIndex >= path.getLast().node.getQuantity()) {
			path.removeLast();
			trim();
		}
	}
	
	
	private static class PathNode {
		Node node;
		int childIndex = 0;
		PathNode(Node node) {
			this.node = node;
		}
	}
	
}
