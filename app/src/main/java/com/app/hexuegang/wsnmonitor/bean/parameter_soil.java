package com.app.hexuegang.wsnmonitor.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by HEXG on 2017/4/9.
 */

public class parameter_soil extends BmobObject implements Serializable{
    private String soil_name;
    private String element_name;
    private float soil_value_bg;
    private float soil_value;
    private float soil_standard;

    public String getSoil_name() {
        return soil_name;
    }

    public void setSoil_name(String soil_name) {
        this.soil_name = soil_name;
    }

    public String getElement_name() {
        return element_name;
    }

    public void setElement_name(String element_name) {
        this.element_name = element_name;
    }

    public float getSoil_value_bg() {
        return soil_value_bg;
    }

    public void setSoil_value_bg(float soil_value_bg) {
        this.soil_value_bg = soil_value_bg;
    }

    public float getSoil_value() {
        return soil_value;
    }

    public void setSoil_value(float soil_value) {
        this.soil_value = soil_value;
    }

    public float getSoil_standard() {
        return soil_standard;
    }

    public void setSoil_standard(float soil_standard) {
        this.soil_standard = soil_standard;
    }
}
