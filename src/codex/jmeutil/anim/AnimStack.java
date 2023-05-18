/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.anim;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implementation of <code>LinkedList</code> with a descending iterator.
 * 
 * @author gary
 */
public class AnimStack extends LinkedList<AnimKey> {
	
	private final String targetlayer;
	
	public AnimStack(String targetlayer) {
		this.targetlayer = targetlayer;
	}
	
	@Override
	public Iterator iterator() {
		return descendingIterator();
	}
	public String getTargetLayer() {
		return targetlayer;
	}
	
}
