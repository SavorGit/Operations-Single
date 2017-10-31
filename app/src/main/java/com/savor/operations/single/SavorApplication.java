package com.savor.operations.single;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.common.api.utils.LogUtils;
import com.google.gson.Gson;
import com.savor.operations.single.core.Session;

import java.util.Map;

/**
 * 全局application
 * Created by hezd on 2016/12/13.
 */

public class SavorApplication extends Application {

    private static SavorApplication mInstance;


    public static SavorApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Debug.startMethodTracing("operation");

        // 设置异常捕获处理类
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
//        MobclickAgent.openActivityDurationTrack(false);
        Session.get(this);
        mInstance = this;
//        Config.DEBUG = true;
//        UMShareAPI.get(this);
//        initCachePath();
//        initUmengPush();
    }

//    private void initUmengPush() {
//        PushAgent pushAgent = PushAgent.getInstance(this);
//        pushAgent.register(new IUmengRegisterCallback() {
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回device token
//                LogUtils.d("savor:push deviceToken="+deviceToken);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//
//            }
//        });
//
//        /**
//         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
//         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
//         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
//         * */
//        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
//            @Override
//            public void dealWithCustomAction(Context context, UMessage msg) {
//                // 点击收到推送，友盟埋点
//                Map<String,String> custom = msg.extra;
//                boolean isRunning = ActivitiesManager.getInstance().contains(MainActivity.class);
//                String type = custom.get("type");
//                String params = custom.get("params");
//                if("1".equals(type)) {
//                    if(!isRunning) {
//                        Intent intent = new Intent(context, SplashActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                }else if("2".equals(type)) {
//                    if(!isRunning) {
//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                    PushCardIdBean bean = new Gson().fromJson(params, PushCardIdBean.class);
//                    if(bean!=null) {
//                        Intent intent = new Intent(SavorApplication.this, CardDetailActivity.class);
//                        intent.putExtra("dailyid",bean.getDailyid());
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                }
//
//            }
//        };
//
//        UmengMessageHandler messageHandler = new UmengMessageHandler(){
//            @Override
//            public void dealWithNotificationMessage(Context context, UMessage uMessage) {
//                super.dealWithNotificationMessage(context, uMessage);
//                // 收到推送
//            }
//        };
//        pushAgent.setMessageHandler(messageHandler);
//        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
//        //CustomNotificationHandler notificationClickHandler = new CustomNotificationHandler();
//        pushAgent.setNotificationClickHandler(notificationClickHandler);
//        pushAgent.setDebugMode(false);
//    }

//    //各个平台的配置，建议放在全局Application或者程序入口
//    {
//        PlatformConfig.setWeixin("wxa5ea2522d1a6785e", "b45b77981f464f0a0b88b6c09ff74673");
//    }
}
