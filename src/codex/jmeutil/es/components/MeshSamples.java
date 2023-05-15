/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.simsilica.es.EntityComponent;

/**
 *
 * @author gary
 */
public class MeshSamples implements EntityComponent {
	
	private final int axis, radial;

	public MeshSamples(int axis, int radial) {
		this.axis = axis;
		this.radial = radial;
	}	
	
	public int getAxis() {
		return axis;
	}
	public int getRadial() {
		return radial;
	}
	@Override
	public String toString() {
		return "MeshSamples["+axis+", "+radial+"]";
	}
	
}
