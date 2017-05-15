package com.app.hexuegang.wsnmonitor.publish;

import android.content.Context;

import com.app.hexuegang.wsnmonitor.bean.parameter_water;
import com.app.hexuegang.wsnmonitor.util.NumberUtils;
import com.app.hexuegang.wsnmonitor.util.SPHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HEXG on 2017/5/12.
 */

public class MyMethods {

    public static double q_water = 0.0f;//水的总评价质量
    public static double a_water = 0.0f;//大气的总评价质量
    public static double s_water = 0.0f;//土壤的总评价质量
    public static double n_water = 0.0f;//噪声的总评价质量
    public static double t_water = 0.0f;//总评价质量

    /**
     * 计算水因子的评价质量
     * @param list
     */
    public static void waterComputingMethod(List<parameter_water> list) {

        try {
            int length = list.size();
            if (length <= 0) return;

//            ArrayList<Float> waterValues1 = new ArrayList<>();//第一组
//            ArrayList<Float> waterValues2 = new ArrayList<>();//第二组
//            ArrayList<Float> waterValues3 = new ArrayList<>();//第三组
            ArrayList<Double> elementValues = new ArrayList<>();//各元素评价值的集合
            ArrayList<String> elementNames = new ArrayList<>();

            for (int i = 0; i < length; i++) {
                parameter_water pWater = list.get(i);
                if (pWater == null) return;
                double v = pWater.getElement_value() / pWater.getElement_standard_1();
//                if (pWater.getGroupId() == 1) {
//                    waterValues1.add(v);
//                } else if (pWater.getGroupId() == 2) {
//                    waterValues2.add(v);
//                } else if (pWater.getGroupId() == 3) {
//                    waterValues3.add(v);
//                }

                if (!elementNames.contains(pWater.getElement_name())){
                    elementNames.add(pWater.getElement_name());
                }

                WaterElements.oneWaterElememtMethod(pWater.getElement_name(), v);
            }

            if (!elementNames.isEmpty()) {
                int size = elementNames.size();
                elementValues.clear();
                for (int i = 0; i< size; i++){
                    elementValues.add(WaterElements.oneWaterElememtComputingMethod(elementNames.get(i)));
                }
            }

            q_water = WaterElements.getAverage(elementValues, MyConstants.ELEMENTS_NUMBER_TYPE_WATER);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            String qWaterStr = NumberUtils.formatDouble(q_water);
            SPHelper.putString(SPHelper.sp_quality_evaluate, MyConstants.SP_KEY_QUALITY_WATER, qWaterStr);
        }
    }
}