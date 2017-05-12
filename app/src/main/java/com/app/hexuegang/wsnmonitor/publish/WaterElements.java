package com.app.hexuegang.wsnmonitor.publish;

import com.app.hexuegang.wsnmonitor.bean.parameter_water;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 水因子的各元素和对应评价值的集合
 * <p>
 * Created by HEXG on 2017/5/12.
 */

public class WaterElements {

    public static final String w_666 = "water_666";
    public static final String w_alpha_radioactivity = "water_alpha_radioactivity";
    public static final String w_anion_synthetic_detergent = "water_anion_synthetic_detergent";
    public static final String w_As = "water_As";
    public static final String w_Ba = "water_Ba";
    public static final String w_Be = "water_Be";
    public static final String w_beta_radioactivity = "water_beta_radioactivity";
    public static final String w_Cd = "water_Cd";
    public static final String w_Cl = "water_Cl";
    public static final String w_Co = "water_Co";
    public static final String w_colour = "water_colour";
    public static final String w_Cr = "water_Cr";
    public static final String w_Cu = "water_Cu";
    public static final String w_cyanide = "water_cyanide";
    public static final String w_DDT = "water_DDT";
    public static final String w_dissolved_solid = "water_dissolved_solid";
    public static final String w_Fe = "water_Fe";
    public static final String w_fluoride = "water_fluoride";
    public static final String w_hardness = "water_hardness";
    public static final String w_Hg = "water_Hg";
    public static final String w_iodide = "water_iodide";
    public static final String w_Mn = "water_Mn";
    public static final String w_Mo = "water_Mo";
    public static final String w_naked_eye = "water_naked_eye";
    public static final String w_NH3 = "water_NH3";
    public static final String w_Ni = "water_Ni";
    public static final String w_NaNO2 = "water_NaNO2";
    public static final String w_NO3 = "water_NO3";
    public static final String w_odor = "water_odor";
    public static final String w_Pb = "water_Pb";
    public static final String w_permanganate_index = "water_permanganate_index";
    public static final String w_pH = "water_pH";
    public static final String w_phenol = "water_phenol";
    public static final String w_Se = "water_Se";
    public static final String w_SO3 = "water_SO3";
    public static final String w_total_bacteria = "water_total_bacteria";
    public static final String w_total_coliform = "water_total_coliform";
    public static final String w_turbidity = "water_turbidity";
    public static final String w_Zn = "water_Zn";

    public static ArrayList<Float> list_666 = new ArrayList<>();
    public static ArrayList<Float> list_alpha_radioactivity = new ArrayList<>();
    public static ArrayList<Float> list_anion_synthetic_detergent = new ArrayList<>();
    public static ArrayList<Float> list_As = new ArrayList<>();
    public static ArrayList<Float> list_Ba = new ArrayList<>();
    public static ArrayList<Float> list_Be = new ArrayList<>();
    public static ArrayList<Float> list_beta_radioactivity = new ArrayList<>();
    public static ArrayList<Float> list_Cd = new ArrayList<>();
    public static ArrayList<Float> list_Cl = new ArrayList<>();
    public static ArrayList<Float> list_Co = new ArrayList<>();
    public static ArrayList<Float> list_colour = new ArrayList<>();
    public static ArrayList<Float> list_Cr = new ArrayList<>();
    public static ArrayList<Float> list_Cu = new ArrayList<>();
    public static ArrayList<Float> list_cyanide = new ArrayList<>();
    public static ArrayList<Float> list_DDT = new ArrayList<>();
    public static ArrayList<Float> list_dissolved_solid = new ArrayList<>();
    public static ArrayList<Float> list_Fe = new ArrayList<>();
    public static ArrayList<Float> list_fluoride = new ArrayList<>();
    public static ArrayList<Float> list_hardness = new ArrayList<>();
    public static ArrayList<Float> list_Hg = new ArrayList<>();
    public static ArrayList<Float> list_iodide = new ArrayList<>();
    public static ArrayList<Float> list_Mn = new ArrayList<>();
    public static ArrayList<Float> list_Mo = new ArrayList<>();
    public static ArrayList<Float> list_naked_eye = new ArrayList<>();
    public static ArrayList<Float> list_NH3 = new ArrayList<>();
    public static ArrayList<Float> list_Ni = new ArrayList<>();
    public static ArrayList<Float> list_NaNO2 = new ArrayList<>();
    public static ArrayList<Float> list_NO3 = new ArrayList<>();
    public static ArrayList<Float> list_odor = new ArrayList<>();
    public static ArrayList<Float> list_Pb = new ArrayList<>();
    public static ArrayList<Float> list_permanganate_index = new ArrayList<>();
    public static ArrayList<Float> list_pH = new ArrayList<>();
    public static ArrayList<Float> list_phenol = new ArrayList<>();
    public static ArrayList<Float> list_Se = new ArrayList<>();
    public static ArrayList<Float> list_SO3 = new ArrayList<>();
    public static ArrayList<Float> list_total_bacteria = new ArrayList<>();
    public static ArrayList<Float> list_total_coliform = new ArrayList<>();
    public static ArrayList<Float> list_turbidity = new ArrayList<>();
    public static ArrayList<Float> list_Zn = new ArrayList<>();


