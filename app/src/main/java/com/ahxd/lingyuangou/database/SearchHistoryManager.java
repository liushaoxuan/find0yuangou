package com.ahxd.lingyuangou.database;

import android.content.Context;

import com.ahxd.lingyuangou.bean.SearchHistoryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class SearchHistoryManager {

    private static SearchHistoryManager mInstance;

    private SearchHistoryDao mDao;

    private SearchHistoryManager(Context context) {
        mDao = new SearchHistoryDao(context);
    }

    public static SearchHistoryManager getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new SearchHistoryManager(context);
        }
        return mInstance;
    }

    public List<SearchHistoryBean> getAllHistory() {
        return mDao.findAllHistory();
    }

    public void deleteAllHistory() {
        mDao.deleteAllHistory();
    }

    public boolean addHistory(String history) {
        return mDao.addHistory(history);
    }
}
