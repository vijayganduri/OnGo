package com.vijayganduri.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.vijayganduri.olaappathon.ongo.AppConstants;


/**
 * Shared Preferences Helper
 * @author Vijay Ganduri
 *
 */
public class PreferencesUtils {

	public static SharedPreferences getPreferences(Context context){		
		SharedPreferences prefs = context.getSharedPreferences(AppConstants.SHARED_PREFS_FILE, ContextWrapper.MODE_PRIVATE);		
		return prefs;
	}

	/////////////////////
	//    GETTERS
	/////////////////////
	public static String getUserID(Context context){		
		return getPreferences(context).getString(AppConstants.PREFS_USER_ID, null);
	}

	///////////////////
	//    SETTERS
	//////////////////
	public static void setUserID(Context context, String value){
		final Editor editor = getPreferences(context).edit();
		editor.putString(AppConstants.PREFS_USER_ID, value).commit();
	}

}