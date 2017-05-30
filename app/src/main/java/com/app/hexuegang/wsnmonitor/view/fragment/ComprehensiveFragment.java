package com.app.hexuegang.wsnmonitor.view.fragment;

import android.view.View;
import android.widget.TextView;

import com.app.hexuegang.wsnmonitor.R;
import com.app.hexuegang.wsnmonitor.publish.MyConstants;
import com.app.hexuegang.wsnmonitor.util.NumberUtils;
import com.app.hexuegang.wsnmonitor.util.SPHelper;

/**
 * Created by HEXG on 2017/4/8.
 */

public class ComprehensiveFragment extends BaseFragment {

    private TextView tv_water_result;
    private TextView tv_water_describe;
    private TextView tv_air_result;
    private TextView tv_air_describe;
    private TextView tv_soil_result;
    private TextView tv_soil_describe;
    private TextView tv_noise_result;
    private TextView tv_noise_describe;
    private TextView tv_overall_result;
    private TextView tv_overall_describe;

    private double qualityWater;
    private double qualityAir;
    private double qualitySoil;
    private double qualityNoise;
    private double qualityOverall;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_5;
    }

    @Override
    public void init(View view) {
        tv_water_result = (TextView) view.findViewById(R.id.tv_water_result);
        tv_water_describe = (TextView) view.findViewById(R.id.tv_water_describe);
        tv_air_result = (TextView) view.findViewById(R.id.tv_air_result);
        tv_air_describe = (TextView) view.findViewById(R.id.tv_air_describe);
        tv_soil_result = (TextView) view.findViewById(R.id.tv_soil_result);
        tv_soil_describe = (TextView) view.findViewById(R.id.tv_soil_describe);
        tv_noise_result = (TextView) view.findViewById(R.id.tv_noise_result);
        tv_noise_describe = (TextView) view.findViewById(R.id.tv_noise_describe);
        tv_overall_result = (TextView) view.findViewById(R.id.tv_overall_result);
        tv_overall_describe = (TextView) view.findViewById(R.id.tv_overall_describe);

        getData();
    }

    private void getData() {

        String qualityWaterStr = SPHelper.getString(SPHelper.sp_quality_evaluate_water, MyConstants.SP_KEY_QUALITY_WATER);
        String qualityAirStr = SPHelper.getString(SPHelper.sp_quality_evaluate_air, MyConstants.SP_KEY_QUALITY_AIR);
        String qualitySoilStr = SPHelper.getString(SPHelper.sp_quality_evaluate_soil, MyConstants.SP_KEY_QUALITY_SOIL);
        String qualityNoiseStr = SPHelper.getString(SPHelper.sp_quality_evaluate_noise, MyConstants.SP_KEY_QUALITY_NOISE);
        String qualityOverallStr = SPHelper.getString(SPHelper.sp_quality_evaluate_total, MyConstants.SP_KEY_QUALITY_OVERALL);

        try {
            qualityWater = Float.parseFloat(qualityWaterStr);
            qualityAir = Float.parseFloat(qualityAirStr);
            qualitySoil = Float.parseFloat(qualitySoilStr);
            qualityNoise = Float.parseFloat(qualityNoiseStr);
            qualityOverall = qualityWater*MyConstants.WEIGHT_WATER
                    + qualityAir*MyConstants.WEIGHT_AIR
                    + qualitySoil*MyConstants.WEIGHT_SOIL
                    + qualityNoise*MyConstants.WEIGHT_NOISE;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
        }

        tv_water_result.setText(qualityWaterStr);
        tv_air_result.setText(qualityAirStr);
        tv_soil_result.setText(qualitySoilStr);
        tv_noise_result.setText(qualityNoiseStr);
        tv_overall_result.setText(qualityOverallStr);
        setDescribe(qualityWater,qualityAir,qualitySoil,qualityNoise, qualityOverall);
    }

    private void refreshUI() {
        tv_water_result.setText(NumberUtils.formatDouble(qualityWater));
        tv_air_result.setText(NumberUtils.formatDouble(qualityAir));
        tv_soil_result.setText(NumberUtils.formatDouble(qualitySoil));
        tv_noise_result.setText(NumberUtils.formatDouble(qualityNoise));
        tv_overall_result.setText(NumberUtils.formatDouble(qualityOverall));

        setDescribe(qualityWater, qualityAir, qualitySoil, qualityNoise, qualityOverall);
    }

    private void setDescribe(double valueWater, double valueAir, double valueSoil, double valueNoise, double valueOverall){
        tv_water_describe.setText(getDescribe(valueWater));
        tv_air_describe.setText(getDescribe(valueAir));
        tv_soil_describe.setText(getDescribe(valueSoil));
        tv_noise_describe.setText(getDescribe(valueNoise));
        tv_overall_describe.setText(getDescribe(valueOverall));
    }

    private String getDescribe(double qualityValue){
        String describe = "";
        if (qualityValue <= 0){
            describe = "数据异常";
        }else if (qualityValue <= 0.1){
            describe = "质量非常好";
        }else if (qualityValue < 0.5){
            describe = "质量较好";
        }else if (qualityValue < 1){
            describe = "质量一般";
        }else if (qualityValue <= 1.5){
            describe = "质量较差";
        }else if (qualityValue <= 2.5){
            describe = "质量非常差";
        }else{
            describe = "质量极差";
        }
        return describe;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        SPHelper.putString(SPHelper.sp_quality_evaluate_water, MyConstants.SP_KEY_QUALITY_WATER, qualityWater+"");
        SPHelper.putString(SPHelper.sp_quality_evaluate_air, MyConstants.SP_KEY_QUALITY_AIR, qualityAir+"");
        SPHelper.putString(SPHelper.sp_quality_evaluate_soil, MyConstants.SP_KEY_QUALITY_SOIL, qualitySoil+"");
        SPHelper.putString(SPHelper.sp_quality_evaluate_noise, MyConstants.SP_KEY_QUALITY_NOISE, qualityNoise+"");
        SPHelper.putString(SPHelper.sp_quality_evaluate_total, MyConstants.SP_KEY_QUALITY_OVERALL, NumberUtils.formatDouble(qualityOverall));
    }
}
