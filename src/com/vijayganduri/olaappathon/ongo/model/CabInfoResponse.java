package com.vijayganduri.olaappathon.ongo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CabInfoResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2976847970578070689L;

	private String status;
	
	@JsonProperty("request_type")
	private String requestType;

	@JsonProperty("cab_categories")
	private List<CabCategory> cabCategories;
	
	private List<CabLocation> cabs;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CabLocation> getCabs() {
		return cabs;
	}

	public void setCabs(List<CabLocation> cabs) {
		this.cabs = cabs;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public List<CabCategory> getCabCategories() {
		return cabCategories;
	}

	public void setCabCategories(List<CabCategory> cabCategories) {
		this.cabCategories = cabCategories;
	}

	@Override
	public String toString() {
		return "CabInfoResponse [status=" + status + ", requestType="
				+ requestType + ", cabCategories=" + cabCategories + ", cabs="
				+ cabs + "]";
	}

}
