package com.vijayganduri.olaappathon.ongo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vijayganduri.olaappathon.ongo.R;
import com.vijayganduri.olaappathon.ongo.googleplaces.model.Place;


public class PlacesListAdapter extends ArrayAdapter<Place> {

	private Context context = null;

	private LayoutInflater inflater;

	private static final String TAG = PlacesListAdapter.class.getName();

	public PlacesListAdapter(Context context) {

		super(context, android.R.layout.list_content);
		this.context = context;
		inflater     = LayoutInflater.from(context);
	}
	
	public void addAllItems(List<Place> places){
		for(Place place : places){
			super.add(place);
		}
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;

		if(convertView==null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, parent, false);
			holder.textview1    = (TextView)convertView.findViewById(R.id.item_title);
			holder.textview2    = (TextView)convertView.findViewById(R.id.item_sub_title);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}	
		
		final Place item     = (Place) getItem(position);		
		holder.textview1.setText( item.getName());
		holder.textview2.setText(item.getVicinity());
		
		return convertView;
	}

	public static class ViewHolder{
		TextView textview1;
		TextView textview2;
	}

}