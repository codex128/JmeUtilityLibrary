/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil;

import java.util.Iterator;

/**
 *
 * @author gary
 */
public class StringIterator implements Iterator<Character>, Iterable<Character> {

	String string;
	int index = 0;
	
	public StringIterator(String string) {
		this.string = string;
	}
	
	@Override
	public boolean hasNext() {
		if (index < string.length()) {
			return true;
		}
		else {
			index = 0;
			return false;
		}
	}
	@Override
	public Character next() {
		return string.charAt(index++);
	}
	@Override
	public Iterator iterator() {
		return this;
	}
	
}
