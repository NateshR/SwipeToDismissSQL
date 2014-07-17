package com.example.swipetodismisssql;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;

	public Adapter(Context context, String[] values) {
		super(context, R.layout.text, values);
		this.context = context;
		this.values = values;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		@SuppressWarnings("static-access")
		LayoutInflater inflator = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflator.inflate(R.layout.text, parent, false);
		TextView tv = (TextView) rowView.findViewById(R.id.tv);
		tv.setText(values[position]);
		tv.setMinHeight(100);
		return rowView;
	}

}
