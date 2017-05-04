package com.app.hexuegang.wsnmonitor.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by HEXG on 2017/4/9.
 */

public class parameter_air_year extends BmobObject implements Serializable{
    private String air_element_name;
    private String air_year_name;
    private float air_year_value;
    private float air_year_standard;

    public String getAir_element_name() {
        return air_element_name;
    }

    public void setAir_element_name(String air_element_name) {
        this.air_element_name = air_element_name;
    }

    public String getAir_year_name() {
        return air_year_name;
    }

    public void setAir_year_name(String air_year_name) {
        this.air_year_name = air_year_name;
    }

    public float getAir_year_value() {
        return air_year_value;
    }

    public void setAir_year_value(float air_year_value) {
        this.air_year_value = air_year_value;
    }

    public float getAir_year_standard() {
        return air_year_standard;
    }

    public void setAir_year_standard(float air_year_standard) {
        this.air_year_standard = air_year_standard;
    }
}
