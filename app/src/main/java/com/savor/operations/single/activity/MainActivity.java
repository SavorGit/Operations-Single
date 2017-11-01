package com.savor.operations.single.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.savor.operations.single.R;
import com.savor.operations.single.bean.FixBean;
import com.savor.operations.single.bean.Hotel;
import com.savor.operations.single.bean.LoginResponse;
import com.savor.operations.single.bean.PositionListInfo;
import com.savor.operations.single.utils.ActivitiesManager;
import com.savor.operations.single.widget.CommonDialog;
import com.savor.operations.single.widget.FixDialog;

import java.util.List;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private long exitTime;
    private TextView mSearchTv;
    private FixBean fixBean;
    private TextView mUserInfo;
    private Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        setViews();
        setListeners();
    }

    @Override
    public void getViews() {
        mSearchTv = (TextView) findViewById(R.id.tv_search);

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
}
