package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.bean.WithdrawalsAccountBean;

import java.util.ArrayList;

/**
 * Created by wpc on 2018/1/17
 */
public class WithdrawalsAccountAdapter extends BaseAdapter {

    Context context;
    ArrayList<WithdrawalsAccountBean> list;
    int i=-1;

    public WithdrawalsAccountAdapter(Context context, ArrayList<WithdrawalsAccountBean> list) {
        this.context = context;
        this.list = list;
        this.i=-1;
    }

    public void refresh(int i) {
        this.i=i;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        ViewHolder holder;
        WithdrawalsAccountBean bean=list.get(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_present_account, null);
            holder = new ViewHolder();
            holder. iv_icon= (ImageView) view.findViewById(R.id.iv_icon_zhifubao);
            holder. rb_recharge_zhifubao= (ImageView) view.findViewById(R.id.rb_recharge_zhifubao);
            holder. tv_accaddress= (TextView) view.findViewById(R.id.tv_accaddress);
            holder. tv_accno= (TextView) view.findViewById(R.id.tv_accno);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_accaddress.setText(bean.getAccAddress());
        holder.tv_accno.setText(bean.getAccNo());
        if (bean.getAccTargetId().equals("1")){
            holder.tv_accaddress.setText("支付宝账号");
            holder.iv_icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_pay_zhifubao));
        }else if (bean.getAccTargetId().equals("2")){
            holder.tv_accaddress.setText("微信支付账号");
            holder.iv_icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_pay_weixin));
        }else {
            holder.iv_icon.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_pay_wallet));
        }
        if(position==i){
            holder.rb_recharge_zhifubao.setSelected(true);
        }else {
            holder.rb_recharge_zhifubao.setSelected(false);
        }
        return view;
    }

    static class ViewHolder {
        ImageView iv_icon;
        ImageView rb_recharge_zhifubao;
        TextView tv_accaddress;
        TextView tv_accno;
    }

}