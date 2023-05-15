/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import codex.jmeutil.scene.SceneGraphIterator;
import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author gary
 */
public class TestSceneIteration extends SimpleApplication {

	public static void main(String[] args) {
		new TestSceneIteration().start();
	}
	
	@Override
	public void simpleInitApp() {
		
		Node n3 = new Node("i am #3");
		rootNode.attachChild(new Node("i am #1"));
		rootNode.attachChild(new Node("i am #2"));
		rootNode.attachChild(n3);
		rootNode.attachChild(new Node("i am #4"));
		rootNode.attachChild(new Node("i am #5"));
		
		n3.attachChild(new Node("i am #3's first"));
		n3.attachChild(new Node("i am #3's second"));
		n3.attachChild(new Node("i am #3's third"));
		n3.attachChild(new Node("i am #3's fourth"));
		n3.attachChild(new Node("i am #3's fifth"));
		n3.attachChild(new Node("i am #3's sixth"));
		n3.attachChild(new Node("i am #3's seventh"));
		
		for (Spatial spatial : new SceneGraphIterator(rootNode)) {
			System.out.println(spatial.getName());
		}
		
	}
	
}
