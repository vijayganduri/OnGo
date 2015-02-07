package com.vijayganduri.olaappathon.ongo.rest;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class RestClient {

	private static final String TAG = RestClient.class.getSimpleName();
	private static final String ID_TAG = "request_id";

	private static RestClient restClient;
	
	private Context context;

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	public RestClient(Context context){
		this.context = context;
	}
	
	public static RestClient getInstance(Context context){
		if(restClient==null){
			restClient = new RestClient(context);
		}
		return restClient;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(context);
		}

		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mRequestQueue,
					new ImageLoader.ImageCache() {
				private final LruCache<String, Bitmap>
				cache = new LruCache<String, Bitmap>(100);

				@Override
				public Bitmap getBitmap(String url) {
					return cache.get(url);
				}

				@Override
				public void putBitmap(String url, Bitmap bitmap) {
					cache.put(url, bitmap);
				}
			});
		}
		return this.mImageLoader;
	}
	
	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? ID_TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(ID_TAG);
		getRequestQueue().add(req);
	}

	public void cancelDefaultPendingRequests() {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(ID_TAG);
		}
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

}