    /**
     * 某个水环境指数参数的预处理方法
     * @param pWater
     * @param v
     */
    public static void oneWaterElememtMethod(parameter_water pWater, float v) {
        switch (pWater.getElement_name()) {
            case w_666:
                list_666.add(v);
                break;
            case w_alpha_radioactivity:
                list_alpha_radioactivity.add(v);
                break;
            case w_anion_synthetic_detergent:
                list_anion_synthetic_detergent.add(v);
                break;
            case w_As:
                list_As.add(v);
                break;
            case w_Ba:
                list_Ba.add(v);
                break;
            case w_Be:
                list_Be.add(v);
                break;
            case w_beta_radioactivity:
                list_beta_radioactivity.add(v);
                break;
            case w_Cd:
                list_Cd.add(v);
                break;
            case w_Cl:
                list_Cl.add(v);
                break;
            case w_Co:
                list_Co.add(v);
                break;
            case w_colour:
                list_colour.add(v);
                break;
            case w_Cr:
                list_Cr.add(v);
                break;
            case w_Cu:
                list_Cu.add(v);
                break;
            case w_cyanide:
                list_cyanide.add(v);
                break;
            case w_DDT:
                list_DDT.add(v);
                break;
            case w_dissolved_solid:
                list_dissolved_solid.add(v);
                break;
            case w_Fe:
                list_Fe.add(v);
                break;
            case w_fluoride:
                list_fluoride.add(v);
                break;
            case w_hardness:
                list_hardness.add(v);
                break;
            case w_Hg:
                list_Hg.add(v);
                break;
            case w_iodide:
                list_iodide.add(v);
                break;
            case w_Mn:
                list_Mn.add(v);
                break;
            case w_Mo:
                list_Mo.add(v);
                break;
            case w_naked_eye:
                list_naked_eye.add(v);
                break;
            case w_NH3:
                list_NH3.add(v);
                break;
            case w_Ni:
                list_Ni.add(v);
                break;
            case w_NaNO2:
                list_NaNO2.add(v);
                break;
            case w_NO3:
                list_NO3.add(v);
                break;
            case w_odor:
                list_odor.add(v);
                break;
            case w_Pb:
                list_Pb.add(v);
                break;
            case w_permanganate_index:
                list_permanganate_index.add(v);
                break;
            case w_pH:
                list_pH.add(v);
                break;
            case w_phenol:
                list_phenol.add(v);
                break;
            case w_Se:
                list_Se.add(v);
                break;
            case w_SO3:
                list_SO3.add(v);
                break;
            case w_total_bacteria:
                list_total_bacteria.add(v);
                break;
            case w_total_coliform:
                list_total_coliform.add(v);
                break;
            case w_turbidity:
                list_turbidity.add(v);
                break;
            case w_Zn:
                list_Zn.add(v);
                break;
        }
    }

    /**
     * 获取集合中的最大值
     * @param list
     * @return
     */
    public static float getMax(ArrayList<Float> list){
        if (list.isEmpty()) return -1;
        return Collections.max(list).floatValue();
    }

    public static float getAverage(ArrayList<Float> list){
        int size = list.size();
        float sum = 0.0f;
        float averageValue = 0.0f;
        for (int i = 0; i< size; i++){
            sum += list.get(i);
        }

        try {
            averageValue = sum/size;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return averageValue;
    }

    /**
     * 某个水环境指数参数的评价计算方法
     * @param pWater
     * @param v
     */
    public static void oneWaterElememtComputingMethod(parameter_water pWater, float v) {
        switch (pWater.getElement_name()) {
            case w_666:
                list_666.add(v);
                break;
            case w_alpha_radioactivity:
                list_alpha_radioactivity.add(v);
                break;
            case w_anion_synthetic_detergent:
                list_anion_synthetic_detergent.add(v);
                break;
            case w_As:
                list_As.add(v);
                break;
            case w_Ba:
                list_Ba.add(v);
                break;
            case w_Be:
                list_Be.add(v);
                break;
            case w_beta_radioactivity:
                list_beta_radioactivity.add(v);
                break;
            case w_Cd:
                list_Cd.add(v);
                break;
            case w_Cl:
                list_Cl.add(v);
                break;
            case w_Co:
                list_Co.add(v);
                break;
            case w_colour:
                list_colour.add(v);
                break;
            case w_Cr:
                list_Cr.add(v);
                break;
            case w_Cu:
                list_Cu.add(v);
                break;
            case w_cyanide:
                list_cyanide.add(v);
                break;
            case w_DDT:
                list_DDT.add(v);
                break;
            case w_dissolved_solid:
                list_dissolved_solid.add(v);
                break;
            case w_Fe:
                list_Fe.add(v);
                break;
            case w_fluoride:
                list_fluoride.add(v);
                break;
            case w_hardness:
                list_hardness.add(v);
                break;
            case w_Hg:
                list_Hg.add(v);
                break;
            case w_iodide:
                list_iodide.add(v);
                break;
            case w_Mn:
                list_Mn.add(v);
                break;
            case w_Mo:
                list_Mo.add(v);
                break;
            case w_naked_eye:
                list_naked_eye.add(v);
                break;
            case w_NH3:
                list_NH3.add(v);
                break;
            case w_Ni:
                list_Ni.add(v);
                break;
            case w_NaNO2:
                list_NaNO2.add(v);
                break;
            case w_NO3:
                list_NO3.add(v);
                break;
            case w_odor:
                list_odor.add(v);
                break;
            case w_Pb:
                list_Pb.add(v);
                break;
            case w_permanganate_index:
                list_permanganate_index.add(v);
                break;
            case w_pH:
                list_pH.add(v);
                break;
            case w_phenol:
                list_phenol.add(v);
                break;
            case w_Se:
                list_Se.add(v);
                break;
            case w_SO3:
                list_SO3.add(v);
                break;
            case w_total_bacteria:
                list_total_bacteria.add(v);
                break;
            case w_total_coliform:
                list_total_coliform.add(v);
                break;
            case w_turbidity:
                list_turbidity.add(v);
                break;
            case w_Zn:
                list_Zn.add(v);
                break;
        }
    }
}
