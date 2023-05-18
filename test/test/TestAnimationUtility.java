/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import codex.jmeutil.anim.AnimKey;
import codex.jmeutil.anim.AnimStackControl;
import codex.jmeutil.anim.AnimState;
import codex.jmeutil.anim.SynchronizedAnimState;
import codex.jmeutil.anim.TargetTween;
import codex.jmeutil.character.OrbitalCamera;
import com.jme3.anim.AnimComposer;
import com.jme3.anim.ArmatureMask;
import com.jme3.anim.SkinningControl;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.input.FunctionId;
import com.simsilica.lemur.input.InputMapper;
import com.simsilica.lemur.input.InputState;
import com.simsilica.lemur.input.StateFunctionListener;

/**
 *
 * @author gary
 */
public class TestAnimationUtility extends SimpleApplication implements StateFunctionListener {
	
	private static final FunctionId
			RUN = new FunctionId("main", "run"),
			CHOP = new FunctionId("main", "chop"),
			STAB = new FunctionId("main", "stab");
	
	AnimStackControl stack;
	
	public static void main(String[] args) {
		new TestAnimationUtility().start();
	}
	
	@Override
	public void simpleInitApp() {
		
		GuiGlobals.initialize(this);
		GuiGlobals.getInstance().setCursorEventsEnabled(false);
		InputMapper im = GuiGlobals.getInstance().getInputMapper();
		
		rootNode.addLight(new DirectionalLight(new Vector3f(1f, -1f, 1f)));
		rootNode.addLight(new DirectionalLight(new Vector3f(-1f, -1f, -1f)));
		
		Spatial person = assetManager.loadModel("Models/person.j3o");
		rootNode.attachChild(person);
		AnimComposer anim = person.getControl(AnimComposer.class);
		SkinningControl skin = person.getControl(SkinningControl.class);
		
		anim.makeLayer("body", ArmatureMask.createMask(skin.getArmature(), "lower_body"));
		ArmatureMask legmask = new ArmatureMask();
		legmask.addFromJoint(skin.getArmature(), "legs_main");
		legmask.addBones(skin.getArmature(), "main");
		anim.makeLayer("legs", legmask);
		anim.setCurrentAction("idle_body", "body");
		anim.setCurrentAction("idle_legs", "legs");
		anim.action("run_body").setSpeed(1.5);
		anim.action("run_legs").setSpeed(1.5);
		stack = new AnimStackControl();
		anim.actionSequence("chop_once", anim.action("chop"), new TargetTween<AnimStackControl>(stack) {
			@Override
			protected void invoke(AnimStackControl target) {
				target.enableState("chop", false);
			}
		});
		anim.actionSequence("stab_once_body", anim.action("stab_body"), new TargetTween<AnimStackControl>(stack) {
			@Override
			protected void invoke(AnimStackControl target) {
				target.enableState("stab", false);
			}
		});
		anim.actionSequence("stab_once_legs", anim.action("stab_legs"), new TargetTween<AnimStackControl>(stack) {
			@Override
			protected void invoke(AnimStackControl target) {
				target.enableState("stab", false);
			}
		});
		
		AnimState idle = new AnimState("idle", true);
		idle.add("body", "idle_body");
		idle.add("legs", "idle_legs");
		AnimState run = new SynchronizedAnimState("run");
		run.add("body", "run_body");
		run.add("legs", "run_legs");
		AnimState chop = new AnimState("chop");
		chop.add("body", "chop_once");
		AnimState stab = stack.add(new AnimState("stab"), false);
		AnimKey stabBody = stab.add("body", "stab_once_body");
		AnimKey stabLegs = stab.add("legs", "stab_once_legs");
		stack.add(idle);
		stack.add(stabLegs);
		stack.add(run);
		stack.add(chop);
		stack.add(stabBody);
		person.addControl(stack);
		
		OrbitalCamera camera = new OrbitalCamera(cam, im);
		camera.getDistanceDomain().set(15f, 20f);
		camera.setOffsets(new Vector3f(0f, 2.5f, 0f));
		person.addControl(camera);
		im.activateGroup(OrbitalCamera.INPUT_GROUP);
		
		im.map(RUN, KeyInput.KEY_A);
		im.map(CHOP, KeyInput.KEY_S);
		im.map(STAB, KeyInput.KEY_D);
		im.addStateListener(this, RUN, CHOP, STAB);
		im.activateGroup("main");
		
	}

	@Override
	public void valueChanged(FunctionId func, InputState value, double tpf) {
		if (func == RUN) {
			stack.enableState("run", !value.equals(InputState.Off));
		}
		else if (func == CHOP && value != InputState.Off) {
			stack.enableState("chop", true);
		}
		else if (func == STAB && value != InputState.Off) {
			stack.enableState("stab", true);
		}
	}
	
}
