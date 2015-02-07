package com.vijayganduri.olaappathon.ongo.googleplaces.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PlacesResponse implements Serializable{
	
   	/**
	 * 
	 */
	private static final long serialVersionUID = 654726185795848625L;

	private String next_page_token;
   	
   	@JsonProperty("results")
   	private List<Place> places;

   	private String status;

 	public String getNext_page_token(){
		return this.next_page_token;
	}
	public void setNext_page_token(String next_page_token){
		this.next_page_token = next_page_token;
	}
 	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
}
