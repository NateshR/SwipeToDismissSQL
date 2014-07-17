package com.example.swipetodismisssql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainSQL {

	public static final String key_id = "key_id";
	public static final String note = "note";

	public static final String Database_name = "database_note_main";
	public static final String Database_table = "database_table_main";
	private static final int Database_version = 8;

	private Context ourContext;
	private DBhelper ourDBhelper;
	private SQLiteDatabase ourDatabse;

	private class DBhelper extends SQLiteOpenHelper {

		public DBhelper(Context context) {
			super(context, Database_name, null, Database_version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + Database_table + " ( " + key_id
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + note
					+ " TEXT NOT NULL); ");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL(" DROP TABLE IF EXISTS " + Database_table);
			onCreate(db);
		}
	}

	public MainSQL(Context c) {
		ourContext = c;
	}

	public MainSQL Open() throws SQLException {
		ourDBhelper = new DBhelper(ourContext);
		ourDatabse = ourDBhelper.getWritableDatabase();
		return this;
	}

	public void Close() {
		ourDBhelper.close();
	}

	public long CreateEntry(String value) {
		ContentValues cv = new ContentValues();
		cv.put(note, value);
		return ourDatabse.insert(Database_table, null, cv);

	}

	public String[] getValues() {
		String[] columns = { note, key_id };
		Cursor c = ourDatabse.query(Database_table, columns, null, null, null,
				null, null);
		int get = c.getColumnIndex(note);

		int i = 0, count = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			count = count + 1;
		}
		String[] result = new String[count];
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result[i] = c.getString(get);
			i++;
		}
		return result;
	}

	public String getPosition_data(int position) {
		String[] columns = { key_id, note };
		Cursor c = ourDatabse.query(Database_table, columns, null, null, null,
				null, null);
		int get = c.getColumnIndex(note);

		int i = 0;
		int count = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			count = count + 1;
		}
		String[] result = new String[count];
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result[i] = c.getString(get);
			i++;

		}
		return result[position];
	}

	public int getID(int position) {
		String[] columns = { key_id, note };
		Cursor c = ourDatabse.query(Database_table, columns, null, null, null,
				null, null);
		int get = c.getColumnIndex(key_id);

		int i = 0;
		int count = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			count = count + 1;
		}
		int[] result = new int[count];
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result[i] = c.getInt(get);
			i++;

		}
		return result[position];
	}

	public void DeleteEntry(String name, int id) {

		ourDatabse.delete(Database_table, note + "='" + name + "'" + " AND "
				+ key_id + "='" + id + "'", null);
	}
}
