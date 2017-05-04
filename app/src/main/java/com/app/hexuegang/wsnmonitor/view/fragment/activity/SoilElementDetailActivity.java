package com.app.hexuegang.wsnmonitor.view.fragment.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.hexuegang.wsnmonitor.BaseActivity;
import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.bean.parameter_soil;
import com.app.hexuegang.wsnmonitor.databinding.ActivitySoilElementDetailBinding;

/**
 * Created by HEXG on 2017/4/9.
 */

public class SoilElementDetailActivity extends BaseActivity<ActivitySoilElementDetailBinding> implements View.OnClickListener {

    private parameter_soil parameterSoil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_element_detail);

        Intent intent = getIntent();
        parameterSoil = (parameter_soil) intent.getSerializableExtra("soil_element");
        if (parameterSoil == null) return;
        initUI();
    }

    private void initUI() {
        setToolBar(true);
        setToolBarTitle(parameterSoil.getElement_name());
        setNavigationIcon(R.mipmap.toolbar_back);
        setNavigationIconClick(SoilElementDetailActivity.this, true);
        bindingView.elementDetailTvName.setText(parameterSoil.getElement_name());
        bindingView.elementDetailTvBg.setText(parameterSoil.getSoil_value_bg()+"mg／kg");
        bindingView.elementDetailTvStandard.setText(parameterSoil.getSoil_standard()+"mg／kg");
        bindingView.elementDetailTvValue.setText(parameterSoil.getSoil_value()+"mg／kg");
        bindingView.elementDetailTvTime.setText(parameterSoil.getUpdatedAt());

        float ew_value = 0;
        try {
            ew_value = (parameterSoil.getSoil_value()-parameterSoil.getSoil_value_bg())/ (parameterSoil.getSoil_standard()-parameterSoil.getSoil_value_bg());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        if (ew_value <= 0){
            bindingView.elementDetailTvDescribe.setText("数据错误");
            bindingView.elementDetailTvDescribe.setTextColor(Color.parseColor("#F86731"));
        }else if (ew_value <= 1.0){
            bindingView.elementDetailTvDescribe.setText("该指标在规定范围内");
            bindingView.elementDetailTvDescribe.setTextColor(Color.parseColor("#00FF00"));
        }else if (ew_value <= 2.0){
            bindingView.elementDetailTvDescribe.setText("该指标已超出规定范围，属于轻度污染");
            bindingView.elementDetailTvDescribe.setTextColor(Color.parseColor("#0000FF"));
        }else {
            bindingView.elementDetailTvDescribe.setText("       该指标已严重超出规定范围，属于重度污染");
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
                SoilElementDetailActivity.this.finish();
                break;
        }
    }
}