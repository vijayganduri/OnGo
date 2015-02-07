package com.vijayganduri.olaappathon.ongo;

import android.app.Application;
import android.content.SharedPreferences;

import com.vijayganduri.utils.PreferencesUtils;


public class MainApplication extends Application {

	private static final String TAG = MainApplication.class.getName();
	
	private SharedPreferences sharedPrefs;
	
	@Override
	public void onCreate() {
		super.onCreate();
	}	
	
	public SharedPreferences getSharedPreferences(){
		if(sharedPrefs==null){
			sharedPrefs = PreferencesUtils.getPreferences(this);
		}
		return sharedPrefs;
	}

}