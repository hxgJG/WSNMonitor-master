package com.app.hexuegang.wsnmonitor.view.fragment.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.hexuegang.wsnmonitor.BaseActivity;
import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.bean.parameter_water;
import com.app.hexuegang.wsnmonitor.databinding.ActivityWaterElementDetailBinding;

/**
 * Created by HEXG on 2017/4/9.
 */

public class WaterElementDetailActivity extends BaseActivity<ActivityWaterElementDetailBinding> implements View.OnClickListener {

    private parameter_water waterElement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_element_detail);

        Intent intent = getIntent();
        waterElement = (parameter_water) intent.getSerializableExtra("water_element");
        if (waterElement == null) return;
        initUI();
    }

    private void initUI() {
        setToolBar(true);
        setToolBarTitle(waterElement.getName());
        setNavigationIcon(R.mipmap.toolbar_back);
        setNavigationIconClick(WaterElementDetailActivity.this, true);
        bindingView.elementDetailTvName.setText(waterElement.getName());
        bindingView.elementDetailTvStandard.setText(waterElement.getElement_standard_1()+"mg／L");
        bindingView.elementDetailTvValue.setText(waterElement.getElement_value()+"mg／L");
        bindingView.elementDetailTvTime.setText(waterElement.getUpdatedAt());

        float ew_value = 0;
        try {
            ew_value = waterElement.getElement_value()/waterElement.getElement_standard_1();
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
                WaterElementDetailActivity.this.finish();
                break;
        }
    }
}