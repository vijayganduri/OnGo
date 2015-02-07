package com.vijayganduri.olaappathon.ongo.googleplaces.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Place implements Serializable{
	
   	/**
	 * 
	 */
	private static final long serialVersionUID = -8391299254996808011L;
	private Geometry geometry;
   	private String icon;
   	private String id;
   	private String name;
   	private List<Photos> photos;
   	private String place_id;
   	private String reference;
   	private String scope;
   	private String vicinity;

 	public Geometry getGeometry(){
		return this.geometry;
	}
	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}
 	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public List<Photos> getPhotos(){
		return this.photos;
	}
	public void setPhotos(List<Photos> photos){
		this.photos = photos;
	}
 	public String getPlace_id(){
		return this.place_id;
	}
	public void setPlace_id(String place_id){
		this.place_id = place_id;
	}
 	public String getReference(){
		return this.reference;
	}
	public void setReference(String reference){
		this.reference = reference;
	}
 	public String getScope(){
		return this.scope;
	}
	public void setScope(String scope){
		this.scope = scope;
	} 	
 	public String getVicinity(){
		return this.vicinity;
	}
	public void setVicinity(String vicinity){
		this.vicinity = vicinity;
	}
}
