package com.app.hexuegang.wsnmonitor.view;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.app.hexuegang.wsnmonitor.publish.MyConstants;
import com.app.hexuegang.wsnmonitor.util.StringUtils;

/**
 * Created by hexuegang on 2017/1/19.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements ILoginView, View.OnClickListener {
    private LoadingDialog loadingDialog;
    private LoginPresenter mLoginPresenter = new LoginPresenter(LoginActivity.this, this);
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        setToolBar(false);

        sp = this.getSharedPreferences(MyConstants.SP_NAME_USER_INFO, Context.MODE_PRIVATE);

        //判断记住密码多选框的状态
        if(sp.getBoolean(MyConstants.SP_KEY_REMEMBER_PASSWORD, false))
        {
            //设置默认是记录密码状态
            bindingView.cbRememberPwd.setChecked(true);
            bindingView.etUsername.setText(sp.getString(MyConstants.SP_KEY_USERNAME, ""));
            bindingView.etPassword.setText(sp.getString(MyConstants.SP_KEY_PASSWORD, ""));
            //判断自动登陆多选框状态
            if(sp.getBoolean(MyConstants.SP_KEY_AUTOMATIC_LOGON, false))
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
//        initBmob(); 已在MyApplication中初始化Bmob
        bindingView.cbRememberPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (bindingView.cbRememberPwd.isChecked()) {
                    //记住密码已选中
                    sp.edit().putBoolean(MyConstants.SP_KEY_REMEMBER_PASSWORD, true).commit();
                }else {
                    //记住密码未选中
                    sp.edit().putBoolean(MyConstants.SP_KEY_REMEMBER_PASSWORD, false).commit();
                }
            }
        });
        bindingView.cbAutomaticLogon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (bindingView.cbRememberPwd.isChecked()) {
                    //自动登录已选中
                    sp.edit().putBoolean(MyConstants.SP_KEY_AUTOMATIC_LOGON, true).commit();
                }else {
                    //自动登录未选中
                    sp.edit().putBoolean(MyConstants.SP_KEY_AUTOMATIC_LOGON, false).commit();
                }
            }
        });
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
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(MyConstants.SP_KEY_USERNAME, bindingView.etUsername.getText().toString());
            editor.putString(MyConstants.SP_KEY_PASSWORD, bindingView.etPassword.getText().toString());
            editor.commit();
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
