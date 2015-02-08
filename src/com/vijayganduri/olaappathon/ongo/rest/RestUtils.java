package com.vijayganduri.olaappathon.ongo.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedorvlasov.lazylist.ImageLoader;
import com.makeramen.RoundedImageView;
import com.vijayganduri.olaappathon.ongo.AppConstants;
import com.vijayganduri.olaappathon.ongo.googleplaces.model.Place;
import com.vijayganduri.olaappathon.ongo.googleplaces.model.PlacesResponse;
import com.vijayganduri.olaappathon.ongo.model.CabInfoResponse;
import com.vijayganduri.olaappathon.ongo.model.LoginResponse;
import com.vijayganduri.olaappathon.ongo.model.RidesResponse;
import com.vijayganduri.utils.PasswordEncrypt;

public class RestUtils extends AbstractRestUtils {

	private static RestUtils restUtils;
	private RestClient restClient;
	private ObjectMapper mapper;

	private String baseUrl;
	private Context ctxt;
	private ImageLoader mImgLoader;

	private static final String TAG = RestUtils.class.getSimpleName();

	public static RestUtils getInstance(Context ctxt){
		if(restUtils==null){
			restUtils = new RestUtils(ctxt);
		}
		return restUtils;
	}

	private RestUtils(Context ctxt){//we make this explicitly to private, so we don't allow multiple instances 
		baseUrl = AppConstants.OLA_DEV_BASE_URL;
		this.ctxt = ctxt;
		mImgLoader = new ImageLoader(ctxt);
		restClient = RestClient.getInstance(ctxt);
		mapper = new ObjectMapper();
	}

	public void setImageUrl(RoundedImageView img, Place place){
		if(place!=null && place.getPhotos()!=null && place.getPhotos().size()>0
				&& place.getPhotos().get(0).getPhoto_reference()!=null){
			String ref = place.getPhotos().get(0).getPhoto_reference();

			List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
			list.add(new BasicNameValuePair("key", AppConstants.GOOGLE_PLACES_KEY));
			list.add(new BasicNameValuePair("photoreference",ref));
			list.add(new BasicNameValuePair("maxwidth","400"));//TODO make this dynamic

			String params = URLEncodedUtils.format(list, "utf-8");

			String url = String.format("%s%s", AppConstants.GOOGLE_PLACES_PHOTO_BASE_URL, params);

			mImgLoader.DisplayImage(url, img);
		}
	}

	public void doLogin(String email, String password, final HttpJsonListener<LoginResponse> listener){

		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair("email", email));
		list.add(new BasicNameValuePair("password",PasswordEncrypt.encrypt(password)));

		String params = URLEncodedUtils.format(list, "utf-8");

		String url = String.format("%sv3/user/login?%s", baseUrl, params);

		CustomStringRequest request = new CustomStringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					LoginResponse reponse = mapper.readValue(response, LoginResponse.class);
					listener.onSuccess(reponse);
				}catch (Exception e) {
					listener.onFailure(e.getMessage());
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				listener.onFailure(error.getMessage());
			}
		});

		restClient.addToRequestQueue(request);
	}


	@Override
	public void getRides(String userid, final HttpJsonListener<RidesResponse> listener) {

		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair("user_id", userid));
		list.add(new BasicNameValuePair("enable_new_state","true"));
		list.add(new BasicNameValuePair("enable_auto","true"));
		list.add(new BasicNameValuePair("enable_marketing","true"));

		String params = URLEncodedUtils.format(list, "utf-8");

		String url = String.format("%sv3/rides?%s", baseUrl, params);

		CustomStringRequest request = new CustomStringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					RidesResponse reponse = mapper.readValue(response, RidesResponse.class);
					listener.onSuccess(reponse);
				}catch (Exception e) {
					listener.onFailure(e.getMessage());
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				listener.onFailure(error.getMessage());
			}
		});

		restClient.addToRequestQueue(request);
	}

	@Override
	public void getCabInfo(String userid, String lat, String lng,
			final HttpJsonListener<CabInfoResponse> listener) {

		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair("user_id", userid));
		list.add(new BasicNameValuePair("enable_auto","true"));
		list.add(new BasicNameValuePair("enable_marketing","true"));

		list.add(new BasicNameValuePair("enable_new_state","true"));
		list.add(new BasicNameValuePair("location_type","CUSTOM"));
		list.add(new BasicNameValuePair("selected_by","USER"));
		list.add(new BasicNameValuePair("lat",lat));
		list.add(new BasicNameValuePair("lng",lng));
		
		String params = URLEncodedUtils.format(list, "utf-8");

		String url = String.format("%sv3/cab/info?%s", baseUrl, params);
		
		CustomStringRequest request = new CustomStringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.d(TAG, "response "+response);
				try {
					CabInfoResponse reponse = mapper.readValue(response, CabInfoResponse.class);
					listener.onSuccess(reponse);
				}catch (Exception e) {
					listener.onFailure(e.getMessage());
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.d(TAG, "err:"+error);
				listener.onFailure(error.getMessage());
			}
		});

		restClient.addToRequestQueue(request);
		
	}
	
	@Override
	public void getPlaces(String type, String location, String radius,
			final HttpJsonListener<PlacesResponse> listener) {

		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair("key", AppConstants.GOOGLE_PLACES_KEY));
		list.add(new BasicNameValuePair("type",type));
		list.add(new BasicNameValuePair("location",location));
		if(radius!=null){
			list.add(new BasicNameValuePair("radius",radius));
		}

		String params = URLEncodedUtils.format(list, "utf-8");

		String url = String.format("%s%s", AppConstants.GOOGLE_PLACES_BASE_URL, params);

		StringRequest request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					PlacesResponse reponse = mapper.readValue(response, PlacesResponse.class);
					listener.onSuccess(reponse);
				}catch (Exception e) {
					listener.onFailure(e.getMessage());
				}
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				listener.onFailure(error.getMessage());
			}
		});

		restClient.addToRequestQueue(request);
	}
}
