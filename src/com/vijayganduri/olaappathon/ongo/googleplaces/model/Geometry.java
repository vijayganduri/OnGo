package com.vijayganduri.olaappathon.ongo.googleplaces.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Geometry implements Serializable{
	
   	/**
	 * 
	 */
	private static final long serialVersionUID = 4196045788251030422L;
	private Location location;

 	public Location getLocation(){
		return this.location;
	}
	public void setLocation(Location location){
		this.location = location;
	}
}
