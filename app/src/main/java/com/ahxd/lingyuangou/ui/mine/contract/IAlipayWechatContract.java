package com.ahxd.lingyuangou.ui.mine.contract;

import com.ahxd.lingyuangou.base.BaseView;
import com.ahxd.lingyuangou.base.ModelCallback;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface IAlipayWechatContract {

    public interface IAlipayWechatView extends BaseView {
        public void showAlipayWechat(JSONObject data);
    }

    public interface IAlipayWechatPresenter {

        public void setAlipayWechat(String id,String accTargetId,String accNo,String userName,
                                    String userPhone,String userNumber);
    }

    public interface IAlipayWechatModel {

        public void setAlipayWechat(String id, String accTargetId, String accNo,String userName,
                                    String userPhone, String userNumber,IAlipayWechatCallback callback);

        public abstract class IAlipayWechatCallback extends ModelCallback {

            public IAlipayWechatCallback(BaseView view) {
                super(view);
            }

            public void onAlipayWechat(JSONObject data) {}
        }

    }


}
