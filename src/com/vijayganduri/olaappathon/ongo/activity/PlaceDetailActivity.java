package com.vijayganduri.olaappathon.ongo.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.makeramen.RoundedImageView;
import com.vijayganduri.olaappathon.ongo.AppConstants;
import com.vijayganduri.olaappathon.ongo.R;
import com.vijayganduri.olaappathon.ongo.googleplaces.model.Place;
import com.vijayganduri.olaappathon.ongo.model.CabInfoResponse;

public class PlaceDetailActivity extends BaseActivity{

	private static final String TAG = PlaceDetailActivity.class.getSimpleName();
	
	private RoundedImageView img;
	private TextView title;
	private TextView description;
	private Place place;
	private CabInfoResponse cabResponse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_detail);

		setupStatusBar();
		setupToolbar();	
	
		title = (TextView)findViewById(R.id.item_title);
		description = (TextView)findViewById(R.id.item_description);
		img = (RoundedImageView)findViewById(R.id.place_img);
		
		if(getIntent()!=null && getIntent().getSerializableExtra(AppConstants.INTENT_PLACE_INFO)!=null){
			place = (Place) getIntent().getSerializableExtra(AppConstants.INTENT_PLACE_INFO);
		}else if(savedInstanceState!=null && savedInstanceState.getSerializable(AppConstants.INTENT_PLACE_INFO)!=null){
			place = (Place) savedInstanceState.getSerializable(AppConstants.INTENT_PLACE_INFO);
		}
		if(getIntent()!=null && getIntent().getSerializableExtra(AppConstants.INTENT_CAB_INFO)!=null){
			cabResponse = (CabInfoResponse) getIntent().getSerializableExtra(AppConstants.INTENT_CAB_INFO);
		}else if(savedInstanceState!=null && savedInstanceState.getSerializable(AppConstants.INTENT_CAB_INFO)!=null){
			cabResponse = (CabInfoResponse) savedInstanceState.getSerializable(AppConstants.INTENT_CAB_INFO);
		}
		
		if(place==null){
			finish();
			return;
		}
		
		title.setText(place.getName());
		description.setText(place.getVicinity());
		restUtils.setImageUrl(img,place);
	}	

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void setupStatusBar(){
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	}

	private void setupToolbar(){
		Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
		if(toolbar!=null){
			setSupportActionBar(toolbar);
		}

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();		
		switch (id) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable(AppConstants.INTENT_PLACE_INFO, place);
		super.onSaveInstanceState(outState);		
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		place = (Place)savedInstanceState.getSerializable(AppConstants.INTENT_PLACE_INFO);
	}
	
}
