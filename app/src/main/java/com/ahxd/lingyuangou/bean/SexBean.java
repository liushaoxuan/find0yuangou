package com.ahxd.lingyuangou.bean;

/**
 * Created by Administrator on 2017/11/16.
 */

public class SexBean {

    private String sex;
    private int type;

    public SexBean(String sex, int type) {
        this.sex = sex;
        this.type = type;
    }

    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
