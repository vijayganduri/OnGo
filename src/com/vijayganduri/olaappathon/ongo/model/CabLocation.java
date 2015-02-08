package com.vijayganduri.olaappathon.ongo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CabLocation  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5571707778968609063L;
	private String id;
	private double lat;
	private double lng;

	@JsonProperty("category_id")
	private String category_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "CabLocation [id=" + id + ", lat=" + lat + ", lng=" + lng
				+ ", category_id=" + category_id + "]";
	}
	
	

}
