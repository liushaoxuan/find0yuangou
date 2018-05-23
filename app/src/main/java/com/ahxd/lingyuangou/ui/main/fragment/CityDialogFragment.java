package com.ahxd.lingyuangou.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.ahxd.lingyuangou.R;
import com.ahxd.lingyuangou.ui.home.adapter.DialogChoseCityAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sxliu on 2018/5/19 14:35
 * E-mail Address 2587294424@qq.com
 * 首页城市选择dialogfeagment
 */

public class CityDialogFragment extends DialogFragment {

    private DialogChoseCityAdapter adapter;

    private List<String> citys;
    RecyclerView recyclerView;

    private View rootView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        citys = new ArrayList<>();
        initData();
        adapter = new DialogChoseCityAdapter(getActivity(),citys);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//设置窗口无标题
        if (rootView==null){
            rootView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_city_chose,container,false);
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }
    private void initData(){
        citys.add("合肥");
        citys.add("南京");
        citys.add("上海");
    }
}
