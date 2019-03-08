package vn.edu.poly.damsenapp.Component.DB.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String _TABLE_NAME = "table_name";
    public static final String _ID = "id";
    public static final String _NAME = "name";

    public static final String DB_NAME = "MANAMENT5.DB";
    public static final int DB_VERSION = 1;

    public static final String _CREATE_TABLE = "create table " + _TABLE_NAME + "("
            + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + _NAME
            + " TEXT)";

    public DBHelper(@Nullable @android.support.annotation.Nullable Context context, @Nullable @android.support.annotation.Nullable String name, @Nullable @android.support.annotation.Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + _TABLE_NAME);
        onCreate(db);
    }


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void QueryData(String sql) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);
    }
}
