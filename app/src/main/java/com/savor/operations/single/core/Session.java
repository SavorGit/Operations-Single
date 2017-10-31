
package com.savor.operations.single.core;

/*
 * Copyright (C) 2010 mAPPn.Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.common.api.codec.binary.Base64;
import com.common.api.utils.AppUtils;
import com.common.api.utils.LogUtils;
import com.common.api.utils.Pair;
import com.common.api.utils.SaveFileData;
import com.savor.operations.single.utils.STIDUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@SuppressLint("WorldReadableFiles")
public class Session implements Serializable{
    private final static String TAG = "Session";
    /**是否开启调试模式，调试模式连接海强机顶盒*/
    public static final boolean isDev = false;
    /**服务员推广数据*/
    private static final String P_APP_WAITER_DATA = "p_app_waiter_data";
    private int roomid;
    private int hotelid = 0;
    private String LocalIp;
    private Context mContext;
    private SaveFileData mPreference;
    private static  Session mInstance;
    public boolean isDebug = true;

    /** 是否已经显示引导图，没有显示则显示 */
    private boolean isNeedGuide = true;
    private boolean isScanGuide = true;
    private static final String P_APP_DEVICEID = "pref.savor.deviceid";
    private static final String P_APP_HOTELID = "pref.savor.hotelid";
    private static final String P_APP_PLATFORM_URL = "pref.savor.platformurl";
    private static final String P_APP_COLLECTIONS = "pref.savor.collections";
    private static final String P_APP_COLLECTIONS_URL = "pref.savor.collectionsurl";
    /**是否显示引导图*/
    private static final String P_APP_IS_SHOW_GUIDE = "version_v1.0";

    private static final String P_APP_IS_SHOW_SCAN_GUIDE = "isScanGuide";


    /**应用上次启动时间*/
    private static final String P_APP_LASTSTARTUP = "p_app_laststartup";
    /**首次播放蒙层提示引导图*/
    private static final String P_APP_FIRST_PLAY = "p_app_firstplay";
    /**启动配置*/
    private static final String APP_START_UP_SETTINGS = "app_start_up_settings";
    public static final String SLIDE_INTERVAL = "slide_interval";
    private long lastTime;
    /**
     * 设备deviceId
     */
    private static final String P_APP_NET_TYPE = "pref.savor.net_type";
    /**pdf浏览历史*/
    private static final String P_APP_PDF_LIST = "p_app_pdf_list";

    private static final String P_APP_AREA_ID = "p_app_area_id";
    /**首次使用*/
    private static final String P_APP_FIRST_USE = "p_app_first_use";
    /**最近可投屏酒店*/
    private static final String P_APP_HOTEL_MAP = "p_app_hotel_map";



    /**
     * The version of OS
     */
    private int osVersion;

    /**
     * The Application Debug flag
     */
    private String debugType;

    /**
     * The Application Version Code
     */
    private int versionCode;

    /**
     * The Application package name
     */
    private String packageName;

    /**
     * The Application version name
     */
    private String versionName;

    /**
     * The Application version name
     */
    private String appName;

    /**
     * The mobile IMEI code
     */
    private String imei = "";

    /**
     * The mobile sim code
     */
    private String sim;

    /**
     * The mobile mac address
     */
    private String macAddress;

    /**
     * The mobile model such as "Nexus One" Attention: some model type may have
     * illegal characters
     */
    private String model;

    /**
     * The user-visible version string. E.g., "1.0"
     */
    private String buildVersion;

    /**
     * 网络连接方式
     */
    private String netType;

    private String deviceid;

    /***/
    private String mTVBoxUrl;

    private int sessionID;
    /**是否首次播放引导图蒙层*/
    private boolean isFirstPlay = true;
    private String channelName;
    private String channelId;
    private double latestLat;
    private double latestLng;

    private Session(Context context) {

        mContext = context;
        mPreference = new SaveFileData(context, "savor");
        osVersion = Build.VERSION.SDK_INT;
        buildVersion = Build.VERSION.RELEASE;
        try {
            model = Build.MODEL;
            AppUtils.clearExpiredFile(context, false);
            AppUtils.clearExpiredCacheFile(context);
            readSettings();
        } catch (Exception e) {
           // LogUtils.e(e.getMessage());
        }
    }
    public void setNetType(String netType) {
        this.netType = netType;
        writePreference(new Pair<String, Object>(P_APP_NET_TYPE, netType));
    }

    public String getNetType() {
        return AppUtils.getNetworkType(mContext)+"";
    }
    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
        writePreference(new Pair<String, Object>(P_APP_LASTSTARTUP, lastTime));
    }
    public void setFirstPlay(boolean firstPlay) {
        this.isFirstPlay = firstPlay;
        writePreference(new Pair<String, Object>(P_APP_FIRST_PLAY, firstPlay));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSessionID() {
        return sessionID;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public boolean isNeedGuide() {
        return isNeedGuide;
    }

    public void setNeedGuide(boolean needGuide) {
        isNeedGuide = needGuide;
        writePreference(new Pair<String, Object>(P_APP_IS_SHOW_GUIDE, needGuide));
    }

    public boolean isScanGuide() {
        return isScanGuide;
    }

    public void setScanGuide(boolean scanGuide) {
        isScanGuide = scanGuide;
        writePreference(new Pair<String, Object>(P_APP_IS_SHOW_SCAN_GUIDE, scanGuide));
    }


    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public static Session get(Context context) {

        if (mInstance == null) {
            synchronized (Session.class) {
                if(mInstance == null) {
                    mInstance = new Session(context);
                }
            }
        }
        return mInstance;
    }


    private void readSettings() {
        deviceid = STIDUtil.getDeviceId(mContext);
        netType = mPreference.loadStringKey(P_APP_NET_TYPE, "");
        isNeedGuide = mPreference.loadBooleanKey(P_APP_IS_SHOW_GUIDE, isNeedGuide);
        isScanGuide = mPreference.loadBooleanKey(P_APP_IS_SHOW_SCAN_GUIDE, isScanGuide);
        lastTime = mPreference.loadLongKey(P_APP_LASTSTARTUP,0);
        isFirstPlay = mPreference.loadBooleanKey(P_APP_FIRST_PLAY,true);

        setDeviceid(deviceid);
        getApplicationInfo();

        /** 清理App缓存 */
        AppUtils.clearExpiredFile(mContext, false);

        /** 刷机防止操作 */
//        readDeviceNum();
    }

    /**获取存放压缩图片的目录*/
    public String getCompressPath(Context context) {
        String path = null;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
            path+=path.endsWith(File.separator)?"":File.separator;
            path+=".Gallery/";
        }else {
            path = context.getCacheDir().getAbsolutePath();
            path+=path.endsWith(File.separator)?"":File.separator;
            path+=".Gallery/";
        }
        LogUtils.d("gallery path:"+path);
        return path;
    }

    /*
     * 读取App配置信息
     */
    private void getApplicationInfo() {

        final PackageManager pm = mContext.getPackageManager();
        try {
            final PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                    0);
            versionName = pi.versionName;
            versionCode = pi.versionCode;

            final ApplicationInfo ai = pm.getApplicationInfo(
                    mContext.getPackageName(), PackageManager.GET_META_DATA);
			channelName = ai.metaData.get("UMENG_CHANNEL").toString();
			channelId = STIDUtil.getChannelIdByChannelName(channelName);
            debugType = ai.metaData.get("app_debug").toString();

            if ("1".equals(debugType)) {
                // developer mode
                isDebug = true;
            } else if ("0".equals(debugType)) {
                // release mode
                isDebug = false;
            }
            LogUtils.allow = isDebug;

            appName = String.valueOf(ai.loadLabel(pm));
            LogUtils.appTagPrefix = appName;
            packageName = mContext.getPackageName();

            TelephonyManager telMgr = (TelephonyManager) mContext
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imei = telMgr.getDeviceId();
            sim = telMgr.getSimSerialNumber();
        } catch (NameNotFoundException e) {
            Log.d(TAG, "met some error when get application info");
        }
    }

    public void readDeviceNum() {
//        String path = AppUtils.getPath(mContext, StorageFile.other);
//        String readNum;
//        File destFile = new File(path, "num.txt");
//        try {
//            if (destFile.exists()) {
//                readNum = FileUtils.readFileToString(destFile);
//                if (!TextUtils.isEmpty(readNum)) {
//                }
//            } else {
//                readNum = getMac() + "-" + System.currentTimeMillis();
//                destFile.createNewFile();
//                FileUtils.writeStringToFile(destFile, readNum);
//            }
//        } catch (Exception e) {
//            LogUtils.e(e.toString());
//        }

    }


    /**
     * 移除用户添加的Key，轻易不要使用
     *
     * @param key
     */
    public void removeKey(String key) {
        mPreference.removeKey(key);
    }

    private void writePreference(Pair<String, Object> updateItem) {
        //
        // // the preference key
        final String key = (String) updateItem.first;

        if ("".equals(key) ||P_APP_PLATFORM_URL.equals(key)
                ||P_APP_AREA_ID.equals(key)
                || P_APP_WAITER_DATA.equals(key)) {
            mPreference.saveStringKey(key, (String) updateItem.second);
        }else if(P_APP_IS_SHOW_GUIDE.equals(key)
                ||P_APP_FIRST_PLAY.equals(key)
                ||P_APP_IS_SHOW_SCAN_GUIDE.equals(key)
                ||P_APP_FIRST_USE.equals(key)){
            mPreference.saveBooleanKey(key,(boolean)updateItem.second);
        }else if(SLIDE_INTERVAL.equals(key)||P_APP_HOTELID.equals(key)){
            mPreference.saveIntKey(key,(Integer) updateItem.second);
        }else if(P_APP_LASTSTARTUP.equals(key)) {
            mPreference.saveLongKey(key,(Long)updateItem.second);
        }
// else if () {
//            mPreference.saveBooleanKey(key, (boolean) updateItem.second);
//        }
    else {//  默认存放对象Object这样的数据 情况特殊，一般类型的数据最好还是写上键值对，方便操作
            String string = ObjectToString(updateItem.second);
            mPreference.saveStringKey(key, string);
        }
    }

    private Object getObj(String key) {
        String string = mPreference.loadStringKey(key, "");
        Object object = null;
        if (!TextUtils.isEmpty(string)) {
            try {
                object = StringToObject(string);
            } catch (Exception ex) {
                Log.e("wang", "异常" + ex.toString());
            }
        }
        return object;
    }

    private void setObj(String key, Object obj) {
        try {
            writePreference(new Pair<String, Object>(key, obj));
        } catch (Exception ex) {
            Log.e("wang", ex.toString());
        }
    }

    private String ObjectToString(Object obj) {
        String productBase64 = null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            productBase64 = new String(Base64.encodeBase64(baos.toByteArray()));
        } catch (Exception e) {
            Log.e("错误", "保存错误" + e.toString());
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return productBase64;
    }

    private Object StringToObject(String str) {
        Object obj = null;
        byte[] base64Bytes;
        ByteArrayInputStream bais = null;
        try {
            String productBase64 = str;
            if (null == productBase64
                    || TextUtils.isEmpty(productBase64.trim())) {
                return null;
            }

            base64Bytes = Base64.decodeBase64(productBase64.getBytes());
            bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
            ois.close();
        } catch (Exception e) {
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }



    public int getVersionCode() {
        if (versionCode <= 0) {
            getApplicationInfo();
        }
        return versionCode;
    }


    public String getMac() {
        if (TextUtils.isEmpty(macAddress)) {
            try {
                WifiManager wifi = (WifiManager) mContext
                        .getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
                macAddress = info.getMacAddress();
            } catch (Exception ex) {
                LogUtils.e(ex.toString());
            }
        }
        return macAddress;
    }


    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
        writePreference(new Pair<String, Object>(P_APP_DEVICEID, deviceid));
    }


    /**
     * 返回设备相关信息
     */
    public String getDeviceInfo() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("versionname=");
        buffer.append(versionName);
        buffer.append(";versioncode=");
        buffer.append(versionCode);
        buffer.append(";buildversion=");
        buffer.append(buildVersion);
        buffer.append(";osversion=");
        buffer.append(osVersion);
        buffer.append(";model=");
        buffer.append(model);
        buffer.append(";appname=");
        buffer.append("hotSpot");
        buffer.append(";clientname=");
        buffer.append("android");
        buffer.append(";channelid=");
        buffer.append(channelId);
        buffer.append(";channelName=");
        buffer.append(channelName);
        buffer.append(";deviceid=");
        buffer.append(deviceid);
        buffer.append(";network=");
        /** 需要修改 */
        buffer.append(getNetType());
        buffer.append(";language=");
        buffer.append("");
        buffer.append(";location=");
        buffer.append(latestLng + "," + latestLat);
        return buffer.toString();
    }
    //        buffer.append(";imei=");
//        buffer.append(imei);
//        buffer.append(";sim=");
//        buffer.append(sim);
//        buffer.append(";macaddress=");
//        buffer.append(getMac());

//        buffer.append(";source=");
//        buffer.append("22");

//        TimeZone timeZone = TimeZone.getDefault();
//        buffer.append(";systemtimezone=");
//        buffer.append(timeZone.getID());

    public void setLatestLat(double latestLat) {
        this.latestLat = latestLat;
    }

    public void setLatestLng(double latestLng) {
        this.latestLng = latestLng;
    }

    public double getLatestLat() {
        return latestLat;
    }

    public double getLatestLng() {
        return latestLng;
    }
}