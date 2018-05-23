package com.ahxd.lingyuangou.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.base.BaseActivity;
import com.ahxd.lingyuangou.ui.mine.contract.IMarketingApplyContract;
import com.ahxd.lingyuangou.ui.mine.contract.IMineContactContract;
import com.ahxd.lingyuangou.ui.mine.presenter.MarketingApplyPresenter;
import com.ahxd.lingyuangou.ui.mine.presenter.MineContactPresenter;
import com.ahxd.lingyuangou.widget.richtext.RichText;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wpc on 2018/1/18.
 */

public class MineContactActivity extends BaseActivity implements IMineContactContract.IMineContactView {


    @BindView(R.id.rt_mine_contact)
    RichText rtMineContact;

    private MineContactPresenter mPresenter;
    @Override
    protected void initView() {
        super.initView();
        setToolBarTitle("联系我们");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter = new MineContactPresenter(this);
        mPresenter.getMineContact();
    }
    @Override
    protected void refreshData() {
        super.refreshData();
        mPresenter.getMineContact();
    }
    @Override
    protected int getLayoutId() {
        return  R.layout.activity_mine_contact;
    }


    @Override
    public void showMineContact(JSONObject data) {
        if (null != data) {
            setToolBarTitle(data.optString("articleTitle"));
            rtMineContact.setRichText(data.optString("articleContent"));
        }

    }
}
