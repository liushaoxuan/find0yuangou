package com.ahxd.lingyuangou.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahxd.lingyuangou.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/5.
 */

public class PicTextRightItem extends RelativeLayout {


    @BindView(R.id.iv_pic_right_item_right)
    ImageView ivPicRightItemRight;
    @BindView(R.id.iv_pic_right_item_icon)
    ImageView ivPicRightItemIcon;
    @BindView(R.id.tv_pic_right_item_label)
    TextView tvPicRightItemLabel;
    @BindView(R.id.tv_pic_right_item_tips)
    TextView tvPicRightItemTips;

    public PicTextRightItem(Context context) {
        super(context);
    }

    public PicTextRightItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_pic_text_right, this, true);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PicTextRightItem);
        ivPicRightItemIcon.setImageResource(typedArray.getResourceId(R.styleable.PicTextRightItem_image, R.mipmap.ic_mine_item_card));
        tvPicRightItemLabel.setText(typedArray.getString(R.styleable.PicTextRightItem_label));
        tvPicRightItemTips.setVisibility(typedArray.getBoolean(R.styleable.PicTextRightItem_tipVisible, false) ? VISIBLE : GONE);
        typedArray.recycle();
    }

    public void setTipsText(String tips) {
        tvPicRightItemTips.setText(tips);
    }

    public void setLabelText(String label) { tvPicRightItemLabel.setText(label);}

}
