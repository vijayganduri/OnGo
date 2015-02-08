package com.vijayganduri.olaappathon.ongo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class CabCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7159015368368358456L;
	private String id;
	private boolean cab_availability;
	private Duration duration;
	private Distance distance;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isCab_availability() {
		return cab_availability;
	}
	public void setCab_availability(boolean cab_availability) {
		this.cab_availability = cab_availability;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public Distance getDistance() {
		return distance;
	}
	public void setDistance(Distance distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "CabCategory [id=" + id + ", cab_availability="
				+ cab_availability + ", duration=" + duration + ", distance="
				+ distance + "]";
	}

	
}
