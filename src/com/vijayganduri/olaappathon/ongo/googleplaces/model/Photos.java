package com.vijayganduri.olaappathon.ongo.googleplaces.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Photos  implements Serializable{
	
   	/**
	 * 
	 */
	private static final long serialVersionUID = 1765789687890974108L;
	private Number height;
   	private String photo_reference;
   	private Number width;

 	public Number getHeight(){
		return this.height;
	}
	public void setHeight(Number height){
		this.height = height;
	}
 	public String getPhoto_reference(){
		return this.photo_reference;
	}
	public void setPhoto_reference(String photo_reference){
		this.photo_reference = photo_reference;
	}
 	public Number getWidth(){
		return this.width;
	}
	public void setWidth(Number width){
		this.width = width;
	}
}
