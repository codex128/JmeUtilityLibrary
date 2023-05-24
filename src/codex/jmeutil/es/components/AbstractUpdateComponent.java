/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

/**
 *
 * @author gary
 */
public abstract class AbstractUpdateComponent implements UpdatableComponent {
	
	int direction = UpdatableComponent.STABLE_ENTITY2WORLD;
	
	@Override
	public int getUpdateDirection() {
		return direction;
	}
	@Override
	public void setDirectionState(int direction) {
		this.direction = direction;
	}
	
}
