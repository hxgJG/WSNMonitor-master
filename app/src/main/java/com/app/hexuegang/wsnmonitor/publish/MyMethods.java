package com.app.hexuegang.wsnmonitor.publish;

import com.app.hexuegang.wsnmonitor.bean.parameter_water;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HEXG on 2017/5/12.
 */

public class MyMethods {

    public static float q_water = 0.0f;//水的总评价质量
    public static float a_water = 0.0f;//大气的总评价质量
    public static float s_water = 0.0f;//土壤的总评价质量
    public static float n_water = 0.0f;//噪声的总评价质量
    public static float t_water = 0.0f;//总评价质量

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
            ArrayList<Float> elementValues = new ArrayList<>();//各元素评价值的集合
            ArrayList<String> elementNames = new ArrayList<>();

            for (int i = 0; i < length; i++) {
                parameter_water pWater = list.get(i);
                if (pWater == null) return;
                float v = pWater.getElement_value() / pWater.getElement_standard_1();
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
            System.out.println(q_water);
        }
    }
}