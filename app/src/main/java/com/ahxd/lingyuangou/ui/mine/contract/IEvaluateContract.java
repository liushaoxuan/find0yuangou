package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IEvaluateContract {

    public interface IEvaluateView extends BaseView {
        public void showSubmitResult(String msg);
    }

    public interface IEvaluatePresenter {
        // 提交评价
        public void submitEvaluate(String orderId, String shopId, String goodsId, String content, String goodsScore,
                                   String serviceScore, String timeScore);
    }

    public interface IEvaluateModel {

        public void submitEvaluate(String orderId, String shopId, String goodsId, String content, String goodsScore,
                                   String serviceScore, String timeScore, IEvaluateCallback callback);

        public abstract class IEvaluateCallback extends ModelCallback {

            public IEvaluateCallback(BaseView view) {
                super(view);
            }

            public void onSubmitEvaluate(String msg) {}

        }
    }

}
