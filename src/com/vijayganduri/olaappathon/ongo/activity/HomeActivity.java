package com.vijayganduri.olaappathon.ongo.activity;

import android.annotation.TargetApi;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.vijayganduri.olaappathon.ongo.AppConstants;
import com.vijayganduri.olaappathon.ongo.R;
import com.vijayganduri.olaappathon.ongo.adapter.PlacesListAdapter;
import com.vijayganduri.olaappathon.ongo.googleplaces.model.Place;
import com.vijayganduri.olaappathon.ongo.googleplaces.model.PlacesResponse;
import com.vijayganduri.olaappathon.ongo.rest.HttpJsonListener;

public class HomeActivity extends BaseActivity implements OnItemClickListener{

	private static final String TAG = HomeActivity.class.getSimpleName();

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	private ListView listview;
	private PlacesListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		setupStatusBar();
		setupToolbar();	
		
		listview = (ListView)findViewById(R.id.listview);
		listview.setOnItemClickListener(this);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getplaces();
	}

	private void getplaces(){

		adapter = new PlacesListAdapter(this);

		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(adapter);
		swingBottomInAnimationAdapter.setAbsListView(listview);

		listview.setAdapter(swingBottomInAnimationAdapter);

		restUtils.getPlaces("restaurant", "17.48,78.38", "3000", new HttpJsonListener<PlacesResponse>() {

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
		startActivity(intent);
	}

}