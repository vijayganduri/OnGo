package com.vijayganduri.olaappathon.ongo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RidesResponse {

	private String status;
	
	@JsonProperty("request_type")
	private String requestType;
	
	private RidesLists rides;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public RidesLists getRides() {
		return rides;
	}

	public void setRides(RidesLists rides) {
		this.rides = rides;
	}

	@Override
	public String toString() {
		return "RidesResponse [status=" + status + ", requestType="
				+ requestType + ", rides=" + rides + "]";
	}
	
}
