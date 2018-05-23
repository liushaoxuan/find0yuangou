package com.ahxd.lingyuangou.database;


import com.ahxd.lingyuangou.bean.SearchHistoryBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public interface ISearchHistoryDao {

    public boolean addHistory(String history);

    public List<SearchHistoryBean> findAllHistory();

    public void deleteAllHistory();

}
