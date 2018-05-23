package com.ahxd.lingyuangou.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.ahxd.lingyuangou.bean.SearchHistoryBean;
import com.ahxd.lingyuangou.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class SearchHistoryDao implements ISearchHistoryDao {

    private DataBaseHelper mDBHelper;

    public SearchHistoryDao(Context context) {
        mDBHelper = new DataBaseHelper(context);
    }

    @Override
    public boolean addHistory(String history) {
        boolean flag = false;
        SQLiteDatabase database = null;
        long id = -1;
        try {
            database = mDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("content", history);
            id = database.insert(Constant.TABLE_SEARCH_HISTORY, null, values);
            flag = (id != -1 ? true : false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;
    }

    @Override
    public List<SearchHistoryBean> findAllHistory() {
        List<SearchHistoryBean> histories = new ArrayList<>();
        SQLiteDatabase database = null;
        SearchHistoryBean history = null;
        try {
            database = mDBHelper.getReadableDatabase();
            Cursor cursor = database.query(Constant.TABLE_SEARCH_HISTORY, null, null,
                    null, null, null, null);
            while (cursor.moveToNext()) {
                history = new SearchHistoryBean();
                history.setHistoryId(cursor.getString(cursor.getColumnIndex("search_id")));
                history.setContent(cursor.getString(cursor.getColumnIndex("content")));
                histories.add(history);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return histories;
    }

    @Override
    public void deleteAllHistory() {
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        database.execSQL("DELETE FROM " + Constant.TABLE_SEARCH_HISTORY + ";");
        database.close();
    }

}
