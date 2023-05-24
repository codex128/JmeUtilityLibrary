/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.components;

import com.simsilica.es.EntityComponent;

/**
 * A component that can either define or display.
 * 
 * @author gary
 * @param <T>
 */
public interface UpdatableComponent extends EntityComponent {
	
	/**
	 * Applies entity info to world info.
	 * i.e. apply entity position to spatial position.<br>
	 * This state is stable: does not change automatically.
	 */
	public static final int STABLE_ENTITY2WORLD = 2;
	/**
	 * Applies entity info to world info.
	 * i.e. apply entity position to spatial position.<br>
	 * This state is temporary: changes automatically to <code>STABLE_WORLD2ENTITY</code>.
	 */
	public static final int TEMP_ENTITY2WORLD = 1;
	/**
	 * Midpoint between the two paradigms.
	 * Values above this are entity to world.<br>
	 * Values below this are world to entity.
	 */
	public static final int MIDPOINT = 0;
	/**
	 * Applies world info to entity info.
	 * i.e. apply spatial position to entity position.<br>
	 * This state is temporary: changes automatically to <code>STABLE_ENTITY2WORLD</code>.
	 */
	public static final int TEMP_WORLD2ENTITY = -1;
	/**
	 * Applies world info to entity info.
	 * i.e. apply spatial position to entity position.<br>
	 * This state is stable: does not change automatically.
	 */
	public static final int STABLE_WORLD2ENTITY = -2;
	
	/**
	 * Returns the update direction.
	 * Values greater than zero have the entity inform the world.<br>
	 * Values less than zero have the world inform the entity.<br>
	 * Values of +/- 2 are stable, and will not automatically change.<br>
	 * Values of +/- 1 are temporary, and will change to the opposite stable version
	 * after the update occurs.
	 * @return update direction
	 */
	public int getUpdateDirection();
	/**
	 * Directly sets the update direction.
	 * Do not call. Use <code>setUpdateDirection(...)</code> instead.
	 * @param direction 
	 */
	public void setDirectionState(int direction);
	
	/**
	 * Changes temporary directions to opposite stable directions.
	 */
	public default void changeTempDirection() {
		if (getUpdateDirection() == TEMP_ENTITY2WORLD) {
			setDirectionState(STABLE_WORLD2ENTITY);
		}
		else if (getUpdateDirection() == TEMP_WORLD2ENTITY) {
			setDirectionState(STABLE_ENTITY2WORLD);
		}
	}
	public default void setUpdateDirection(int direction) {
		if (!isLegal(direction)) {
			throw new IllegalArgumentException("Direction "+direction+" is illegal.");
		}
		setDirectionState(direction);
	}
	public default void setUpdateDirection(int direction, boolean changeTemp) {
		setDirectionState(direction);
		if (changeTemp) changeTempDirection();
	}
	
	public static boolean isStable(int direction) {
		return direction == STABLE_ENTITY2WORLD || direction == STABLE_WORLD2ENTITY;
	}
	public static boolean isLegal(int direction) {
		return direction >= STABLE_WORLD2ENTITY &&
				direction <= STABLE_ENTITY2WORLD &&
				direction != MIDPOINT;
	}
	
}
