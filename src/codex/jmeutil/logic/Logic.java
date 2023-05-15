/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.logic;

/**
 * Class for logical operations.
 * 
 * @author gary
 */
public class Logic implements Binary {
	
	Binary[] binaries;
	LogicalOperator operator;
	
	public Logic(Binary... binaries) {
		setOperator(LogicalOperator.AND);
		setBinaries(binaries);
	}
	public Logic(LogicalOperator operator, Binary... binaries) {
		setOperator(operator);
		setBinaries(binaries);
	}
	
	/**
	 * Get the binary state of this logic.
	 * @return 
	 */
	@Override
	public boolean getBinaryState() {
		return operator.isTrue(countBinaries());
	}
	
	/**
	 * Counts the number of true and false binaries this logic contains.
	 * A null binary is automatically counted as false.
	 * @return BinaryInput representing the number of true and false binaries
	 */
	public BinaryInput countBinaries() {
		return new BinaryInput(binaries);
	}
	/**
	 * Counts the number of true array this logic contains.
	 * @return 
	 */
	public int countTrueBinaries() {
		int t = 0;
		for (Binary b : binaries) {
			if (Binary.isTrue(b)) t++;
		}
		return t;
	}
	/**
	 * Counts the number of false binaires this logic contains
	 * @return 
	 */
	public int countFalseBinaries() {
		int f = 0;
		for (Binary b : binaries) {
			if (!Binary.isTrue(b)) f++;
		}
		return f;
	}
	
	/**
	 * Set the array this logic uses.
	 * @param binaries 
	 */
	public void setBinaries(Binary... binaries) {
		this.binaries = binaries;
	}
	/**
	 * Set custom operator.
	 * The custom operator, if not null, overrides the current operator.
	 * @param operator 
	 */
	public void setOperator(LogicalOperator operator) {
		this.operator = operator;
	}
	
	public Binary[] getBinaries() {
		return binaries;
	}
	public int getNumBinaries() {
		return binaries.length;
	}
	public LogicalOperator getOperator() {
		return operator;
	}
	@Override
	public String toString() {
		return "Logic["+getBinaryState()+"]";
	}
	
}
