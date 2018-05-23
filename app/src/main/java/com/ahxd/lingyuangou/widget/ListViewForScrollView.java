package com.ahxd.lingyuangou.widget;

/**
 * Created by wpc on 2018/1/17.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by http://blog.csdn.net/q610098308 on 2015/12/17.
 */
public class ListViewForScrollView extends ListView {
    public ListViewForScrollView(Context context) {
        super(context);
    }
    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ListViewForScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}