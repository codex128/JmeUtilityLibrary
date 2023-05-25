/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import codex.jmeutil.es.factory.PhysicsFactory;

/**
 *
 * @author gary
 */
public class Physics extends GameObject {
	
	public Physics() {
		super(PhysicsFactory.AUTO);
	}
	public Physics(String type) {
		super(type);
	}
	
}
