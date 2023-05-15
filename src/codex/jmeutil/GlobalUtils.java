/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil;

import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.util.Collection;

/**
 *
 * @author gary
 */
public class GlobalUtils {
	
	/**
	 * Get the nearest control of the asserted type in the spatial's hierarchy.
	 * @param <T>
	 * @param type control type
	 * @param spatial origin spatial
	 * @param depth maximum depth this method is allowed to search, or -1 for unlimited depth
	 * @return first control of the asserted type
	 */
	public static <T extends Control> T getControl(Class<T> type, Spatial spatial, int depth) {
		while (spatial != null && depth != 0) {
			T control = spatial.getControl(type);
			if (control != null) {
				return control;
			}
			spatial = spatial.getParent();
			depth--;
		}
		return null;
	}
	
	/**
	 * Stores all elements in collection in the given array.
	 * @param <T>
	 * @param collection
	 * @param array
	 * @return 
	 */
	public static <T> T[] toArray(Collection<T> collection, T[] array) {
		int i = 0;
		for (T e : collection) {
			array[i++] = e;
		}
		return array;
	}
	
	
	
}
