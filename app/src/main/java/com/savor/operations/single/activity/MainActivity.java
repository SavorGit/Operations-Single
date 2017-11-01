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
    private TextView mHotelNameTv;
    private TextView mLocationTv;
    private TextView mSearchTv;
    private FixBean fixBean;
    private Button mFixBtn;
    private Button mSignBtn;

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
        mHotelNameTv = (TextView) findViewById(R.id.tv_hotel);
        mLocationTv = (TextView) findViewById(R.id.tv_location);

        mFixBtn = (Button) findViewById(R.id.btn_fix);
        mSignBtn = (Button) findViewById(R.id.btn_sign);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void setListeners() {
        mSearchTv.setOnClickListener(this);
        mFixBtn.setOnClickListener(this);
        mSignBtn.setOnClickListener(this);
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
            case R.id.btn_fix:
                if(fixBean==null|| TextUtils.isEmpty(fixBean.getHotel_id())||TextUtils.isEmpty(fixBean.getBox_mac())) {
                    new CommonDialog(this, "请选择酒楼和版位", new CommonDialog.OnConfirmListener() {
                        @Override
                        public void onConfirm() {

                        }
                    }).show();
                }else {
                    new FixDialog(this, new FixDialog.OnSubmitBtnClickListener() {
                        @Override
                        public void onSubmitClick(FixDialog.OperationType type, PositionListInfo fixHistoryResponse, FixDialog.FixState isResolve, List<String> damageDesc, String comment, Hotel hotel) {

                        }
                    }, FixDialog.OperationType.TYPE_BOX,null,mSession.getDamageConfig(),null).show();
                }
                break;
            case R.id.tv_search:
                Intent intent = new Intent(this,SearchHotelActivity.class);
                startActivityForResult(intent,HotelPositionInfoAcitivty.REQUEST_CODE_SELECT);
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
