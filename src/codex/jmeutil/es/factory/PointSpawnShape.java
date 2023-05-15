/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.factory;

import com.jme3.math.Vector3f;

/**
 *
 * @author gary
 */
public class PointSpawnShape implements SpawnShape {

	@Override
	public Vector3f getRandomVolumePoint() {
		return new Vector3f();
	}
	@Override
	public Vector3f getRandomEdgePoint() {
		return new Vector3f();
	}
	
}
