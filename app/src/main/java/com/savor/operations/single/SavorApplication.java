package com.savor.operations.single;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.common.api.utils.AppUtils;
import com.savor.operations.single.core.Session;
import com.savor.operations.single.utils.LocationService;

import java.io.File;

/**
 * 全局application
 * Created by hezd on 2016/12/13.
 */

public class SavorApplication extends Application {

    private static SavorApplication mInstance;
    public String imagePath;
    public LocationService locationService;


    public static SavorApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 设置异常捕获处理类
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        Session.get(this);
        mInstance = this;
        initCacheFile();
        locationService = new LocationService(getApplicationContext());
    }

    private void initCacheFile() {
        String cachePath = AppUtils.getSDCardPath()+"savor"+ File.separator;
        imagePath = cachePath+File.separator+"operations-single"+File.separator+"cache";
    }
}
