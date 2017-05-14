package com.app.hexuegang.wsnmonitor.publish;

/**
 * Created by HEXG on 2017/4/19.
 */

public class MyConstants {

    public final static int QUERY_ITEM_LIMITS = 200;     // 查询结果条目限制个数
    public static final int REQUEST_CODE_POST_1 = 1;


    //获取数据的方式
    public final static int GET_DATA_MODE_FIRST = 0;     // 第一次请求数据
    public final static int GET_DATA_MODE_REFRESH = 1;     // 下拉刷新请求数据

    //某因子的元素个数
    public final static int ELEMENTS_NUMBER_WATER = 39;     // 水因子的元素个数



    public final static String ELEMENTS_NUMBER_TYPE_WATER = "elements_number_type_water";
    public final static String ELEMENTS_NUMBER_TYPE_OTHER = "elements_number_type_other";



}
