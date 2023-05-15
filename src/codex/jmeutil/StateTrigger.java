/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil;

import codex.jmeutil.logic.Binary;

/**
 *
 * @author gary
 */
public class StateTrigger implements Binary {
	
	int value = 0;
	
	public boolean released() {
		return value == 0;
	}
	public boolean active() {
		return value > 0;
	}	
	public int getValue() {
		return value;
	}
	
	public void update() {
		if (--value < 0) {
			value = 0;
		}
	}
	public void pull() {
		value = 2;
	}

	@Override
	public boolean getBinaryState() {
		return active();
	}
	
}
