package com.savor.operations.single.activity;

import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.savor.operations.single.R;
import com.savor.operations.single.utils.ActivitiesManager;

public class MainActivity extends BaseActivity {

    private long exitTime;
    private TextView mHotelNameTv;
    private TextView mLocationTv;
    private TextView mSearchTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getViews() {
        mSearchTv = (TextView) findViewById(R.id.tv_search);
        mHotelNameTv = (TextView) findViewById(R.id.tv_hotel);
        mLocationTv = (TextView) findViewById(R.id.tv_location);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void setListeners() {

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

}
