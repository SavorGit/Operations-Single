package com.savor.operations.single.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.common.api.http.callback.FileDownProgress;
import com.common.api.utils.AppUtils;
import com.common.api.utils.LogUtils;
import com.common.api.utils.ShowMessage;
import com.savor.operations.single.R;
import com.savor.operations.single.SavorApplication;
import com.savor.operations.single.bean.FixBean;
import com.savor.operations.single.bean.LoginResponse;
import com.savor.operations.single.bean.UpgradeInfo;
import com.savor.operations.single.core.AppApi;
import com.savor.operations.single.utils.ActivitiesManager;
import com.savor.operations.single.utils.LocationService;
import com.savor.operations.single.utils.STIDUtil;
import com.savor.operations.single.widget.CommonDialog;
import com.savor.operations.single.widget.UpgradeDialog;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.savor.operations.single.core.AppApi.Action.POST_UPGRADE_JSON;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int NOTIFY_DOWNLOAD_FILE = 10001;
    private long exitTime;
    private TextView mSearchTv;
    private FixBean fixBean;
    private TextView mUserInfo;
    private Button mLogoutBtn;
    private UpgradeInfo upGradeInfo;
    private UpgradeDialog mUpgradeDialog;
    private NotificationManager manager;
    private int msg;
    private Notification notif;
    private LocationService locationService;
    private TextView mLocationTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setViews();
        setListeners();
        upgrade();

    }

    private void upgrade(){
        AppApi.Upgrade(mContext,this,mSession.getVersionCode());
    }

    @Override
    public void getViews() {
        mSearchTv = (TextView) findViewById(R.id.tv_search);
        mLocationTv = (TextView) findViewById(R.id.tv_location);

        mUserInfo = (TextView) findViewById(R.id.tv_user);
        mLogoutBtn = (Button) findViewById(R.id.btn_logout);
    }

    @Override
    public void setViews() {
        LoginResponse loginResponse = mSession.getLoginResponse();
        if(loginResponse!=null) {
            String nickname = loginResponse.getNickname();
            mUserInfo.setText("当前登录用户："+nickname);
        }
    }

    @Override
    public void setListeners() {
        mSearchTv.setOnClickListener(this);
        mLogoutBtn.setOnClickListener(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast(getString(R.string.confirm_exit_app));
                exitTime = System.currentTimeMillis();
            } else {
                exitApp();
            }
        }
        return true;
    }

    private void exitApp() {
        ActivitiesManager.getInstance().popAllActivities();
        Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
                new CommonDialog(this, "是否退出？", new CommonDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        mSession.setLoginResponse(null);
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }, new CommonDialog.OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                },"确定").show();
                break;
            case R.id.tv_search:
                Intent intent = new Intent(this,SearchHotelActivity.class);
                startActivityForResult(intent,HotelPositionInfoAcitivty.REQUEST_CODE_SELECT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == HotelPositionInfoAcitivty.RESULT_CODE_SELECT) {
            if(data!=null) {
                fixBean = (FixBean) data.getSerializableExtra("bean");
            }
        }
    }

    @Override
    public void onSuccess(AppApi.Action method, Object obj) {
        super.onSuccess(method, obj);
        switch (method) {

            case POST_UPGRADE_JSON:
                if(isFinishing())
                    return;
                if (obj instanceof UpgradeInfo) {
                    upGradeInfo = (UpgradeInfo) obj;
                    if (upGradeInfo != null) {
                        setUpGrade();
                    }
                }
                break;
            case TEST_DOWNLOAD_JSON:
                if (obj instanceof FileDownProgress){
                    FileDownProgress fs = (FileDownProgress) obj;
                    long now = fs.getNow();
                    long total = fs.getTotal();
                    if ((int)(((float)now / (float)total)* 100)-msg>=5) {
                        msg = (int) (((float)now / (float)total)* 100);
                        notif.contentView.setTextViewText(R.id.content_view_text1,
                                (Integer) msg + "%");
                        notif.contentView.setProgressBar(R.id.content_view_progress,
                                100, (Integer) msg, false);
                        manager.notify(NOTIFY_DOWNLOAD_FILE, notif);
                    }

                }else if (obj instanceof File) {
                    mSession.setApkDownloading(false);
                    File f = (File) obj;
                    byte[] fRead;
                    String md5Value=null;
                    try {
                        fRead = FileUtils.readFileToByteArray(f);
                        md5Value= AppUtils.getMD5(fRead);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //比较本地文件版本是否与服务器文件一致，如果一致则启动安装
                    if (md5Value!=null&&md5Value.equals(upGradeInfo.getMd5())){
                        if (manager!=null){
                            manager.cancel(NOTIFY_DOWNLOAD_FILE);
                        }
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.setDataAndType(Uri.parse("file://" + f.getAbsolutePath()),
                                "application/vnd.android.package-archive");
                        startActivity(i);
                    }else {
                        if (manager!=null){
                            manager.cancel(NOTIFY_DOWNLOAD_FILE);
                        }
                        ShowMessage.showToast(mContext,"下载失败");
                        setUpGrade();
                    }

                }
                break;
        }
    }

    private void setUpGrade(){
        String upgradeUrl = upGradeInfo.getOss_addr();
        //String upgradeUrl = "http://a5.pc6.com/pc6_soure/2016-2/com.huiche360.huiche_8.apk";

        if (!TextUtils.isEmpty(upgradeUrl)) {
            if (STIDUtil.needUpdate(mSession, upGradeInfo)) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(getString(R.string.home_update),"ensure");
                String[] content = upGradeInfo.getRemark();
                if (upGradeInfo.getUpdate_type() == 1) {
                    mUpgradeDialog = new UpgradeDialog(
                            mContext,
                            TextUtils.isEmpty(upGradeInfo.getVersion_code()+"")?"":"新版本：V"+upGradeInfo.getVersion_code(),
                            content,
                            this.getString(R.string.confirm),
                            forUpdateListener
                    );
                    mUpgradeDialog.show();
                }else {
                    mUpgradeDialog = new UpgradeDialog(
                            mContext,
                            TextUtils.isEmpty(upGradeInfo.getVersion_code()+"")?"":"新版本：V"+upGradeInfo.getVersion_code(),
                            content,
                            this.getString(R.string.cancel),
                            this.getString(R.string.confirm),
                            cancelListener,
                            forUpdateListener
                    );
                    mUpgradeDialog.show();
                }


            }
        }


    }

    private View.OnClickListener forUpdateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mUpgradeDialog.dismiss();
            downLoadApk(upGradeInfo.getOss_addr());
            // downLoadApk("http://download.savorx.cn/app-xiaomi-debug.apk");
        }
    };

    protected void downLoadApk(String apkUrl) {
        if (!mSession.isApkDownloading()){
            mSession.setApkDownloading(true);
            // 下载apk包
            initNotification();
            AppApi.downApp(mContext,apkUrl, MainActivity.this);
        }else{
            ShowMessage.showToast(mContext,"下载中,请稍候");
        }
    }

    /**
     * 初始化Notification
     */
    private void initNotification() {
        manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notif = new Notification();
        notif.icon = R.mipmap.ic_launcher;
        notif.tickerText = "下载通知";
        // 通知栏显示所用到的布局文件
        notif.contentView = new RemoteViews(mContext.getPackageName(),
                R.layout.download_content_view);
        notif.contentIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(
                mContext.getPackageName()+".debug"), PendingIntent.FLAG_CANCEL_CURRENT);
        // notif.defaults = Notification.DEFAULT_ALL;
        manager.notify(NOTIFY_DOWNLOAD_FILE, notif);
    }

    private View.OnClickListener cancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mUpgradeDialog.dismiss();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        startLocation();
    }


    @Override
    protected void onStop() {
        locationService.unregisterListener(mLocationListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();

    }

    private void startLocation() {
        locationService = ((SavorApplication)getApplication()).locationService;
        locationService.registerListener(mLocationListener);
        locationService.start();
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
                String addr = bdLocation.getAddrStr();    //获取详细地址信息
                String desc = bdLocation.getLocationDescribe();    //获取国家
                mSession.setLatestLat(latitude);
                mSession.setLatestLng(longitude);
                if(!TextUtils.isEmpty(addr)&&!TextUtils.isEmpty(desc)) {
                    mLocationTv.setText("当前位置："+addr+" "+desc);
                }
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
