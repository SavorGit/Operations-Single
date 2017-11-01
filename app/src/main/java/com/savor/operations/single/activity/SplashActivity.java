package com.savor.operations.single.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.common.api.utils.LogUtils;
import com.savor.operations.single.R;
import com.savor.operations.single.SavorApplication;
import com.savor.operations.single.bean.LoginResponse;
import com.savor.operations.single.utils.LocationService;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    private LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);

        new Handler(){}.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginResponse loginResponse = mSession.getLoginResponse();
                if(loginResponse == null|| TextUtils.isEmpty(loginResponse.getUserid())) {
                    launchLoginActivity();
                }else {
                    launchMainActivity();
                }
            }
        },2000);

        startLocation();
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void startLocation() {
        locationService = ((SavorApplication)getApplication()).locationService;
        locationService.registerListener(mLocationListener);
        locationService.start();
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void getViews() {

    }

    @Override
    public void setViews() {

    }

    @Override
    public void setListeners() {

    }

    /*****
     *
     * 点击首页底部悬浮窗进行定位结果回调
     *
     */
    private BDAbstractLocationListener mLocationListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (null != bdLocation && bdLocation.getLocType() != BDLocation.TypeServerError) {
                double latitude = bdLocation.getLatitude();
                double longitude = bdLocation.getLongitude();
                String addrStr = bdLocation.getAddrStr();
                String locationDescribe = bdLocation.getLocationDescribe();
                String currentLocation = addrStr+" "+locationDescribe;
                mSession.setLatestLat(latitude);
                mSession.setLatestLng(longitude);
                mSession.setCurrentLocation(currentLocation);
            }
        }

//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
//                double latitude = location.getLatitude();
//                double longitude = location.getLongitude();
//                LogUtils.d("operations:location lat="+latitude+",lng="+longitude);
//                mSession.setLatestLat(latitude);
//                mSession.setLatestLng(longitude);
//            }
//        }
//
//        public void onConnectHotSpotMessage(String s, int i){
//            LogUtils.d("savor:location onconnect = "+s);
//        }
    };
}
