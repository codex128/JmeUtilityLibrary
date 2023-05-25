/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.components;

import codex.jmeutil.es.update.UpdateDirection;
import com.simsilica.es.EntityComponent;

/**
 * A component that can either define or display.
 * 
 * @author gary
 */
public interface UpdatableComponent extends EntityComponent {
	
	/**
	 * Returns the update direction.
	 * Values greater than zero have the entity inform the world.<br>
	 * Values less than zero have the world inform the entity.<br>
	 * Values of +/- 2 are stable, and will not automatically change.<br>
	 * Values of +/- 1 are temporary, and will change to the opposite stable version
	 * after the update occurs.
	 * @return update direction
	 */
	public UpdateDirection getUpdateDirection();
	public EntityComponent setUpdateDirection(UpdateDirection dir);
	
	public default void inheritUpdateDirection(UpdatableComponent predecessor) {
		setUpdateDirection(predecessor.getUpdateDirection());
	}
	
}
