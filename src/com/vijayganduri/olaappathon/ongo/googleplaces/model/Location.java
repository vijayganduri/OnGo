package com.vijayganduri.olaappathon.ongo.googleplaces.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Location implements Serializable{
	
   	/**
	 * 
	 */
	private static final long serialVersionUID = 5215411340815820640L;
	private int lat;
   	private int lng;

 	public int getLat(){
		return this.lat;
	}
	public void setLat(int lat){
		this.lat = lat;
	}
 	public int getLng(){
		return this.lng;
	}
	public void setLng(int lng){
		this.lng = lng;
	}
}
