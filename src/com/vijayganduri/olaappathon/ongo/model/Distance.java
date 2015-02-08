package com.vijayganduri.olaappathon.ongo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Distance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7818194504456600942L;
	private String unit;
	private int value;
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Distance [unit=" + unit + ", value=" + value + "]";
	}
	
}
