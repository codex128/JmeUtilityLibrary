/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

/**
 * Component representing a single boolean.
 * 
 * @author gary
 */
public class Active implements EntityBinaryComponent {
	
	private final boolean active;
	
	public Active(boolean active) {
		this.active = active;
	}	

	@Override
	public boolean getBinaryState() {
		return active;
	}
	@Override
	public String toString() {
		return "Active["+active+"]";
	}
	
}
