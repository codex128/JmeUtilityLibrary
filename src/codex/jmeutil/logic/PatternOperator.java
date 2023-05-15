/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.logic;

/**
 * Marks binary input as true if the order of true/false values matches a pattern.
 * 
 * @author gary
 */
public class PatternOperator implements LogicalOperator {

	boolean[] pattern;
	
	public PatternOperator(boolean... pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public boolean isTrue(BinaryInput input) {
		int index = 0;
		for (Binary b : input.getBinaryArray()) {
			if (index >= pattern.length) break;
			boolean state = b.getBinaryState();
			if ((!state && pattern[index]) || (state && !pattern[index])) {
				return false;
			}
			index++;
		}
		return true;
	}
	
}
