package com.vijayganduri.olaappathon.ongo.rest;

import com.vijayganduri.olaappathon.ongo.googleplaces.model.PlacesResponse;
import com.vijayganduri.olaappathon.ongo.model.CabInfoResponse;
import com.vijayganduri.olaappathon.ongo.model.LoginResponse;
import com.vijayganduri.olaappathon.ongo.model.RidesResponse;

public abstract class AbstractRestUtils {

	//GOOGLE PLACES API
	public abstract void getPlaces(String type, String location, String radius, HttpJsonListener<PlacesResponse> listener);
	
	//OLA REST APIs
	public abstract void doLogin(String email, String password, HttpJsonListener<LoginResponse> listener);

	public abstract void getRides(String userid, HttpJsonListener<RidesResponse> listener);
	
	public abstract void getCabInfo(String userid, String lat, String lng, HttpJsonListener<CabInfoResponse> listener);

}
