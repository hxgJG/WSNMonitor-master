package com.app.hexuegang.wsnmonitor.IListener;

import android.content.Context;

/**
 * Created by hexuegang on 2017/1/19.
 */

public interface IUserOperate {
    void register(Context context, String username, String password, String email, OnRegisterListener registerListener);
    void login(Context context, String username, String password, OnLoginListener loginListener);
}
