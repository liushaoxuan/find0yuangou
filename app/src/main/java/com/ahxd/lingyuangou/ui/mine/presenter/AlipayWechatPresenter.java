package com.ahxd.lingyuangou.ui.mine.presenter;

import com.ahxd.lingyuangou.ui.mine.contract.IAlipayWechatContract;
import com.ahxd.lingyuangou.ui.mine.contract.IWalletContract;
import com.ahxd.lingyuangou.ui.mine.model.AlipayWechatModel;
import com.ahxd.lingyuangou.ui.mine.model.WalletModel;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/15.
 */
public class AlipayWechatPresenter implements IAlipayWechatContract.IAlipayWechatPresenter{

    private IAlipayWechatContract.IAlipayWechatView mView;
    private AlipayWechatModel mModel;

    public AlipayWechatPresenter(IAlipayWechatContract.IAlipayWechatView view) {
        this.mView = view;
        mModel = new AlipayWechatModel();
    }

    @Override
    public void setAlipayWechat(String id, String accTargetId, String accNo, String userName, String userPhone, String userNumber) {
        mModel.setAlipayWechat(id,accTargetId,accNo,userName,userPhone,userNumber,new IAlipayWechatContract.IAlipayWechatModel.IAlipayWechatCallback(mView) {
            @Override
            public void onAlipayWechat(JSONObject data) {
                mView.showAlipayWechat(data);
            }
        });
    }
}
