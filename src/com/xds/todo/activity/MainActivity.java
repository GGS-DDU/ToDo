package com.xds.todo.activity;

import java.util.ArrayList;
import java.util.List;
import com.xds.todo.R;
import com.xds.todo.adapter.ItemAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity{
	
	private ListView mylistvView;
	private ItemAdapter mAdapter;
	private List<String> data = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mylistvView = (ListView) findViewById(R.id.mylistview);
		mAdapter = new ItemAdapter(MainActivity.this, R.layout.mylayoutview1, data);
		data.add("a");
		data.add("b");
		data.add("c");
		data.add("d");
		data.add("e");
		data.add("f");
		data.add("g");
		data.add("h");
		data.add("i");
		data.add("j");
		data.add("k");
		data.add("l");
		data.add("m");
		data.add("n");
		mylistvView.setAdapter(mAdapter);
	}

}
