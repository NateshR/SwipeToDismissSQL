package com.example.swipetodismisssql;

import com.example.android.swipedismiss.SwipeDismissListViewTouchListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

@SuppressLint("ShowToast")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv = (ListView) findViewById(R.id.lvmain);

		MainSQL mySQL = new MainSQL(MainActivity.this);
		mySQL.Open();
		String[] values = mySQL.getValues();
		mySQL.Close();
		Adapter adapter = new Adapter(this, values);
		lv.setAdapter(adapter);

		// Swipe-to-Dismiss
		SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(
				lv, new SwipeDismissListViewTouchListener.DismissCallbacks() {

					@Override
					public boolean canDismiss(int position) {
						// TODO Auto-generated method stub
						return true;
					}

					@Override
					public void onDismiss(ListView listView,
							int[] reverseSortedPositions) {
						// TODO Auto-generated method stub
						final MainSQL mySQL_del = new MainSQL(MainActivity.this);
						mySQL_del.Open();
						String[] values_before = mySQL_del.getValues();
						for (int position : reverseSortedPositions) {

							mySQL_del.DeleteEntry(values_before[position],
									mySQL_del.getID(position));

						}
						mySQL_del.Close();
						onCreate(new Bundle());

					}
				});
		lv.setOnTouchListener(touchListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.item) {
			Intent i = new Intent(MainActivity.this, EditActivity.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

}
