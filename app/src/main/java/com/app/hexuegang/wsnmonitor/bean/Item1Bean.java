package com.app.hexuegang.wsnmonitor.bean;

import java.io.Serializable;

/**
 * Created by HEXG on 2017/4/8.
 */

public class Item1Bean implements Serializable{
    private int type;
    private String txtStr;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTxtStr() {
        return txtStr;
    }

    public void setTxtStr(String txtStr) {
        this.txtStr = txtStr;
    }
}
