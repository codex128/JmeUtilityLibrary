/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.factory;

import com.jme3.math.Vector3f;

/**
 *
 * @author gary
 */
public interface SpawnShape {
	
	public abstract Vector3f getRandomVolumePoint();
	public abstract Vector3f getRandomEdgePoint();
	
}
