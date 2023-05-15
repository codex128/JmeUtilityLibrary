/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.filters;

import com.simsilica.es.ComponentFilter;
import com.simsilica.es.EntityComponent;

/**
 *
 * @author gary
 * @param <T>
 */
public class NotFilter <T extends EntityComponent> implements ComponentFilter<T> {

	Class<T> type;
	ComponentFilter<? super T>[] operands;
	
	public NotFilter(Class<T> type, ComponentFilter<? super T>... operands) {
		this.type = type;
		this.operands = operands;
	}
	
	@Override
	public Class<T> getComponentType() {
		return type;
	}
	@Override
	public boolean evaluate(EntityComponent c) {
		if (!type.isInstance(c)) {
			return false;
		}
		if (operands == null) {
			return true;
		}
		for (ComponentFilter filter : operands) {
			if (filter.evaluate(c)) {
				return false;
			}
		}
		return true;
	}
	
}
