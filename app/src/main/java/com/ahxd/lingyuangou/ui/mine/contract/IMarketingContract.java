package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;
import com.ahxd.lingyuangou.bean.MarketingBean;
import com.ahxd.lingyuangou.bean.RecordBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface IMarketingContract {

    public interface IMarketingListView extends BaseView {
        public void showMarketingList(List<MarketingBean> list);

    }

    public interface IMarketingListPresenter {
        public void getMarketingList(int mPage);
    }

    public interface IMarketingListModel {

        public void getMarketingList(int mPage,IMarketingListCallback callback);

        public abstract class IMarketingListCallback extends ModelCallback {

            public IMarketingListCallback(BaseView view) {
                super(view);
            }

            public void onMarketingList(List<MarketingBean> list) {}

        }
    }

}
