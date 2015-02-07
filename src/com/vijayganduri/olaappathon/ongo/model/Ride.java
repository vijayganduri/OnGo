package com.vijayganduri.olaappathon.ongo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ride  implements Serializable{

	private String id;
	private long crn;
	private String status;

	@JsonProperty("pickup_time")
	private long pickupTime;

	@JsonProperty("pickup_address")
	private String pickupAddress;

	@JsonProperty("service_city")
	private String serviceCity;

	@JsonProperty("car_category")
	private String carCategory;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCrn() {
		return crn;
	}

	public void setCrn(long crn) {
		this.crn = crn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(long pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public String getServiceCity() {
		return serviceCity;
	}

	public void setServiceCity(String serviceCity) {
		this.serviceCity = serviceCity;
	}

	public String getCarCategory() {
		return carCategory;
	}

	public void setCarCategory(String carCategory) {
		this.carCategory = carCategory;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", crn=" + crn + ", status=" + status
				+ ", pickupTime=" + pickupTime + ", pickupAddress="
				+ pickupAddress + ", serviceCity=" + serviceCity
				+ ", carCategory=" + carCategory + "]";
	}

}
