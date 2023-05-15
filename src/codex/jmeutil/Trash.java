/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil;

import java.util.Collection;
import java.util.LinkedList;

/**
 * For storing objects intended for removal from specific collections.
 * Mainly used when an object should be removed while iterating through a collection.
 * 
 * @author gary
 */
public class Trash extends LinkedList<Trash.Garbage> {
	
	public Trash() {}
	
	public <T> void add(T object, Collection<T>... owners) {
		add(new Garbage(object, owners));
	}
	@Override
	public void clear() {
		for (Garbage g : this) {
			g.destroy();
		}
		super.clear();
	}
	
	public static final class Garbage <T> {
		T object;
		Collection<T>[] owners;
		public Garbage(T object, Collection<T>... owners) {
			this.object = object;
			this.owners = owners;
		}
		public T getObject() {
			return object;
		}
		public Collection<T>[] getOwners() {
			return owners;
		}
		private void destroy() {
			for (Collection<T> c : owners) {
				c.remove(object);
			}
		}
	}
	
}
