package com.app.hexuegang.wsnmonitor.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.app.hexuegang.wsnmonitor.BaseActivity;
import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.databinding.ActivityLoginBinding;
import com.app.hexuegang.wsnmonitor.mywidget.LoadingDialog;
import com.app.hexuegang.wsnmonitor.presenter.LoginPresenter;
import com.app.hexuegang.wsnmonitor.util.SPHelper;
import com.app.hexuegang.wsnmonitor.publish.MyConstants;
import com.app.hexuegang.wsnmonitor.util.StringUtils;

/**
 * Created by hexuegang on 2017/1/19.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements ILoginView, View.OnClickListener {
    private LoadingDialog loadingDialog;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {

        mLoginPresenter = new LoginPresenter(LoginActivity.this, this);

        setToolBar(false);

        //判断记住密码多选框的状态
        if(SPHelper.getBoolean(SPHelper.sp_login, MyConstants.SP_KEY_REMEMBER_PASSWORD))
        {
            //设置默认是记录密码状态
            bindingView.cbRememberPwd.setChecked(true);
            bindingView.etUsername.setText(SPHelper.getString(SPHelper.sp_login, MyConstants.SP_KEY_USERNAME));
            bindingView.etUsername.setText(SPHelper.getString(SPHelper.sp_login, MyConstants.SP_KEY_PASSWORD));

            //判断自动登陆多选框状态
            if(SPHelper.getBoolean(SPHelper.sp_login, MyConstants.SP_KEY_AUTOMATIC_LOGON))
            {
                //设置默认是自动登录状态
                bindingView.cbAutomaticLogon.setChecked(true);
                //跳转界面
                gotoHomeActivity();
            }
        }

        if (loadingDialog == null)
        {
            loadingDialog = new LoadingDialog(LoginActivity.this).Builder();
        }

        bindingView.btnLogin.setOnClickListener(this);
        bindingView.btnRegister.setOnClickListener(this);
        bindingView.cbRememberPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (bindingView.cbRememberPwd.isChecked()) {
                    //记住密码已选中
                    SPHelper.putBoolean(SPHelper.sp_login, MyConstants.SP_KEY_REMEMBER_PASSWORD, true);
                }else {
                    //记住密码未选中
                    SPHelper.putBoolean(SPHelper.sp_login, MyConstants.SP_KEY_REMEMBER_PASSWORD, false);
                }
            }
        });
        bindingView.cbAutomaticLogon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (bindingView.cbRememberPwd.isChecked()) {
                    //自动登录已选中
                    SPHelper.putBoolean(SPHelper.sp_login, MyConstants.SP_KEY_AUTOMATIC_LOGON, true);
                }else {
                    //自动登录未选中
                    SPHelper.putBoolean(SPHelper.sp_login, MyConstants.SP_KEY_AUTOMATIC_LOGON, false);
                }
            }
        });
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
                doLogin();
                break;
            case R.id.btn_register:
                mLoginPresenter.gotoRegisterActivity();
                break;
        }
    }

    private void doLogin() {

        //登录成功和记住密码框为选中状态才保存用户信息
        if(bindingView.cbRememberPwd.isChecked())
        {
            //记住用户名、密码、
            SPHelper.putString(SPHelper.sp_login, MyConstants.SP_KEY_USERNAME, bindingView.etUsername.getText().toString());
            SPHelper.putString(SPHelper.sp_login, MyConstants.SP_KEY_PASSWORD, bindingView.etPassword.getText().toString());
        }

        mLoginPresenter.login();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter = null;
        loadingDialog = null;
    }
}
