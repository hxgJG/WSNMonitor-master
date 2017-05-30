package com.app.hexuegang.wsnmonitor.publish;

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


    public static ArrayList<Double> list_666 = new ArrayList<>();
    public static ArrayList<Double> list_alpha_radioactivity = new ArrayList<>();
    public static ArrayList<Double> list_anion_synthetic_detergent = new ArrayList<>();
    public static ArrayList<Double> list_As = new ArrayList<>();
    public static ArrayList<Double> list_Ba = new ArrayList<>();
    public static ArrayList<Double> list_Be = new ArrayList<>();
    public static ArrayList<Double> list_beta_radioactivity = new ArrayList<>();
    public static ArrayList<Double> list_Cd = new ArrayList<>();
    public static ArrayList<Double> list_Cl = new ArrayList<>();
    public static ArrayList<Double> list_Co = new ArrayList<>();
    public static ArrayList<Double> list_colour = new ArrayList<>();
    public static ArrayList<Double> list_Cr = new ArrayList<>();
    public static ArrayList<Double> list_Cu = new ArrayList<>();
    public static ArrayList<Double> list_cyanide = new ArrayList<>();
    public static ArrayList<Double> list_DDT = new ArrayList<>();
    public static ArrayList<Double> list_dissolved_solid = new ArrayList<>();
    public static ArrayList<Double> list_Fe = new ArrayList<>();
    public static ArrayList<Double> list_fluoride = new ArrayList<>();
    public static ArrayList<Double> list_hardness = new ArrayList<>();
    public static ArrayList<Double> list_Hg = new ArrayList<>();
    public static ArrayList<Double> list_iodide = new ArrayList<>();
    public static ArrayList<Double> list_Mn = new ArrayList<>();
    public static ArrayList<Double> list_Mo = new ArrayList<>();
    public static ArrayList<Double> list_naked_eye = new ArrayList<>();
    public static ArrayList<Double> list_NH3 = new ArrayList<>();
    public static ArrayList<Double> list_Ni = new ArrayList<>();
    public static ArrayList<Double> list_NaNO2 = new ArrayList<>();
    public static ArrayList<Double> list_NO3 = new ArrayList<>();
    public static ArrayList<Double> list_odor = new ArrayList<>();
    public static ArrayList<Double> list_Pb = new ArrayList<>();
    public static ArrayList<Double> list_permanganate_index = new ArrayList<>();
    public static ArrayList<Double> list_pH = new ArrayList<>();
    public static ArrayList<Double> list_phenol = new ArrayList<>();
    public static ArrayList<Double> list_Se = new ArrayList<>();
    public static ArrayList<Double> list_SO3 = new ArrayList<>();
    public static ArrayList<Double> list_total_bacteria = new ArrayList<>();
    public static ArrayList<Double> list_total_coliform = new ArrayList<>();
    public static ArrayList<Double> list_turbidity = new ArrayList<>();
    public static ArrayList<Double> list_Zn = new ArrayList<>();

    /**
     * 某个水环境指数参数的预处理方法
     * @param elementName
     * @param v
     */
    public static void oneWaterElememtMethod( String elementName, double v) {
        switch (elementName) {
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
     *
     * @param list
     * @return
     */
    public static double getMax(ArrayList<Double> list) {
        if (list.isEmpty()) return -1;
        return Collections.max(list).floatValue();
    }

    public static double getAverage(ArrayList<Double> list) {
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

    private static double e_max = 0;
    private static double e_average = 0;
    private static double evaluation_quality = 0;

    /**
     * 某个水环境指数参数的评价计算方法
     *
     * @param elementName
     */
    public static double oneWaterElememtComputingMethod(String elementName) {
        evaluation_quality = 0;
        switch (elementName) {
            case w_666:
                evaluation_quality = computingEvaluation(list_666);
                break;
            case w_alpha_radioactivity:
                evaluation_quality = computingEvaluation(list_alpha_radioactivity);
                break;
            case w_anion_synthetic_detergent:
                evaluation_quality = computingEvaluation(list_anion_synthetic_detergent);
                break;
            case w_As:
                evaluation_quality = computingEvaluation(list_As);
                break;
            case w_Ba:
                evaluation_quality = computingEvaluation(list_Ba);
                break;
            case w_Be:
                evaluation_quality = computingEvaluation(list_Be);
                break;
            case w_beta_radioactivity:
                evaluation_quality = computingEvaluation(list_beta_radioactivity);
                break;
            case w_Cd:
                evaluation_quality = computingEvaluation(list_Cd);
                break;
            case w_Cl:
                evaluation_quality = computingEvaluation(list_Cl);
                break;
            case w_Co:
                evaluation_quality = computingEvaluation(list_Co);
                break;
            case w_colour:
                evaluation_quality = computingEvaluation(list_colour);
                break;
            case w_Cr:
                evaluation_quality = computingEvaluation(list_Cr);
                break;
            case w_Cu:
                evaluation_quality = computingEvaluation(list_Cu);
                break;
            case w_cyanide:
                evaluation_quality = computingEvaluation(list_cyanide);
                break;
            case w_DDT:
                evaluation_quality = computingEvaluation(list_DDT);
                break;
            case w_dissolved_solid:
                evaluation_quality = computingEvaluation(list_dissolved_solid);
                break;
            case w_Fe:
                evaluation_quality = computingEvaluation(list_Fe);
                break;
            case w_fluoride:
                evaluation_quality = computingEvaluation(list_fluoride);
                break;
            case w_hardness:
                evaluation_quality = computingEvaluation(list_hardness);
                break;
            case w_Hg:
                evaluation_quality = computingEvaluation(list_Hg);
                break;
            case w_iodide:
                evaluation_quality = computingEvaluation(list_iodide);
                break;
            case w_Mn:
                evaluation_quality = computingEvaluation(list_Mn);
                break;
            case w_Mo:
                evaluation_quality = computingEvaluation(list_Mo);
                break;
            case w_naked_eye:
                evaluation_quality = computingEvaluation(list_naked_eye);
                break;
            case w_NH3:
                evaluation_quality = computingEvaluation(list_NH3);
                break;
            case w_Ni:
                evaluation_quality = computingEvaluation(list_Ni);
                break;
            case w_NaNO2:
                evaluation_quality = computingEvaluation(list_NaNO2);
                break;
            case w_NO3:
                evaluation_quality = computingEvaluation(list_NO3);
                break;
            case w_odor:
                evaluation_quality = computingEvaluation(list_odor);
                break;
            case w_Pb:
                evaluation_quality = computingEvaluation(list_Pb);
                break;
            case w_permanganate_index:
                evaluation_quality = computingEvaluation(list_permanganate_index);
                break;
            case w_pH:
                evaluation_quality = computingEvaluation(list_pH);
                break;
            case w_phenol:
                evaluation_quality = computingEvaluation(list_phenol);
                break;
            case w_Se:
                evaluation_quality = computingEvaluation(list_Se);
                break;
            case w_SO3:
                evaluation_quality = computingEvaluation(list_SO3);
                break;
            case w_total_bacteria:
                evaluation_quality = computingEvaluation(list_total_bacteria);
                break;
            case w_total_coliform:
                evaluation_quality = computingEvaluation(list_total_coliform);
                break;
            case w_turbidity:
                evaluation_quality = computingEvaluation(list_turbidity);
                break;
            case w_Zn:
                evaluation_quality = computingEvaluation(list_Zn);
                break;
        }
        return evaluation_quality;
    }

    /**
     *
     * @param list
     * @return
     */
    private static double computingEvaluation(ArrayList<Double> list) {
        e_max = getMax(list);
        e_average = getAverage(list);
        list.clear();
        return  (float) Math.sqrt((e_max * e_max + e_average * e_average) / 2);
    }

}
