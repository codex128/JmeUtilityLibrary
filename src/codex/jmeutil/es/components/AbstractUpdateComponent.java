/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import codex.jmeutil.es.update.UpdateDirection;
import com.simsilica.es.EntityComponent;

/**
 *
 * @author gary
 */
public abstract class AbstractUpdateComponent implements UpdatableComponent {
	
	UpdateDirection direction = new UpdateDirection();
	
	@Override
	public UpdateDirection getUpdateDirection() {
		return direction;
	}
	@Override
	public EntityComponent setUpdateDirection(UpdateDirection dir) {
		direction = dir;
		return this;
	}
	public EntityComponent setUpdateValue(int value) {
		direction.setValue(value);
		return this;
	}
	public EntityComponent setUpdateAsStable(boolean stable) {
		direction.setStable(stable);
		return this;
	}
	public EntityComponent setUpdate(int value, boolean stable) {
		direction.setValue(value);
		direction.setStable(stable);
		return this;
	}
	
}
