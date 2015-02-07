package com.vijayganduri.olaappathon.ongo.rest;


public interface HttpJsonListener<T>{
		
	public abstract void onSuccess(T t);
	
	public abstract void onFailure(String error);
	
}
