/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import codex.jmeutil.es.EntityState;
import codex.jmeutil.es.bullet.PhysicsState;
import codex.jmeutil.es.VisualState;
import codex.jmeutil.es.update.ComponentUpdateState;
import codex.jmeutil.es.update.PositionUpdater;
import codex.jmeutil.es.update.RotationUpdater;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;

/**
 *
 * @author gary
 */
public class TestEntityPhysicsCharacter extends SimpleApplication {
	
	public static void main(String[] args) {
		new TestEntityPhysicsCharacter().start();
	}

	@Override
	public void simpleInitApp() {
		
		BulletAppState bulletapp = new BulletAppState();
		bulletapp.setDebugEnabled(true);
		
		stateManager.attachAll(
			new EntityState(),
			new VisualState(),
			bulletapp,
			new PhysicsState(),
			new ComponentUpdateState(
				new PositionUpdater(),
				new RotationUpdater()));
		
	}
	
}
