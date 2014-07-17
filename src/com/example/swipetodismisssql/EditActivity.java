package com.example.swipetodismisssql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class EditActivity extends Activity {

	EditText et;
	MainSQL mySQL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editactivity);
		et = (EditText) findViewById(R.id.et);
		mySQL = new MainSQL(EditActivity.this);
		mySQL.Open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.editmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.item1) {
			String get = et.getText().toString();
			Intent i = new Intent(EditActivity.this, MainActivity.class);

		
			mySQL.CreateEntry(get);
			mySQL.Close();

			startActivity(i);

		}
		return super.onOptionsItemSelected(item);

	}

}
