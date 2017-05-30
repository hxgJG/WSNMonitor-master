package com.app.hexuegang.wsnmonitor.publish;

import com.app.hexuegang.wsnmonitor.bean.ParameterAirYear;
import com.app.hexuegang.wsnmonitor.bean.ParameterNoise;
import com.app.hexuegang.wsnmonitor.bean.ParameterSoil;
import com.app.hexuegang.wsnmonitor.bean.ParameterWater;
import com.app.hexuegang.wsnmonitor.util.NumberUtils;
import com.app.hexuegang.wsnmonitor.util.SPHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HEXG on 2017/5/12.
 */

public class MyMethods {

    public static double q_water = 0.0f;//水的总评价质量
    public static double q_air = 0.0f;//大气的总评价质量
    public static double q_soil = 0.0f;//土壤的总评价质量
    public static double q_noise = 0.0f;//噪声的总评价质量
    public static double q_total = 0.0f;//总评价质量

    /**
     * 计算水因子的评价质量
     *
     * @param list
     */
    public static void waterComputingMethod(List<ParameterWater> list) {

        try {
            int length = list.size();
            if (length <= 0) return;

            ArrayList<Double> elementValues = new ArrayList<>();//各元素评价值的集合
            ArrayList<String> elementNames = new ArrayList<>();

            for (int i = 0; i < length; i++) {
                ParameterWater pWater = list.get(i);
                if (pWater == null) return;
                double v = pWater.getElement_value() / pWater.getElement_standard_1();

                if (!elementNames.contains(pWater.getElement_name())) {
                    elementNames.add(pWater.getElement_name());
                }

                WaterElements.oneWaterElememtMethod(pWater.getElement_name(), v);
            }

            if (!elementNames.isEmpty()) {
                int size = elementNames.size();
                elementValues.clear();
                for (int i = 0; i < size; i++) {
                    elementValues.add(WaterElements.oneWaterElememtComputingMethod(elementNames.get(i)));
                }
            }

            q_water = WaterElements.getAverage(elementValues);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            String qWaterStr = NumberUtils.formatDouble(q_water);
            SPHelper.putString(SPHelper.sp_quality_evaluate_water, MyConstants.SP_KEY_QUALITY_WATER, qWaterStr);
        }
    }

    /**
     * 计算大气因子的评价质量
     *
     * @param list
     */
    public static void airComputingMethod(List<ParameterAirYear> list) {

        try {
            int length = list.size();
            if (length <= 0) return;

            ArrayList<Double> elementValues = new ArrayList<>();//各元素评价值的集合
            ArrayList<String> elementNames = new ArrayList<>();

            for (int i = 0; i < length; i++) {
                ParameterAirYear pAirYear = list.get(i);
                if (pAirYear == null) return;
                double v = pAirYear.getAir_year_value() / pAirYear.getAir_year_standard();

                if (!elementNames.contains(pAirYear.getAir_year_name())) {
                    elementNames.add(pAirYear.getAir_year_name());
                }

                AirElements.oneAirElememtMethod(pAirYear.getAir_year_name(), v);
            }

            if (!elementNames.isEmpty()) {
                int size = elementNames.size();
                elementValues.clear();
                for (int i = 0; i < size; i++) {
                    elementValues.add(AirElements.oneAirElememtComputingMethod(elementNames.get(i)));
                }
            }

            q_air = AirElements.getAverage(elementValues);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            String qAirStr = NumberUtils.formatDouble(q_air);
            SPHelper.putString(SPHelper.sp_quality_evaluate_air, MyConstants.SP_KEY_QUALITY_AIR, qAirStr);
        }
    }

    /**
     * 计算土壤因子的评价质量
     *
     * @param list
     */
    public static void soilComputingMethod(List<ParameterSoil> list) {

        try {
            int length = list.size();
            if (length <= 0) return;

            ArrayList<Double> elementValues = new ArrayList<>();//各元素评价值的集合
            ArrayList<String> elementNames = new ArrayList<>();

            for (int i = 0; i < length; i++) {
                ParameterSoil pSoil = list.get(i);
                if (pSoil == null) return;
                double v = (pSoil.getSoil_value()-pSoil.getSoil_value_bg()) / (pSoil.getSoil_standard()-pSoil.getSoil_value_bg());

                if (!elementNames.contains(pSoil.getSoil_name())) {
                    elementNames.add(pSoil.getSoil_name());
                }

                SoilElements.oneSoilElememtMethod(pSoil.getSoil_name(), v);
            }

            if (!elementNames.isEmpty()) {
                int size = elementNames.size();
                elementValues.clear();
                for (int i = 0; i < size; i++) {
                    elementValues.add(SoilElements.oneSoilElememtComputingMethod(elementNames.get(i)));
                }
            }

            q_soil = SoilElements.getAverage(elementValues);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            String qSoilStr = NumberUtils.formatDouble(q_soil);
            SPHelper.putString(SPHelper.sp_quality_evaluate_soil, MyConstants.SP_KEY_QUALITY_SOIL, qSoilStr);
        }
    }


    /**
     * 计算噪声因子的评价质量
     *
     * @param list
     */
    public static void noiseComputingMethod(List<ParameterNoise> list) {

        try {
            int length = list.size();
            if (length <= 0) return;

            ArrayList<Double> elementValues = new ArrayList<>();//各元素评价值的集合

            for (int i = 0; i < length; i++) {
                ParameterNoise pNoise = list.get(i);
                if (pNoise == null) return;
                double v = pNoise.getNoise_value() / pNoise.getNoise_standard();
                elementValues.add(v);
            }

            if (!elementValues.isEmpty()) {
                q_noise = getAverage(elementValues);
                elementValues.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            String qNoisetr = NumberUtils.formatDouble(q_noise);
            SPHelper.putString(SPHelper.sp_quality_evaluate_noise, MyConstants.SP_KEY_QUALITY_NOISE, qNoisetr);
        }
    }

    private static double getAverage(ArrayList<Double> list) {
        int size = list.size();
        double sum = 0.0f;
        double averageValue = 0.0f;
        for (int i = 0; i < size; i++) {
            sum += list.get(i);
        }

        try {
            averageValue = sum / size;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return averageValue;
    }
}