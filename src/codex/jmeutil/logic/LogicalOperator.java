/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.logic;

/**
 * Interface for determining true/false state of a set of Binary inputs.
 * 
 * @author gary
 */
public interface LogicalOperator {
	
	/**
	 * Returns true if all inputs are true.
	 */
	public static final LogicalOperator
			AND = (BinaryInput input) -> {
				return input.f == 0;
			};
	/**
	 * Returns true if at least one input is true.
	 */
	public static final LogicalOperator
			OR = (BinaryInput input) -> {
				return input.t > 0;
			};
	/**
	 * Returns true if no inputs are true.
	 */
	public static final LogicalOperator
			NOR = (BinaryInput input) -> {
				return input.t == 0;
			};
	/**
	 * Returns true if at least one input is false.
	 */
	public static final LogicalOperator
			NAND = (BinaryInput input) -> {
				return input.f > 0;
			};
	/**
	 * Returns true if there are more true inputs than false inputs.
	 */
	public static final LogicalOperator
			GREATER = (BinaryInput input) -> {
				return input.t > input.f;
			};
	/**
	 * Returns true if there are less true inputs than false inputs.
	 */
	public static final LogicalOperator
			LESSER = (BinaryInput input) -> {
				return input.t < input.f;
			};
	/**
	 * Returns true if there are an equal number of true and false inputs.
	 */
	public static final LogicalOperator
			EQUAL = (BinaryInput input) -> {
				return input.t == input.f;
			};
	
	public abstract boolean isTrue(BinaryInput input);
	
}
