package com.app.hexuegang.wsnmonitor.app;

import android.app.Application;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by hexuegang on 2017/1/20.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    public static MyApplication getInstance(){
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initBmob();

    }

    private void initBmob() {
        BmobConfig config =new BmobConfig.Builder(myApplication)
                .setApplicationId("968f16f4fe79f35e87a1db95c594a18a")//设置appkey
                .setConnectTimeout(30)//请求超时时间（单位为秒）：默认15s
                .setUploadBlockSize(1024*1024)//文件分片上传时每片的大小（单位字节），默认512*1024
                .setFileExpiration(2500)//文件的过期时间(单位为秒)：默认1800s
                .build();
        Bmob.initialize(config);
    }
}
