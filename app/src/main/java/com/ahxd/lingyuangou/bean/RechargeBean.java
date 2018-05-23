package com.ahxd.lingyuangou.bean;

/**
 * Created by Administrator on 2018/1/9.
 */

public class RechargeBean {


    /**
     * configId : 86
     * fieldName : 充值100
     * fieldValue : 100
     * description : null
     */

    private boolean isChecked = false;
    private String configId;
    private String fieldName;
    private String fieldValue;
    private String description;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
