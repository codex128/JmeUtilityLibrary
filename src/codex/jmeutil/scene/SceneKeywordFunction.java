/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.scene;

import com.jme3.scene.Spatial;

/**
 *
 * @author gary
 */
public interface SceneKeywordFunction <T> {
	
	public abstract String getKeyword();
	public abstract void accept(T value, Spatial spatial);
	
	public default T test(Spatial spatial) {
		T value = spatial.getUserData(getKeyword());
		if (value != null) {
			accept(value, spatial);
		}
		return value;
	}
	
}
