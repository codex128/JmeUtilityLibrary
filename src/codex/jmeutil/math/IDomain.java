/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.math;

import java.util.Collection;

/**
 *
 * @author gary
 */
public class IDomain implements Cloneable {
	
	Integer min, max;
	Collection collection;
	
	public IDomain() {}
	public IDomain(IDomain range) {
		set(range);
	}
	public IDomain(Collection collection) {
		set(0, collection);
	}
	public IDomain(Integer min, Integer max) {
		set(min, max);
	}
	public IDomain(Integer min, Collection collection) {
		set(min, collection);
	}
	public IDomain(Integer min, Integer max, Collection collection) {
		set(min, max, collection);
	}
	
	/**
	 * Set the default minimum value.
	 * May be null, in which case there is no default minimum.
	 * @param min
	 * @return
	 */
	public IDomain setMin(Integer min) {
		this.min = min;
		verifyValues();
		return this;
	}
	/**
	 * Set the default maximum value.
	 * May be null, in which case there is no default maximum.
	 * @param max
	 * @return 
	 */
	public IDomain setMax(Integer max) {
		this.max = max;
		verifyValues();
		return this;
	}
	/**
	 * Set the collection that defines the domain.
	 * If the collection is not null, the domain aligns itself with the
	 * collection's size. 
	 * @param collection
	 * @return 
	 */
	public IDomain setCollection(Collection collection) {
		this.collection = collection;
		return this;
	}
	public IDomain set(IDomain range) {
		return setMin(range.min).setMax(range.max);
	}
	public IDomain set(Integer min, Integer max) {
		return setMin(min).setMax(max);
	}
	public IDomain set(Integer min, Collection collection) {
		return setMin(min).setCollection(collection);
	}
	public IDomain set(Integer min, Integer max, Collection collection) {
		return setMin(min).setMax(max).setCollection(collection);
	}
	
	/**
	 * Constrains the value between the minimum and maximum values.
	 * @param value
	 * @return the constrained value
	 */
	public int applyConstrain(int value) {
		if (getMin() != null && value < getMin()) {
			return getMin();
		}
		else if (getMax() != null && value > getMax()) {
			return getMax();
		}
		else {
			return value;
		}
	}
	
	public Integer getMin() {
		if (collection == null) {
			return min;
		}
		else {
			return 0;
		}
	}
	public Integer getMax() {
		if (collection != null) {
			int size = collection.size();
			if (max == null || max >= size) {
				return size;
			}
		}
		return max;
	}
	public Collection getCollection() {
		return collection;
	}
	
	public int getRange() {
		return getMax()-getMin();
	}
	
	private void verifyValues() {
		if (max != null && min != null && max < min) {
			throw new IllegalArgumentException("Max value cannot be less than min value!");
		}
	}
	
	@Override
	public IDomain clone() {
		return new IDomain(min, max, collection);
	}
	public FDomain toFloatDomain() {
		return new FDomain((float)getMin(), (float)getMax());
	}
	
}
