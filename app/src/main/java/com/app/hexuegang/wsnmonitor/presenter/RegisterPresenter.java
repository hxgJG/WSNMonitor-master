package com.app.hexuegang.wsnmonitor.presenter;

import android.content.Context;
import android.content.Intent;

import com.app.hexuegang.wsnmonitor.IListener.IUserOperate;
import com.app.hexuegang.wsnmonitor.IListener.OnRegisterListener;
import com.app.hexuegang.wsnmonitor.IListener.operate.UserOperate;
import com.app.hexuegang.wsnmonitor.util.StringUtils;
import com.app.hexuegang.wsnmonitor.view.IRegisterView;
import com.app.hexuegang.wsnmonitor.view.LoginActivity;

/**
 * Created by hexuegang on 2017/1/19.
 */

public class RegisterPresenter {
    private IUserOperate userOperate;
    private IRegisterView mIRegisterView;
    private Context mContext;
    public RegisterPresenter(Context mContext, IRegisterView mIRegisterView){
        this.userOperate = new UserOperate();
        this.mContext = mContext;
        this.mIRegisterView = mIRegisterView;
    }

    public void register(){
        if (StringUtils.isEmpty(mIRegisterView.getUsername()) || StringUtils.isEmpty(mIRegisterView.getPassword())
                || StringUtils.isEmpty(mIRegisterView.getEmail()) || StringUtils.isEmpty(mIRegisterView.getSecondPassword())){
            mIRegisterView.ErrorOfUsnorPsdOrEmail();
            return;
        }

        if (!mIRegisterView.getSecondPassword().endsWith(mIRegisterView.getPassword())){
            mIRegisterView.ErrorOfConfigerPsd();
            return;
        }

        mIRegisterView.showLoading();
        userOperate.register(mContext, mIRegisterView.getUsername(), mIRegisterView.getPassword(),
                mIRegisterView.getEmail(), new OnRegisterListener() {
                    @Override
                    public void onRegisterSuccess() {
                        mIRegisterView.hideLoading();
                        mIRegisterView.success();
                        mIRegisterView.finishActivity();
                    }

                    @Override
                    public void onRegisterFailed() {
                        mIRegisterView.hideLoading();
                        mIRegisterView.failed();
                    }
                });
    }

    public void gotoLoginActivity(){
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }
}
