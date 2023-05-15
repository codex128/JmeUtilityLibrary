/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.jme3.math.ColorRGBA;
import com.simsilica.es.EntityComponent;

/**
 * Component representing the entity's color.
 * 
 * Used primarily as an argument for model factories.
 * 
 * @author gary
 */
public class Color implements EntityComponent {
	
	private final ColorRGBA color;
	
	public Color(ColorRGBA color) {
		this.color = color;
	}
	
	public ColorRGBA getColor() {
		return color;
	}
	@Override
	public String toString() {
		return "Color["+color+"]";
	}
	
}
