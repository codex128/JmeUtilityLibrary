/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import codex.jmeutil.logic.Binary;
import codex.jmeutil.logic.BinaryInput;
import codex.jmeutil.logic.Logic;
import codex.jmeutil.logic.LogicalOperator;
import codex.jmeutil.logic.PatternOperator;

/**
 *
 * @author gary
 */
public class TestLogic {
	
	public static void main(String[] args) {
		
		Logic logic1 = new Logic(LogicalOperator.AND, Binary.TRUE, Binary.RANDOM);
		System.out.println(logic1);
		Logic logic2 = new Logic(LogicalOperator.OR, Binary.RANDOM, Binary.RANDOM);
		System.out.println(logic2);
		Logic logic3 = new Logic(new LogicalOperator() {
			@Override
			public boolean isTrue(BinaryInput input) {
				return input.f < input.t || input.t == 0;
			}
		}, Binary.RANDOM, Binary.RANDOM, Binary.RANDOM, Binary.RANDOM);
		System.out.println(logic3);
		Logic logic4 = new Logic(new PatternOperator(true, false, true, false),
				Binary.TRUE, Binary.FALSE, Binary.TRUE, Binary.FALSE);
		System.out.println(logic4);
		
	}
	
}
