/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.math;

import com.jme3.math.Quaternion;

/**
 *
 * @author gary
 */
public interface Rotatable {
	
	public void setRotation(Quaternion rotation);
	public Quaternion getRotation();
	
}
