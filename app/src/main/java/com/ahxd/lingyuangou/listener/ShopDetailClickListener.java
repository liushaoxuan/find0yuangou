package com.ahxd.lingyuangou.listener;

/**
 * Created by Administrator on 2018/1/4.
 */

public interface ShopDetailClickListener {

    // 电话
    public void call(String tel);

    // 收藏
    public void favorite(String targetId, boolean isFavorite);

    // 广告费
    public void showAds(String incomeRate);
}
