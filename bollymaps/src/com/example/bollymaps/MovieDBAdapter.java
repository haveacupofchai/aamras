package com.example.bollymaps;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/* Wrapper class for the database
 * */
public class MovieDBAdapter extends SQLiteOpenHelper {

	private String DATABASE_PATH = "/data/data/com.example.bollymaps/databases/";
	public static final String DATABASE_NAME = "movieDB";

	private static SQLiteDatabase mDb;

	private final Context mContext;

	private static boolean mCreateDatabase = false;
	private boolean mUpgradeDatabase = false;

    public static final String COL_ROWID = "_id";
    public static final String COL_YEAR = "Year";
    public static final String COL_NAME = "Name";
    public static final String COL_ACTOR = "Actor";
    public static final String COL_ACTRESS = "Actress";
    public static final String COL_DIRECTOR = "Director";
    public static final String COL_LATITUDE = "Latitude";
    public static final String COL_LONGITUDE = "Longitude";
    public static final String COL_LOCATION = "MovieLocation";
    
    public static final String DATABASE_TABLE = "Movie";
	/**
	 * Constructor
	 * Takes and keeps a reference of the passed context in order to access
	 * the application's assets and resources
	 * @param context
	 */
	public MovieDBAdapter(Context context) {
		super(context, DATABASE_NAME, null, 1);

		mContext = context;
	}

	public void initializeDatabase(String path) {	
		DATABASE_PATH = path;
		getWritableDatabase();

		if(mUpgradeDatabase) {
			mContext.deleteDatabase(DATABASE_NAME);
		}

		if(mCreateDatabase || mUpgradeDatabase) {
			try {
				copyDatabase();
			} catch (IOException e) {
				throw new Error("Error Copying Database");
			}
		}
	}

	private void copyDatabase() throws IOException {
		close();

		InputStream input = mContext.getAssets().open(DATABASE_NAME);

		String outFileName = DATABASE_PATH + DATABASE_NAME;

		OutputStream output = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = input.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}

		output.flush();
		output.close();
		input.close();

		getWritableDatabase().close();
	}
	
	public Cursor GetCursor(String[] columns, String selection, String[] selectionArgs, String groupBy, String having)
	{
		Cursor c = mDb.query(DATABASE_TABLE, columns, selection, selectionArgs, groupBy, having, null);
		return c;		
	}
	
	public ArrayList<String> GetArrayList(String name, String[] columns, String selection, String[] selectionArgs, String groupBy, String having)
	{
		ArrayList<String> tempStr = new ArrayList<String>();
		Cursor c = mDb.query(DATABASE_TABLE, columns, selection, selectionArgs, groupBy, having, null);
	      if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String in = c.getString(c.getColumnIndex(name));
	    				tempStr.add(in);
	    			}while (c.moveToNext());
	    		} 
	      }
		return tempStr;		
	}
	
	public MovieDBAdapter open() throws SQLException {
		mDb = getReadableDatabase();
		return this;
	}

	public void CleanUp() {
		mDb.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		mCreateDatabase = true;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		mUpgradeDatabase = true;
	}

	
}