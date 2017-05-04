package com.app.hexuegang.wsnmonitor.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.app.hexuegang.wsnmonitor.BaseActivity;
import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.databinding.ActivityRegisterBinding;
import com.app.hexuegang.wsnmonitor.mywidget.LoadingDialog;
import com.app.hexuegang.wsnmonitor.presenter.RegisterPresenter;

/**
 * Created by hexuegang on 2017/1/19.
 */

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> implements View.OnClickListener, IRegisterView {
    private LoadingDialog loadingDialog;

    private RegisterPresenter mRegisterPresenter = new RegisterPresenter(RegisterActivity.this, this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    protected void init() {
        if (loadingDialog == null)
        {
            loadingDialog = new LoadingDialog(RegisterActivity.this).Builder();
        }

        bindingView.btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                mRegisterPresenter.register();
                break;
        }
    }

    @Override
    public String getUsername() {
        return bindingView.etUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return bindingView.etPassword.getText().toString();
    }

    @Override
    public String getSecondPassword() {
        return bindingView.etSecondPassword.getText().toString();
    }

    @Override
    public String getEmail() {
        return bindingView.etEmail.getText().toString();
    }

    @Override
    public Context getContext() {
        return RegisterActivity.this;
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
    public void success() {
        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        mRegisterPresenter.gotoLoginActivity();
    }

    @Override
    public void failed() {
        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        RegisterActivity.this.finish();
    }

    @Override
    public void ErrorOfUsnorPsdOrEmail() {
        Toast.makeText(RegisterActivity.this, "用户名,密码或者邮箱错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ErrorOfConfigerPsd() {
        Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRegisterPresenter = null;
        loadingDialog = null;
    }
}
