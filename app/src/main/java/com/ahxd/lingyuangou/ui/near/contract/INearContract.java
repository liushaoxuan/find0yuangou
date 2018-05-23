package com.ahxd.lingyuangou.ui.near.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.bean.AccredBean;
import com.ahxd.lingyuangou.bean.CatBean;
import com.ahxd.lingyuangou.bean.FoodShopBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

public interface INearContract {

    public interface INearView extends BaseView {
        public void showNearList(List<FoodShopBean> result);
        public void showCatsList(List<CatBean> result);
        public void showAccredList(List<AccredBean> result);
    }

    public interface INearPresenter {
        public void getNearList(int page,String accredId, String areaId, String order, String catId, String keywords);
        public void getCatsList();
        public void getAccredList();
    }

}
