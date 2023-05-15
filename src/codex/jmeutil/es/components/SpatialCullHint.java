/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.jme3.scene.Spatial;
import com.simsilica.es.EntityComponent;

/**
 *
 * @author gary
 */
public class SpatialCullHint implements EntityComponent {
	
	Spatial.CullHint hint = Spatial.CullHint.Inherit;
	
	public SpatialCullHint() {}
	public SpatialCullHint(Spatial.CullHint hint) {
		this.hint = hint;
	}
	
	public Spatial.CullHint getHint() {
		return hint;
	}
	@Override
	public String toString() {
		return "SpatialCullHint["+hint+"]";
	}
	
}
