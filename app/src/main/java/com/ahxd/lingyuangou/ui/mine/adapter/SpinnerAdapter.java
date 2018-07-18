package com.ahxd.lingyuangou.ui.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.ahxd.lingyuangou.R;

import java.util.List;

/**
 * Created by sxliu on 2018/7/15 18:26
 * E-mail Address 2587294424@qq.com
 */

public class SpinnerAdapter extends ArrayAdapter<String> {
    public SpinnerAdapter(@NonNull Context context, @NonNull List<String> names) {
        super(context, R.layout.item_spiner, names);
    }
}
