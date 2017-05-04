package com.app.hexuegang.wsnmonitor.view;

import android.content.Context;

/**
 * Created by hexuegang on 2017/1/19.
 */

public interface IRegisterView {
    String getUsername();
    String getPassword();
    String getSecondPassword();
    String getEmail();
    Context getContext();
    void showLoading();
    void hideLoading();
    void success();
    void failed();
    void finishActivity();
    void ErrorOfUsnorPsdOrEmail();
    void ErrorOfConfigerPsd();
}
