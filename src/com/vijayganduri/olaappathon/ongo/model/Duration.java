package com.vijayganduri.olaappathon.ongo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class Duration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3692181609609106554L;
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
		return "Duration [unit=" + unit + ", value=" + value + "]";
	}

}
