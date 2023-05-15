/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.logic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author gary
 */
public interface Binary {
	
	/**
	 * Binary that always returns true.
	 */
	public static final Binary TRUE =  () -> { return true;  };
	/**
	 * Binary that always returns false.
	 */
	public static final Binary FALSE = () -> { return false; };
	/**
	 * Binary that returns a random boolean.
	 */
	public static final Binary RANDOM = () -> { return ThreadLocalRandom.current().nextBoolean(); };
	
	/**
	 * Returns the binary state of this object.
	 * @return binary state
	 */
	public boolean getBinaryState();
	
	/**
	 * Returns true if the given binary is not null and its binary state is true.
	 * @param b given binary
	 * @return true if not null and true binary state
	 */
	public static boolean isTrue(Binary b) {
		return b != null && b.getBinaryState();
	}
	
}
