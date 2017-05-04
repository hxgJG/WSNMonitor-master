package com.app.hexuegang.wsnmonitor.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by HEXG on 2017/4/9.
 */

public class parameter_noise extends BmobObject implements Serializable{
    private String noise_name;
    private int noise_time_type;
    private float noise_value;
    private float noise_standard;

    public String getNoise_name() {
        return noise_name;
    }

    public void setNoise_name(String noise_name) {
        this.noise_name = noise_name;
    }

    public int getNoise_time_type() {
        return noise_time_type;
    }

    public void setNoise_time_type(int noise_time_type) {
        this.noise_time_type = noise_time_type;
    }

    public float getNoise_value() {
        return noise_value;
    }

    public void setNoise_value(float noise_value) {
        this.noise_value = noise_value;
    }

    public float getNoise_standard() {
        return noise_standard;
    }

    public void setNoise_standard(float noise_standard) {
        this.noise_standard = noise_standard;
    }
}
