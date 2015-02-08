package com.vijayganduri.olaappathon.ongo.activity;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.RoundedImageView;
import com.vijayganduri.olaappathon.ongo.AppConstants;
import com.vijayganduri.olaappathon.ongo.R;
import com.vijayganduri.olaappathon.ongo.googleplaces.model.Place;
import com.vijayganduri.olaappathon.ongo.model.CabInfoResponse;
import com.vijayganduri.olaappathon.ongo.model.Duration;

public class PlaceDetailActivity extends BaseActivity{

	private static final String TAG = PlaceDetailActivity.class.getSimpleName();

	private RoundedImageView img;
	private Button bookBtn;
	private TextView title;
	private TextView description;
	private TextView cabsFound;
	private TextView nearestCab;
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
		bookBtn = (Button)findViewById(R.id.book);
		cabsFound = (TextView)findViewById(R.id.cabs_found);
		nearestCab = (TextView)findViewById(R.id.nearest_cab);

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


		if(cabResponse!=null & cabResponse.getCabs()!=null){
			cabsFound.setText(cabResponse.getCabs().size()+" Cabs found nearby");
		}
		try{
			if(cabResponse!=null & cabResponse.getCabCategories()!=null){
				Duration duration = cabResponse.getCabCategories().get(0).getDuration();
				nearestCab.setText(String.format("Nearest cab %s %s away",duration.getValue(),duration.getUnit()));
			}
		}catch(Exception e){//TODO remove this

		}

		bookBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try{
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.setComponent(new ComponentName("com.olacabs.customer","com.olacabs.customer.ui.SplashActivity"));
					startActivity(intent);
				}catch(Exception e){
					Toast.makeText(PlaceDetailActivity.this, "Couldn't launch Ola app", Toast.LENGTH_SHORT).show();
				}
			}
		});

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
