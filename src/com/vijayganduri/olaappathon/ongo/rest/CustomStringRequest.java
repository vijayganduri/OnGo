package com.vijayganduri.olaappathon.ongo.rest;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.vijayganduri.olaappathon.ongo.AppConstants;

public class CustomStringRequest extends StringRequest{

	private static Map<String, String> customHeaders;

	public CustomStringRequest(int method, String url,
			Listener<String> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
		// TODO Auto-generated constructor stub
	}

	public CustomStringRequest(String url,
			Listener<String> listener, ErrorListener errorListener) {
		super(url, listener, errorListener);
		// TODO Auto-generated constructor stub
	}	

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return getCustomHeaders();
	}

	private Map<String, String> getCustomHeaders() throws AuthFailureError{
		if(customHeaders==null){
			customHeaders = new HashMap<String, String>();  
			customHeaders.put("client", AppConstants.OLA_CLIENT_KEY);
			customHeaders.put("api-key", AppConstants.OLA_PRIVATE_API_KEY);
			customHeaders.put("enable_auto", "true");
		}

		return customHeaders;
	}

}