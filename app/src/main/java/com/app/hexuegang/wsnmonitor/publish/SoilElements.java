package com.app.hexuegang.wsnmonitor.publish;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 土壤因子的各元素
 *
 * Created by HEXG on 2017/5/12.
 */

public class SoilElements {

    public static final String s_666 = "soil_666";
    public static final String s_As = "soil_As";
    public static final String s_Cd = "soil_Cd";
    public static final String s_Cr = "soil_Cr";
    public static final String s_Cu = "soil_Cu";
    public static final String s_DDT = "soil_DDT";
    public static final String s_Hg = "soil_Hg";
    public static final String s_Ni = "soil_Ni";
    public static final String s_Pb = "soil_Pb";
    public static final String s_Zn = "soil_Zn";

    public static ArrayList<Double> list_666 = new ArrayList<>();
    public static ArrayList<Double> list_As = new ArrayList<>();
    public static ArrayList<Double> list_Cd = new ArrayList<>();
    public static ArrayList<Double> list_Cr = new ArrayList<>();
    public static ArrayList<Double> list_Cu = new ArrayList<>();
    public static ArrayList<Double> list_DDT = new ArrayList<>();
    public static ArrayList<Double> list_Hg = new ArrayList<>();
    public static ArrayList<Double> list_Ni = new ArrayList<>();
    public static ArrayList<Double> list_Pb = new ArrayList<>();
    public static ArrayList<Double> list_Zn = new ArrayList<>();

    /**
     * 某个土壤环境指数参数的预处理方法
     * @param elementName
     * @param v
     */
    public static void oneSoilElememtMethod( String elementName, double v) {
        switch (elementName) {
            case s_666:
                list_666.add(v);
                break;
            case s_As:
                list_As.add(v);
                break;
            case s_Cd:
                list_Cd.add(v);
                break;
            case s_Cr:
                list_Cr.add(v);
                break;
            case s_Cu:
                list_Cu.add(v);
                break;
            case s_DDT:
                list_DDT.add(v);
                break;
            case s_Hg:
                list_Hg.add(v);
                break;
            case s_Ni:
                list_Ni.add(v);
                break;
            case s_Pb:
                list_Pb.add(v);
                break;
            case s_Zn:
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
     * 某个土壤环境指数参数的评价计算方法
     *
     * @param elementName
     */
    public static double oneSoilElememtComputingMethod(String elementName) {
        evaluation_quality = 0;
        switch (elementName) {
            case s_666:
                evaluation_quality = computingEvaluation(list_666);
                break;
            case s_As:
                evaluation_quality = computingEvaluation(list_As);
                break;
            case s_Cd:
                evaluation_quality = computingEvaluation(list_Cd);
                break;
            case s_Cr:
                evaluation_quality = computingEvaluation(list_Cr);
                break;
            case s_Cu:
                evaluation_quality = computingEvaluation(list_Cu);
                break;
            case s_DDT:
                evaluation_quality = computingEvaluation(list_DDT);
                break;
            case s_Hg:
                evaluation_quality = computingEvaluation(list_Hg);
                break;
            case s_Ni:
                evaluation_quality = computingEvaluation(list_Ni);
                break;
            case s_Pb:
                evaluation_quality = computingEvaluation(list_Pb);
                break;
            case s_Zn:
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
