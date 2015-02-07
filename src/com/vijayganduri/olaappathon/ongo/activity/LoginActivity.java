package com.vijayganduri.olaappathon.ongo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vijayganduri.olaappathon.ongo.R;
import com.vijayganduri.olaappathon.ongo.model.LoginResponse;
import com.vijayganduri.olaappathon.ongo.rest.HttpJsonListener;
import com.vijayganduri.olaappathon.ongo.rest.RestUtils;
import com.vijayganduri.utils.PreferencesUtils;
import com.vijayganduri.utils.ValidatorUtils;

public class LoginActivity extends ActionBarActivity {

	private EditText emailEditText;
	private EditText passwordEditText;
	private Button loginBtn;
	private RestUtils restUtils;

	private static final String TAG = LoginActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_login);
		setupStatusBar();

		restUtils = RestUtils.getInstance(getApplicationContext());

		emailEditText = (EditText)findViewById(R.id.login_email_edit_text);
		passwordEditText = (EditText)findViewById(R.id.login_password_edit_text);
		loginBtn = (Button)findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				doLogin(emailEditText.getText().toString(), passwordEditText.getText().toString());				
			}
		});

	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void setupStatusBar(){
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	}

	private void doLogin(String email, String password){

		if(!ValidatorUtils.isValidEmail(email)){
			showToast("Enter valid email");
			return;
		}

		if(!ValidatorUtils.isValidPassword(password)){
			showToast("Enter valid password");
			return;
		}

		restUtils.doLogin(email, password, new HttpJsonListener<LoginResponse>() {

			@Override
			public void onSuccess(LoginResponse response) {
				Log.d(TAG, "onSuccess "+response);
				if("SUCCESS".equals(response.getStatus()) && response.getUserId()!=null){
					PreferencesUtils.setUserID(LoginActivity.this, response.getUserId());
					
					Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
					startActivity(intent);
					finish();//finish login
					
				}else{
					showToast("Login Failed!!!");
				}
			}

			@Override
			public void onFailure(String error) {
				Log.e(TAG, "onFailure "+error);
				showToast("Login Failed!!!");
			}
		});
	}

	private void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

}
