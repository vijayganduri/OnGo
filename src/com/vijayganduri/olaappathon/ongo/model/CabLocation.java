package com.vijayganduri.olaappathon.ongo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CabLocation  implements Serializable{

	private String id;
	private float lat;
	private float lng;

	@JsonProperty("category_id")
	private String category_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
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
