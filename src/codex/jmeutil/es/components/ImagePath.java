/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.simsilica.es.EntityComponent;

/**
 * Contains a string representing an image asset.
 * 
 * Used mainly as an argument for model factories.
 * 
 * @author gary
 */
public class ImagePath implements EntityComponent {
	
	private final String image;
	
	public ImagePath(String image) {
		this.image = image;
	}
	
	public String getImagePath() {
		return image;
	}
	@Override
	public String toString() {
		return "ImagePath["+image+"]";
	}
	
}
