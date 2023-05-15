/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author gary
 */
public class SceneGraphTestControl extends AbstractControl {

	
	
	@Override
	protected void controlUpdate(float tpf) {
		System.out.println(spatial.getName()+" spatial is attached to scene graph");
	}
	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {}
	
}
