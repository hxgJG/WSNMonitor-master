package com.app.hexuegang.wsnmonitor.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.app.hexuegang.wsnmonitor.BaseActivity;
import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.databinding.ActivityLoginBinding;
import com.app.hexuegang.wsnmonitor.mywidget.LoadingDialog;
import com.app.hexuegang.wsnmonitor.presenter.LoginPresenter;
import com.app.hexuegang.wsnmonitor.util.StringUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by hexuegang on 2017/1/19.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements ILoginView, View.OnClickListener {
    private LoadingDialog loadingDialog;
    private LoginPresenter mLoginPresenter = new LoginPresenter(LoginActivity.this, this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        setToolBar(false);
        if (loadingDialog == null)
        {
            loadingDialog = new LoadingDialog(LoginActivity.this).Builder();
        }

        bindingView.btnLogin.setOnClickListener(this);
        bindingView.btnRegister.setOnClickListener(this);
//        initBmob(); 已在MyApplication中初始化Bmob
    }

//    private void initBmob() {
//        BmobConfig config =new BmobConfig.Builder(this)
//                .setApplicationId("968f16f4fe79f35e87a1db95c594a18a")//设置appkey
//                .setConnectTimeout(30)//请求超时时间（单位为秒）：默认15s
//                .setUploadBlockSize(1024*1024)//文件分片上传时每片的大小（单位字节），默认512*1024
//                .setFileExpiration(2500)//文件的过期时间(单位为秒)：默认1800s
//                .build();
//        Bmob.initialize(config);
//    }

    @Override
    public String getUsername() {
        return bindingView.etUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return bindingView.etPassword.getText().toString();
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
    }

    @Override
    public void gotoHomeActivity() {
        mLoginPresenter.gotoHomeActivity();
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        LoginActivity.this.finish();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }

    @Override
    public void ErrorOfUserAndPsd() {
        if (StringUtils.isEmpty(bindingView.etUsername.getText().toString())
                || StringUtils.isEmpty(bindingView.etPassword.getText().toString())){
            showFailedError();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mLoginPresenter.login();
                break;
            case R.id.btn_register:
                mLoginPresenter.gotoRegisterActivity();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter = null;
        loadingDialog = null;
    }
}
