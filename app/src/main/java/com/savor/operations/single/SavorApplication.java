package com.savor.operations.single;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.common.api.utils.AppUtils;
import com.common.api.utils.LogUtils;
import com.google.gson.Gson;
import com.savor.operations.single.core.Session;

import java.io.File;
import java.util.Map;

/**
 * 全局application
 * Created by hezd on 2016/12/13.
 */

public class SavorApplication extends Application {

    private static SavorApplication mInstance;
    public String imagePath;


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
    }

    private void initCacheFile() {
        String cachePath = AppUtils.getSDCardPath()+"savor"+ File.separator;
        imagePath = cachePath+File.separator+"operations-single"+File.separator+"cache";
    }
}
