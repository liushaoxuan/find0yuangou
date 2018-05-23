package com.ahxd.lingyuangou.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ahxd.lingyuangou.constant.Constant;

/**
 * Created by Administrator on 2017/11/14.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final String SQL_CREATE_TABLE_SEARCH_HISTORY = "CREATE TABLE IF NOT EXISTS "
            + Constant.TABLE_SEARCH_HISTORY + " (search_id integer primary key autoincrement, content varchar(50))";

    private static final String SQL_DELETE_TABLE_SEARCH_HISTORY = "DROP TABLE " + Constant.TABLE_SEARCH_HISTORY;

    public DataBaseHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_SEARCH_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(SQL_DELETE_TABLE_SEARCH_HISTORY);
            db.execSQL(SQL_CREATE_TABLE_SEARCH_HISTORY);
        }
    }
}
