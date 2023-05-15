/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.logic;

/**
 * Represents binary input of <code>Logic</code>.
 * 
 * @author gary
 */
public class BinaryInput {
	
	public final int t, f;
	private final Binary[] array;
	
	public BinaryInput(Binary[] array) {
		int t = 0, f = 0;
		for (Binary b : array) {
			if (Binary.isTrue(b)) t++;
			else f++;
		}
		this.t = t;
		this.f = f;
		this.array = array;
	}
	public BinaryInput(BinaryInput input) {
		t = input.t;
		f = input.f;
		array = input.array;
	}
	
	public int getTrueBinaries() {
		return t;
	}
	public int getFalseBinaries() {
		return f;
	}
	public int getTotalBinaries() {
		return t+f;
	}
	public Binary[] getBinaryArray() {
		return array;
	}
	
}
