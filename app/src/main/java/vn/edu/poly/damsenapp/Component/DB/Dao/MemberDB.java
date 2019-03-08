package vn.edu.poly.damsenapp.Component.DB.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import vn.edu.poly.damsenapp.Component.DB.Database.DBHelper;

public class MemberDB {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;


    public MemberDB(Context context) {
        this.context = context;
    }


    public MemberDB open() throws SQLException {
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }


    public void close() {
        dbHelper.close();
    }


    public void insert(String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper._NAME, name);
        sqLiteDatabase.insert(DBHelper._TABLE_NAME, null, contentValues);
    }

    public int update(long id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper._ID, id);
        contentValues.put(DBHelper._NAME, name);
        int i = sqLiteDatabase.update(DBHelper._TABLE_NAME, contentValues,
                DBHelper._ID + " = " + "'" + id + "'", null);
        return i;
    }

    public void delete(long id) {
        sqLiteDatabase.delete(DBHelper._TABLE_NAME, DBHelper._ID + " = " + "'" + id + "'", null);
    }

}
