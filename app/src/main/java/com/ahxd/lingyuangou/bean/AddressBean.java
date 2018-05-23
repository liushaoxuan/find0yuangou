package com.ahxd.lingyuangou.bean;

import java.io.Serializable;

/**
 * Created by Mao Zhendong on 2018/1/12.
 */

public class AddressBean implements Serializable {

    /**
     * province : 安徽省
     * city : 合肥市
     * area : 包河区
     * userName : 张三1
     * userPhone : 13856998694
     * userAddress : 安徽省阜阳市临泉县安徽省阜阳市临泉县安徽临泉工业园区A
     * addressId : 7
     * isDefault : 1
     */

    private String province;
    private String city;
    private String area;
    private String userName;
    private String userPhone;
    private String userAddress;
    private String addressId;
    private int isDefault;
    private String provinceId;
    private String cityId;
    private String areaId;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
