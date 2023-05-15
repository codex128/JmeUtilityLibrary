/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil;

import codex.jmeutil.listen.Listenable;
import java.util.function.Consumer;

/**
 *
 * @author gary
 * @param <T> type of general listener
 * @param <R> type of user
 */
public interface Usable <T, R extends T> extends Listenable<T> {
	
	/**
	 * Directly sets the current user.
	 * Does not (and should not) add the given user as a general listener.
	 * Other classes should invoke <code>setUser(...)</code> instead.
	 * @param user 
	 */
	public abstract void setClient(R user);
	public abstract R getUser();
	
	public default void setUser(R user) {
		if (getUser() != null) {
			removeListener(getUser());
		}
		setClient(user);
		if (getUser() != null) {
			addListener(getUser());
		}
	}	
	public default void notifyUser(Consumer<R> notify) {
		notify.accept(getUser());
	}
	
}
