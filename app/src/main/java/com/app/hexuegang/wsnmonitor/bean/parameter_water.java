package com.app.hexuegang.wsnmonitor.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by HEXG on 2017/4/9.
 */

public class parameter_water extends BmobObject implements Serializable{
    private String element_name;
    private String name;
    private String image_url;
    private float element_value;
    private float element_standard_1;
    private int groupId;
//    private int type = (int) (Math.random()*100);


    public parameter_water() {
    }

    public String getElement_name() {
        return element_name;
    }

    public void setElement_name(String element_name) {
        this.element_name = element_name;
    }

    public float getElement_value() {
        return element_value;
    }

    public void setElement_value(float element_value) {
        this.element_value = element_value;
    }

    public float getElement_standard_1() {
        return element_standard_1;
    }

    public void setElement_standard_1(float element_standard_1) {
        this.element_standard_1 = element_standard_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
