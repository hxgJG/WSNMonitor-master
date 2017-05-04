package com.app.hexuegang.wsnmonitor.IListener.operate;

import android.content.Context;

import com.app.hexuegang.wsnmonitor.IListener.IUserOperate;
import com.app.hexuegang.wsnmonitor.IListener.OnLoginListener;
import com.app.hexuegang.wsnmonitor.IListener.OnRegisterListener;
import com.app.hexuegang.wsnmonitor.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by hexuegang on 2017/1/19.
 */

public class UserOperate implements IUserOperate {
    @Override
    public void register(Context context, String username, String password, String email, final OnRegisterListener registerListener) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    registerListener.onRegisterSuccess();
                } else {
                    registerListener.onRegisterFailed();
                }
            }
        });
    }

    @Override
    public void login(Context context, String username, String password, final OnLoginListener loginListener) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    loginListener.onLoginSuccess(user);
                } else {
                    loginListener.onLoginFailed();
                }
            }
        });
    }
}
