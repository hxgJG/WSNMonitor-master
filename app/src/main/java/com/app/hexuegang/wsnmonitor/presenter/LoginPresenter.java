package com.app.hexuegang.wsnmonitor.presenter;

import android.content.Context;
import android.content.Intent;

import com.app.hexuegang.wsnmonitor.IListener.IUserOperate;
import com.app.hexuegang.wsnmonitor.IListener.OnLoginListener;
import com.app.hexuegang.wsnmonitor.IListener.operate.UserOperate;
import com.app.hexuegang.wsnmonitor.MainActivity;
import com.app.hexuegang.wsnmonitor.bean.User;
import com.app.hexuegang.wsnmonitor.view.ILoginView;
import com.app.hexuegang.wsnmonitor.view.RegisterActivity;

/**
 * Created by hexuegang on 2017/1/19.
 */

public class LoginPresenter {
    private IUserOperate mIUserOperate;
    private ILoginView mILoginView;
    private Context mContext;

    public LoginPresenter(Context context, ILoginView mILoginView){
        this.mContext = context;
        this.mILoginView = mILoginView;
        mIUserOperate = new UserOperate();
    }

    public void login(){
        mILoginView.showLoading();
        mIUserOperate.login(mContext, mILoginView.getUsername(), mILoginView.getPassword(), new OnLoginListener() {
            @Override
            public void onLoginSuccess(User user) {
                mILoginView.hideLoading();
                mILoginView.gotoHomeActivity();
            }

            @Override
            public void onLoginFailed() {
                mILoginView.hideLoading();
                mILoginView.showFailedError();
            }
        });
    }

    public void gotoHomeActivity(){
        mContext.startActivity(new Intent(mContext, MainActivity.class));
    }

    public void gotoRegisterActivity(){
        mContext.startActivity(new Intent(mContext, RegisterActivity.class));
    }
}
