package com.savor.operations.single;

import android.app.Application;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

import com.savor.operations.single.utils.ActivitiesManager;


/**
 * Created by zhanghq on 2016/12/19.
 */

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final Application mContext;
    private final Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    public ExceptionHandler(Application context) {
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        mContext = context;

    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();

        showCrashTips();

        // 退出并重启应用
        exitAndRestart();
    }

    private void showCrashTips() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "亲 ，程序出了点小问题即将重启哦", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();

        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }


    private void exitAndRestart() {
        ActivitiesManager.getInstance().popAllActivities();
        Process.killProcess(Process.myPid());
    }



}
