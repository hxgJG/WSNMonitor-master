package com.app.hexuegang.wsnmonitor.view.fragment.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.hexuegang.wsnmonitor.BaseActivity;
import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.bean.parameter_air_year;
import com.app.hexuegang.wsnmonitor.databinding.ActivityAirElementDetailBinding;

/**
 * Created by HEXG on 2017/4/9.
 */

public class AirElementDetailActivity extends BaseActivity<ActivityAirElementDetailBinding> implements View.OnClickListener {

    private parameter_air_year airYear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_element_detail);

        Intent intent = getIntent();
        airYear = (parameter_air_year) intent.getSerializableExtra("air_element");
        if (airYear == null) return;
        initUI();
    }

    private void initUI() {
        setToolBar(true);
        setToolBarTitle(airYear.getAir_element_name());
        setNavigationIcon(R.mipmap.toolbar_back);
        setNavigationIconClick(AirElementDetailActivity.this, true);
        bindingView.elementDetailTvName.setText(airYear.getAir_element_name());
        bindingView.elementDetailTvStandard.setText(airYear.getAir_year_standard()+"微克／立方米");
        bindingView.elementDetailTvValue.setText(airYear.getAir_year_value()+"微克／立方米");
        bindingView.elementDetailTvTime.setText(airYear.getUpdatedAt());

        float ew_value = 0;
        try {
            ew_value = airYear.getAir_year_value()/ airYear.getAir_year_standard();
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
                AirElementDetailActivity.this.finish();
                break;
        }
    }
}