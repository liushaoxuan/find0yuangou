package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IEvaluateContract;
import com.ahxd.lingyuangou.ui.mine.model.EvaluateModel;

/**
 * Created by Administrator on 2018/1/16.
 */

public class EvaluatePresenter implements IEvaluateContract.IEvaluatePresenter {

    private IEvaluateContract.IEvaluateView mView;
    private IEvaluateContract.IEvaluateModel mModel;

    public EvaluatePresenter(IEvaluateContract.IEvaluateView view) {
        this.mView = view;
        mModel = new EvaluateModel();
    }

    @Override
    public void submitEvaluate(String orderId, String shopId, String goodsId, String content, String goodsScore,
                               String serviceScore, String timeScore) {
        mModel.submitEvaluate(orderId, shopId, goodsId, content, goodsScore, serviceScore, timeScore,
                new IEvaluateContract.IEvaluateModel.IEvaluateCallback(mView) {
                    @Override
                    public void onSubmitEvaluate(String msg) {
                        mView.showSubmitResult(msg);
                    }
                });
    }
}
