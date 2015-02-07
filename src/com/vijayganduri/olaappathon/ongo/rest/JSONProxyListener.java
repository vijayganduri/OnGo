package com.vijayganduri.olaappathon.ongo.rest;

import java.io.IOException;

import com.android.volley.Response.Listener;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijayganduri.olaappathon.ongo.model.LoginResponse;

public abstract class JSONProxyListener implements Listener<String>{
	
	
	static final String TAG = JSONProxyListener.class.getSimpleName();
	
	static ObjectMapper mapper;

	static{
		if(mapper==null){
			mapper = new ObjectMapper();
//			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);	
		}	
	}
	
	@Override
	public void onResponse(String response) {
		
		try {
			LoginResponse reponse = mapper.readValue(response, LoginResponse.class);
			onJSONResponse(reponse);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public abstract void onJSONResponse(LoginResponse response);
	
}
