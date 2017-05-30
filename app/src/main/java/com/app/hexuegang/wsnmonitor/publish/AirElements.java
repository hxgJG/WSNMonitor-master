package com.app.hexuegang.wsnmonitor.publish;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 大气因子的各元素
 *
 * Created by HEXG on 2017/5/12.
 */

public class AirElements {

    public static final String a_hour_BaP = "air_hour_BaP";
    public static final String a_month_BaP = "air_month_BaP";
    public static final String a_year_BaP = "air_year_BaP";

    public static final String a_hour_CO = "air_hour_CO";
    public static final String a_month_CO = "air_month_CO";
    public static final String a_year_CO = "air_year_CO";

    public static final String a_hour_NO2 = "air_hour_NO2";
    public static final String a_month_NO2 = "air_month_NO2";
    public static final String a_year_NO2 = "air_year_NO2";

    public static final String a_hour_NOx = "air_hour_NOx";
    public static final String a_month_NOx = "air_month_NOx";
    public static final String a_year_NOx = "air_year_NOx";

    public static final String a_hour_O3 = "air_hour_O3";
    public static final String a_month_O3 = "air_month_O3";
    public static final String a_year_O3 = "air_year_O3";

    public static final String a_hour_Pb = "air_hour_Pb";
    public static final String a_month_Pb = "air_month_Pb";
    public static final String a_year_Pb = "air_year_Pb";

    public static final String a_hour_PM2_5 = "air_hour_PM2.5";
    public static final String a_month_PM2_5 = "air_month_PM2.5";
    public static final String a_year_PM2_5 = "air_year_PM2.5";

    public static final String a_hour_PM10 = "air_hour_PM10";
    public static final String a_month_PM10 = "air_month_PM10";
    public static final String a_year_PM10 = "air_year_PM10";

    public static final String a_hour_SO2 = "air_hour_SO2";
    public static final String a_month_SO2 = "air_month_SO2";
    public static final String a_year_SO2 = "air_year_SO2";

    public static final String a_hour_TSP = "air_hour_TSP";
    public static final String a_month_TSP = "air_month_TSP";
    public static final String a_year_TSP = "air_year_TSP";


    public static ArrayList<Double> list_year_BaP = new ArrayList<>();
    public static ArrayList<Double> list_year_CO = new ArrayList<>();
    public static ArrayList<Double> list_year_NO2 = new ArrayList<>();
    public static ArrayList<Double> list_year_NOx = new ArrayList<>();
    public static ArrayList<Double> list_year_O3 = new ArrayList<>();
    public static ArrayList<Double> list_year_Pb = new ArrayList<>();
    public static ArrayList<Double> list_year_PM2_5 = new ArrayList<>();
    public static ArrayList<Double> list_year_PM10 = new ArrayList<>();
    public static ArrayList<Double> list_year_SO2 = new ArrayList<>();
    public static ArrayList<Double> list_year_TSP = new ArrayList<>();

    /**
     * 某个水环境指数参数的预处理方法
     * @param elementName
     * @param v
     */
    public static void oneAirElememtMethod( String elementName, double v) {
        switch (elementName) {
            case a_year_BaP:
                list_year_BaP.add(v);
                break;
            case a_year_CO:
                list_year_CO.add(v);
                break;
            case a_year_NO2:
                list_year_NO2.add(v);
                break;
            case a_year_NOx:
                list_year_NOx.add(v);
                break;
            case a_year_O3:
                list_year_O3.add(v);
                break;
            case a_year_Pb:
                list_year_Pb.add(v);
                break;
            case a_year_PM2_5:
                list_year_PM2_5.add(v);
                break;
            case a_year_PM10:
                list_year_PM10.add(v);
                break;
            case a_year_SO2:
                list_year_SO2.add(v);
                break;
            case a_year_TSP:
                list_year_TSP.add(v);
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
     * 某个大气环境指数参数的评价计算方法
     *
     * @param elementName
     */
    public static double oneAirElememtComputingMethod(String elementName) {
        evaluation_quality = 0;
        switch (elementName) {
            case a_year_BaP:
                evaluation_quality = computingEvaluation(list_year_BaP);
                break;
            case a_year_CO:
                evaluation_quality = computingEvaluation(list_year_CO);
                break;
            case a_year_NO2:
                evaluation_quality = computingEvaluation(list_year_NO2);
                break;
            case a_year_NOx:
                evaluation_quality = computingEvaluation(list_year_NOx);
                break;
            case a_year_O3:
                evaluation_quality = computingEvaluation(list_year_O3);
                break;
            case a_year_Pb:
                evaluation_quality = computingEvaluation(list_year_Pb);
                break;
            case a_year_PM2_5:
                evaluation_quality = computingEvaluation(list_year_PM2_5);
                break;
            case a_year_PM10:
                evaluation_quality = computingEvaluation(list_year_PM10);
                break;
            case a_year_SO2:
                evaluation_quality = computingEvaluation(list_year_SO2);
                break;
            case a_year_TSP:
                evaluation_quality = computingEvaluation(list_year_TSP);
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
