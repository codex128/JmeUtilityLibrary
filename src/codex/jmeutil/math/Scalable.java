/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.math;

import com.jme3.math.Vector3f;

/**
 *
 * @author gary
 */
public interface Scalable {
	
	public void setScale(Vector3f scale);
	public Vector3f getScale();
	
	public default void setScale(float scale) {
		setScale(new Vector3f(scale, scale, scale));
	}
	
}
