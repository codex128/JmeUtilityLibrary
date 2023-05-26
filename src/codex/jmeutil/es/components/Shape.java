/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.components;

import com.simsilica.es.EntityComponent;
import java.util.Arrays;

/**
 * Component which defines the shape and size of the entity.
 * 
 * @author gary
 */
public class Shape implements EntityComponent {
	
	private final String name;
	private final float[] parameters;
	
	public Shape(String name, float... parameters) {
		this.name = name;
		this.parameters = parameters;
	}
	
	public String getShapeName() {
		return name;
	}
	public float[] getParameters() {
		return parameters;
	}
	
	@Override
	public String toString() {
		return "Shape["+name+", "+Arrays.toString(parameters)+"]";
	}
	
}
