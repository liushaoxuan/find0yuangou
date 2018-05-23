package com.ahxd.lingyuangou.ui.home.presenter;

import com.ahxd.lingyuangou.bean.ExchangeBean;
import com.ahxd.lingyuangou.bean.HomeCatBean;
import com.ahxd.lingyuangou.bean.HomeFinanceBean;
import com.ahxd.lingyuangou.bean.HomeFoodShopBean;
import com.ahxd.lingyuangou.bean.HomeGiftBean;
import com.ahxd.lingyuangou.bean.HomeGoodBean;
import com.ahxd.lingyuangou.ui.home.contract.IExchangeGiftContract;
import com.ahxd.lingyuangou.ui.home.contract.IHomeContract;
import com.ahxd.lingyuangou.ui.home.model.ExchangeGiftModel;
import com.ahxd.lingyuangou.ui.home.model.HomeModel;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class ExchangeGiftPresenter implements IExchangeGiftContract.IExchangeGiftPresenter {

    private IExchangeGiftContract.IExchangeGiftView mView;
    private IExchangeGiftContract.IExchangeGiftModel mModel;

    public ExchangeGiftPresenter(IExchangeGiftContract.IExchangeGiftView view) {
        this.mView = view;
        mModel = new ExchangeGiftModel();
    }

    @Override
    public void getHomeData(int page) {
        mModel.getExchangeGiftData(page,new IExchangeGiftContract.IExchangeGiftModel.IExchangeGiftCallback(mView) {
            @Override
            public void onBannerData(ExchangeBean banner) {
                mView.showBanner(banner);
            }

            @Override
            public void onCatData(ExchangeBean cats) {
                mView.showCats(cats);
            }

            @Override
            public void onHotData(ExchangeBean hots) {
                mView.showHots(hots);
            }
        });
    }
}
