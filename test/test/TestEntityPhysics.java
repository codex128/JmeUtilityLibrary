/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import codex.jmeutil.es.ESAppState;
import codex.jmeutil.es.EntityState;
import codex.jmeutil.es.PhysicsState;
import codex.jmeutil.es.VisualState;
import codex.jmeutil.es.components.AngularVelocity;
import codex.jmeutil.es.components.GameObject;
import codex.jmeutil.es.components.LinearVelocity;
import codex.jmeutil.es.components.Mass;
import codex.jmeutil.es.components.Physics;
import codex.jmeutil.es.components.Position;
import codex.jmeutil.es.components.Rotation;
import codex.jmeutil.es.components.Visual;
import codex.jmeutil.es.update.AngularVelocityUpdater;
import codex.jmeutil.es.update.ComponentUpdateState;
import codex.jmeutil.es.update.LinearVelocityUpdater;
import codex.jmeutil.es.update.PositionUpdater;
import codex.jmeutil.es.update.RotationUpdater;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.JoyAxisEvent;
import com.jme3.input.event.JoyButtonEvent;
import com.jme3.input.event.KeyInputEvent;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.input.event.TouchEvent;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityId;

/**
 * Tests physics intergration with Zay-ES.
 * 
 * Press any key to make the block jump.
 * 
 * @author gary
 */
public class TestEntityPhysics extends SimpleApplication {
	
	public static void main(String[] args) {
		new TestEntityPhysics().start();
	}
	
	@Override
	public void simpleInitApp() {
		
		BulletAppState bulletapp = new BulletAppState();
		bulletapp.setDebugEnabled(true);
		
		MyState state = new MyState();
		inputManager.addRawInputListener(state);
		
		ComponentUpdateState update = new ComponentUpdateState();
		
		stateManager.attachAll(
			new EntityState(),
			new VisualState(),
			bulletapp,
			new PhysicsState(),
			new ComponentUpdateState(
				new PositionUpdater(),
				new RotationUpdater(),
				new LinearVelocityUpdater(),
				new AngularVelocityUpdater()),
			state);
		
	}
	
	
	private class MyState extends ESAppState implements RawInputListener {
		
		EntityId cube;
		
		@Override
		protected void init(Application app) {
			
			cube = ed.createEntity();
			ed.setComponents(cube,
					new GameObject("cube"),
					new Physics(),
					new Mass(1f),
					new Position().setUpdate(-1, true),
					new Rotation().setUpdate(-1, true),
					new LinearVelocity().setUpdate(-1, true),
					new Visual());
			
		}
		@Override
		protected void cleanup(Application app) {}
		@Override
		protected void onEnable() {}
		@Override
		protected void onDisable() {}
		@Override
		public void update(float tpf) {
			//System.out.println(ed.getComponent(cube, Position.class));
		}

		@Override
		public void beginInput() {}
		@Override
		public void endInput() {}
		@Override
		public void onJoyAxisEvent(JoyAxisEvent evt) {}
		@Override
		public void onJoyButtonEvent(JoyButtonEvent evt) {}
		@Override
		public void onMouseMotionEvent(MouseMotionEvent evt) {}
		@Override
		public void onMouseButtonEvent(MouseButtonEvent evt) {}
		@Override
		public void onKeyEvent(KeyInputEvent evt) {
			ed.setComponent(cube, new LinearVelocity(new Vector3f(0f, 10f, 0f)).setUpdate(1, false));
			ed.setComponent(cube, new AngularVelocity(new Vector3f(
					FastMath.rand.nextFloat()*4-2,
					FastMath.rand.nextFloat()*4-2,
					FastMath.rand.nextFloat()*4-2)).setUpdate(1, false));
		}
		@Override
		public void onTouchEvent(TouchEvent evt) {}
		
	}
	
}
