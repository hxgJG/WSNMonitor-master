package com.app.hexuegang.wsnmonitor.view.fragment.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.hexuegang.wsnmonitor.BaseActivity;
import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.bean.parameter_noise;
import com.app.hexuegang.wsnmonitor.databinding.ActivityNoiseElementDetailBinding;

/**
 * Created by HEXG on 2017/4/9.
 */

public class NoiseElementDetailActivity extends BaseActivity<ActivityNoiseElementDetailBinding> implements View.OnClickListener {

    private parameter_noise parameterNoise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noise_element_detail);

        Intent intent = getIntent();
        parameterNoise = (parameter_noise) intent.getSerializableExtra("noise_element");
        if (parameterNoise == null) return;
        initUI();
    }

    private void initUI() {
        setToolBar(true);
        setToolBarTitle(parameterNoise.getNoise_name());
        setNavigationIcon(R.mipmap.toolbar_back);
        setNavigationIconClick(NoiseElementDetailActivity.this, true);
        bindingView.elementDetailTvName.setText(parameterNoise.getNoise_name());
        bindingView.elementDetailTvStandard.setText(parameterNoise.getNoise_standard()+"dB(A)");
        bindingView.elementDetailTvValue.setText(parameterNoise.getNoise_value()+"dB(A)");
        bindingView.elementDetailTvTime.setText(parameterNoise.getUpdatedAt());

        float ew_value = 0;
        try {
            if (parameterNoise.getNoise_time_type() == 1)
            {
                bindingView.elementDetailTvDate.setText("昼间");
                ew_value = parameterNoise.getNoise_value()/parameterNoise.getNoise_standard();
            }else {
                bindingView.elementDetailTvDate.setText("夜间");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        if (ew_value <= 0){
            bindingView.elementDetailTvDescribe.setText("    数据错误");
            bindingView.elementDetailTvDescribe.setTextColor(Color.parseColor("#F86731"));
        }else if (ew_value <= 0.7){
            bindingView.elementDetailTvDescribe.setText("    无噪声污染");
            bindingView.elementDetailTvDescribe.setTextColor(Color.parseColor("#00FF00"));
        }else if (ew_value <= 1.0){
            bindingView.elementDetailTvDescribe.setText("    有轻微噪声污染");
            bindingView.elementDetailTvDescribe.setTextColor(Color.parseColor("#0000FF"));
        }else {
            bindingView.elementDetailTvDescribe.setText("    噪声污染严重");
            bindingView.elementDetailTvDescribe.setTextColor(Color.parseColor("#FF0000"));
        }

        bindingView.btnOk.setOnClickListener(this);

        doListener();
    }

    private void doListener() {

    }



    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_ok:
                NoiseElementDetailActivity.this.finish();
                break;
        }
    }
}