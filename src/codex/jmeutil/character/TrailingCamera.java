/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.character;

import codex.jmeutil.Motion;
import codex.jmeutil.math.FDomain;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author gary
 */
public class TrailingCamera extends AbstractControl  {
	
	Camera camera;
	Vector3f up = new Vector3f(0f, 1f, 0f);
	FDomain distance = new FDomain(1f, 10f);
	
	public TrailingCamera(Camera camera) {
		this.camera = camera;
	}
	
	@Override
	protected void controlUpdate(float tpf) {
		Vector3f focus = spatial.getWorldTranslation();
		float dist = camera.getLocation().distanceSquared(focus);
		if (dist < distance.getMin()*distance.getMin()) {
			camera.setLocation(camera.getLocation().subtract(focus)
					.normalizeLocal().multLocal(distance.getMin()).addLocal(focus));
		}
		else if (dist > distance.getMax()*distance.getMax()) {			
			camera.setLocation(camera.getLocation().subtract(focus)
					.normalizeLocal().multLocal(distance.getMax()).addLocal(focus));
		}
		camera.lookAt(focus, up);
	}
	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {}
	
}
