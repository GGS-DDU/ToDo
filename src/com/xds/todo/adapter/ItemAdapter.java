package com.xds.todo.adapter;

import java.util.List;

import com.xds.todo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<String> {

	private int resourceId;

	public ItemAdapter(Context context, int textViewResourceId,
			List<String> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String s = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView content = (TextView) view.findViewById(R.id.content_view);
		content.setText(s);
		return view;
	}

}
