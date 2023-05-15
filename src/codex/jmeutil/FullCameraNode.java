/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil;

import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

/**
 *
 * @author gary
 */
public class FullCameraNode extends Node {
	
	Camera cam;
	
	public FullCameraNode(Camera cam) {
		super();
		setCamera(cam);
	}
	public FullCameraNode(String name, Camera cam) {
		super(name);
		setCamera(cam);
	}
	
	public void setCamera(Camera cam) {
		this.cam = cam;
	}
	public Camera getCamera() {
		return cam;
	}
	
	@Override
	public void updateLogicalState(float tpf) {
		super.updateLogicalState(tpf);
		cam.setLocation(getWorldTranslation());
		cam.setRotation(getWorldRotation());
	}
	
}
