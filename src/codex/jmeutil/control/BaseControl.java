/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.control;

import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author gary
 */
public abstract class BaseControl extends AbstractControl {
	
	@Override
	public void setSpatial(Spatial spat) {
		if (spatial != null && spatial != spat) {
			spatialRemoved(spatial);
		}
		super.setSpatial(spat);
		if (spatial != null) {
			spatialAssigned(spatial);
		}
	}
	
	protected abstract void spatialAssigned(Spatial spatial);
	protected abstract void spatialRemoved(Spatial spatial);
	
}
