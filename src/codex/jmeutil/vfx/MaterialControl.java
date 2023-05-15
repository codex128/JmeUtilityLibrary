/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.vfx;

import codex.jmeutil.control.GeometryControl;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;

/**
 * Controls the material of the geometry using a <code>MaterialInfluencer</code>.
 * 
 * @author gary
 */
public class MaterialControl extends GeometryControl {
	
	MaterialInfluencer influencer;
	Object lastvalue;
	
	public MaterialControl(MaterialInfluencer influencer) {
		this.influencer = influencer;
	}
	
	@Override
	protected void controlUpdate(float tpf) {
		Object value = influencer.getParameterValue(tpf);
		if (!value.equals(lastvalue)) {
			geometry.getMaterial().setParam(influencer.getParameterName(), influencer.getParameterType(), value);
			lastvalue = value;
		}
	}
	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {}
	
}
