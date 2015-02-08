package com.vijayganduri.olaappathon.ongo.activity;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.vijayganduri.olaappathon.ongo.AppConstants;
import com.vijayganduri.olaappathon.ongo.R;
import com.vijayganduri.olaappathon.ongo.adapter.PlacesListAdapter;
import com.vijayganduri.olaappathon.ongo.googleplaces.model.Place;
import com.vijayganduri.olaappathon.ongo.googleplaces.model.PlacesResponse;
import com.vijayganduri.olaappathon.ongo.model.CabCategory;
import com.vijayganduri.olaappathon.ongo.model.CabInfoResponse;
import com.vijayganduri.olaappathon.ongo.rest.HttpJsonListener;
import com.vijayganduri.utils.PreferencesUtils;

public class HomeActivity extends BaseActivity implements OnItemClickListener{

	private static final String TAG = HomeActivity.class.getSimpleName();

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	private ListView listview;
	private PlacesListAdapter adapter;
	private CabInfoResponse cabResponse;

	private TextView sedanKms;
	private TextView sedaneat;
	private TextView cabKms;
	private TextView cabeat;
	private TextView autoKms;
	private TextView autoeat;

	private Button bookBtn;
	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		setupStatusBar();
		setupToolbar();	

		sedanKms = (TextView)findViewById(R.id.sedan_kms);
		sedaneat = (TextView)findViewById(R.id.sedan_eat);
		cabKms = (TextView)findViewById(R.id.cab_kms);
		cabeat = (TextView)findViewById(R.id.cab_eat);
		autoKms = (TextView)findViewById(R.id.auto_kms);
		autoeat = (TextView)findViewById(R.id.auto_eat);
		
		bookBtn = (Button)findViewById(R.id.book);

		listview = (ListView)findViewById(R.id.listview);
		listview.setOnItemClickListener(this);

		userId = PreferencesUtils.getUserID(this);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.nearby_cabs, R.string.app_name) {};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getplaces();
		getNearbyCabInfo();
		
		bookBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try{
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.setComponent(new ComponentName("com.olacabs.customer","com.olacabs.customer.ui.SplashActivity"));
					startActivity(intent);
				}catch(Exception e){
					Toast.makeText(HomeActivity.this, "Couldn't launch Ola app", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void getplaces(){

		adapter = new PlacesListAdapter(this);

		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(adapter);
		swingBottomInAnimationAdapter.setAbsListView(listview);

		listview.setAdapter(swingBottomInAnimationAdapter);

		//Hardcoding location to EGL
		restUtils.getPlaces("art_gallery|museum|restaurant|meal_takeaway|movie_theater", "12.949097,77.644295", "10000", new HttpJsonListener<PlacesResponse>() {

			@Override
			public void onSuccess(PlacesResponse response) {
				if(response.getPlaces()!=null){
					adapter.addAllItems(response.getPlaces());
				}
			}

			@Override
			public void onFailure(String error) {
				Log.e(TAG, error);
			}
		});
	}

	private void getNearbyCabInfo(){
		restUtils.getCabInfo(userId, "12.949097", "77.644295", new HttpJsonListener<CabInfoResponse>() {

			@Override
			public void onSuccess(CabInfoResponse response) {
				updateCabAvailabiltyData(response);
			}

			@Override
			public void onFailure(String error) {
				Log.e(TAG, error);
			}
		});
	}

	private void updateCabAvailabiltyData(CabInfoResponse response){
		this.cabResponse = response;
		if(response!=null && response.getCabCategories()!=null && response.getCabCategories().size()>0){
			for(CabCategory category : response.getCabCategories()){
				if("economy_sedan".equals(category.getId())){
					if(category.getDuration()!=null){//TODO optimise this
						sedaneat.setText(String.format("%s %s",category.getDuration().getValue(), category.getDuration().getUnit()));
					}
					if(category.getDistance()!=null){
						sedanKms.setText(String.format("%s %s",category.getDistance().getValue(), category.getDistance().getUnit()));
					}
				}else if ("local_auto".equals(category.getId())){
					if(category.getDuration()!=null){
						autoeat.setText(String.format("%s %s",category.getDuration().getValue(), category.getDuration().getUnit()));
					}
					if(category.getDistance()!=null){
						autoKms.setText(String.format("%s %s",category.getDistance().getValue(), category.getDistance().getUnit()));
					}
				}else if ("compact".equals(category.getId())){
					if(category.getDuration()!=null){
						cabeat.setText(String.format("%s %s",category.getDuration().getValue(), category.getDuration().getUnit()));
					}
					if(category.getDistance()!=null){
						cabKms.setText(String.format("%s %s",category.getDistance().getValue(), category.getDistance().getUnit()));
					}
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}else if (id == R.id.action_filter) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
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
		getSupportActionBar().setHomeButtonEnabled(true);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Place item = adapter.getItem(position);
		Intent intent = new Intent(this, PlaceDetailActivity.class);
		intent.putExtra(AppConstants.INTENT_PLACE_INFO,item);
		intent.putExtra(AppConstants.INTENT_CAB_INFO,cabResponse);
		startActivity(intent);
	}

}
