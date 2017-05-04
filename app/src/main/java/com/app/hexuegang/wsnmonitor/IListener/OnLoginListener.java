package com.app.hexuegang.wsnmonitor.IListener;

import com.app.hexuegang.wsnmonitor.bean.User;

/**
 * Created by hexuegang on 2017/1/19.
 */

public interface OnLoginListener {
    void onLoginSuccess(User user);
    void onLoginFailed();
}
