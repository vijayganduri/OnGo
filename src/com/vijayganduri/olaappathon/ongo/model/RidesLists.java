package com.vijayganduri.olaappathon.ongo.model;

import java.io.Serializable;
import java.util.List;

public class RidesLists  implements Serializable{

	private List<Ride> all;
	private List<Ride> upcoming;
	private List<Ride> completed;
	
	public List<Ride> getAll() {
		return all;
	}
	public void setAll(List<Ride> all) {
		this.all = all;
	}
	public List<Ride> getUpcoming() {
		return upcoming;
	}
	public void setUpcoming(List<Ride> upcoming) {
		this.upcoming = upcoming;
	}
	public List<Ride> getCompleted() {
		return completed;
	}
	public void setCompleted(List<Ride> completed) {
		this.completed = completed;
	}
	@Override
	public String toString() {
		return "RidesLists [all=" + all + ", upcoming=" + upcoming
				+ ", completed=" + completed + "]";
	}
	
}
