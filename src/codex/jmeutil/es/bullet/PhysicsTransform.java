/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.bullet;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author gary
 */
public interface PhysicsTransform {
	
	public void applyPhysicsLocation(Vector3f vec);
	public void applyPhysicsRotation(Quaternion rot);
	public void applyPhysicsScale(Vector3f scale);
	
}
