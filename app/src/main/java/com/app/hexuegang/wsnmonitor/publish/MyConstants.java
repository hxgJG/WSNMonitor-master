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


    public final static String SP_NAME_USER_INFO = "sharedpreferences_name_user_info";
    public final static String SP_KEY_USERNAME = "sharedpreferences_key_username";
    public final static String SP_KEY_PASSWORD = "sharedpreferences_key_password";
    public final static String SP_KEY_REMEMBER_PASSWORD = "sharedpreferences_key_remember_password";
    public final static String SP_KEY_AUTOMATIC_LOGON = "sharedpreferences_key_automatic_logon";



}
