/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import com.simsilica.es.ComponentFilter;
import com.simsilica.es.EntityComponent;
import com.simsilica.es.Filters;

/**
 * Component representing the entity's primary purpose in the game.
 * 
 * @author gary
 */
public class GameObject implements EntityComponent {
	
	private final String type;
	
	public GameObject(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	@Override
	public String toString() {
		return "GameObject["+type+"]";
	}
	
	/**
	 * Constructs a component filter based on game object type.
	 * Is compatible with subclasses.
	 * @param classtype
	 * @param type
	 * @return 
	 */
	public static ComponentFilter filterGameObject(Class<? extends GameObject> classtype, String type) {
		return Filters.fieldEquals(classtype, "type", type);
	}
	/**
	 * Constructs a component filter based on game object type.
	 * Is not compatible with subclasses.
	 * @param type
	 * @return 
	 */
	public static ComponentFilter filterGameObject(String type) {
		return Filters.fieldEquals(GameObject.class, "type", type);
	}
	
}
