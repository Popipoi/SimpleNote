package com.example.jin.simplenote;

/**
 * Created by Jin on 2014-12-21.
 */

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {
    private static final String DB_NAME = "promise2.db";
    private static final int VERSION = 1;
    private static final String ID = "_id";
    private static final String MEETING = "meeting";
    private static final String TIME = "time";

    private static final String TABLE_NAME = "promise";
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " integer primary key autoincrement, " +
                    MEETING + " text not null, " +
                    TIME + " text not null )";

    private SQLiteDatabase db;

    public DBAdapter(Context context) {
        super(context, DB_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public synchronized void close() {
        db.close();
        super.close();
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // create
    public boolean insertInfo(String meeting, String time) {
        ContentValues cv = new ContentValues();
        cv.put(MEETING, meeting);
        cv.put(TIME, time);
        return db.insert(TABLE_NAME, null, cv) != -1;
    }

    // read
    public ArrayList<Info> getAllInfo() {
        ArrayList<Info> info = new ArrayList<Info>();
        Cursor c = db.query(TABLE_NAME, new String[] {ID, MEETING, TIME}, null, null, null, null, ID + " DESC");

        if (c.moveToFirst()) {
            final int indexId = c.getColumnIndex(ID);
            final int indexMeeting = c.getColumnIndex(MEETING);
            final int indexTime = c.getColumnIndex(TIME);

            do {
                int id = c.getInt(indexId);
                String meeting = c.getString(indexMeeting);
                String time = c.getString(indexTime);
                info.add(new Info(id, meeting, time));
            } while (c.moveToNext());
        }
        c.close();

        return info;
    }

    // update
    public boolean updateInfo(Info i) {
        ContentValues cv = new ContentValues();
        cv.put(MEETING, i.getMeeting());
        cv.put(TIME, i.getTime());
        String[] params = new String[] { Integer.toString(i.getId()) };
        int result = db.update(TABLE_NAME, cv, ID + "=?", params);
        return result > 0;
    }

    // delete
    public boolean deleteInfo(int id) {
        String[] params = new String[] { Integer.toString(id) };
        int result = db.delete(TABLE_NAME, ID + "=?", params);
        return result > 0;
    }

    public boolean deleteAll() {
        int result=db.delete(TABLE_NAME, null, null);
        return result > 0;
    }
}
