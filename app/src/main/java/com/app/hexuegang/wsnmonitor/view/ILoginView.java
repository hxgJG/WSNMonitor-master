package com.app.hexuegang.wsnmonitor.view;

import android.content.Context;

/**
 * Created by hexuegang on 2017/1/19.
 */

public interface ILoginView {
    String getUsername();
    String getPassword();
    void showLoading();
    void hideLoading();
    void gotoHomeActivity();
    void showFailedError();
    Context getContext();
    void ErrorOfUserAndPsd();
}
