package com.vijayganduri.olaappathon.ongo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.vijayganduri.olaappathon.ongo.AppConstants;
import com.vijayganduri.olaappathon.ongo.MainApplication;
import com.vijayganduri.olaappathon.ongo.rest.RestUtils;
import com.vijayganduri.utils.PreferencesUtils;

public class BaseActivity extends ActionBarActivity {

	MainApplication app;
	SharedPreferences prefs;
	RestUtils restUtils;
	TextView title;
	private static final String TAG = BaseActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();

		if(!isLoggedIn()){
			Intent intent = new Intent(this, LoginActivity.class);
			finish();//finish current proxy
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}

	}

	private void init(){
		if(app==null){
			app = (MainApplication)getApplicationContext();
		}
		if(prefs==null){
			prefs = PreferencesUtils.getPreferences(this);
		}

		restUtils = RestUtils.getInstance(getApplicationContext());

	}


	protected boolean isLoggedIn(){		
		return prefs.getString(AppConstants.PREFS_USER_ID, null)!=null;
	}

	private void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

}