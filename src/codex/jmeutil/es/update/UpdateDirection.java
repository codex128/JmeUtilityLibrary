/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.update;

/**
 *
 * @author gary
 */
public class UpdateDirection {
	
	int value = 1;
	boolean stable = true;
	
	public UpdateDirection() {}
	public UpdateDirection(int value) {
		setValue(value);
	}
	public UpdateDirection(int value, boolean stable) {
		setValue(value);
		setStable(stable);
	}
	
	public void increment() {
		if (stable) return;
		int dir = -sign(value);
		value += dir;
		if (value == 0) {
			value += dir;
			setStable(true);
		}
	}
	public boolean isEntityToWorld() {
		return value > 0;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	public void setStable(boolean stable) {
		this.stable = stable;
	}
	
	public int getValue() {
		return value;
	}
	public boolean isStable() {
		return stable;
	}
	
	private static int sign(int n) {
		if (n > 0) return 1;
		else if (n < 0) return -1;
		return 0;
	}
	
}
