package com.savor.operations.single.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.savor.operations.single.R;
import com.savor.operations.single.adapter.HotelPositionAdapter;
import com.savor.operations.single.bean.DamageConfig;
import com.savor.operations.single.bean.PositionListInfo;
import com.savor.operations.single.bean.Hotel;
import com.savor.operations.single.core.AppApi;

import java.util.List;

/**
 * 酒楼版位信息
 */
public class HotelPositionInfoAcitivty extends BaseActivity implements  View.OnClickListener {

    private ListView mPostionListView;
    private ImageView mBackBtn;
    private TextView mTitleTv;
    private HotelPositionAdapter mHotelPositionAdapter;
    private Hotel mHotel;
    private TextView mSpVersionTv;
    private TextView mLastSpVersionTv;
    private TextView mLastXintiao;
    private ImageView mSpState;
    private ImageView mLastXintiaoIV;
    private TextView mPositionDesc;
    private DamageConfig damageConfig;
    private PositionListInfo positionListInfo;
    private TextView mFixHintTv;
    private RecyclerView mSpHistoryRlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_info_acitivty);

        handleIntent();
        getViews();
        setViews();
        setListeners();

        getData();
        getDamageInfo();

    }

    private void getDamageInfo() {
        AppApi.getDamageConfig(this,this);
    }

    private void getData() {
        AppApi.getPositionList(this,mHotel.getId(),this);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        mHotel = (Hotel) intent.getSerializableExtra("hotel");
    }

    @Override
    public void getViews() {
        View headerView = View.inflate(this,R.layout.header_view_position_list,null);
        mPositionDesc = (TextView) headerView.findViewById(R.id.tv_position_desc);
        mBackBtn = (ImageView) findViewById(R.id.iv_left);
        mTitleTv = (TextView) findViewById(R.id.tv_center);
        mPostionListView = (ListView) findViewById(R.id.lv_hotel_position_list);
        mPostionListView.addHeaderView(headerView);

        damageConfig = mSession.getDamageConfig();
    }

    @Override
    public void setViews() {
        mBackBtn.setOnClickListener(this);
        mHotelPositionAdapter = new HotelPositionAdapter(this);
        mPostionListView.setAdapter(mHotelPositionAdapter);

        if(mHotel!=null) {
            mTitleTv.setVisibility(View.VISIBLE);
            mTitleTv.setText(mHotel.getName());
            mTitleTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
            mTitleTv.getPaint().setAntiAlias(true);//抗锯齿
        }


    }

    @Override
    public void setListeners() {
        mTitleTv.setOnClickListener(this);
    }

    @Override
    public void onSuccess(AppApi.Action method, Object obj) {
        switch (method) {
            case POST_POSITION_LIST_JSON:
                if(obj instanceof PositionListInfo) {
                    mPostionListView.setVisibility(View.VISIBLE);
                    positionListInfo = (PositionListInfo) obj;
                    if(positionListInfo!=null) {
                        PositionListInfo.PositionInfo list = positionListInfo.getList();
                        if(list!=null) {
                            String banwei = list.getBanwei();
                            mPositionDesc.setText(banwei);
                            List<PositionListInfo.PositionInfo.BoxInfoBean> box_info = list.getBox_info();
                            mHotelPositionAdapter.setData(box_info);
                        }
                    }
                }
                break;
            case POST_DAMAGE_CONFIG_JSON:
                if(obj instanceof DamageConfig) {
                    damageConfig = (DamageConfig) obj;
                    List<DamageConfig.DamageInfo> list = damageConfig.getList();
                    if(list!=null&&list.size()>0) {
                        mSession.setDamageConfig(damageConfig);
                    }
                }
                break;
        }
    }

    @Override
    public void onError(AppApi.Action method, Object obj) {
        super.onError(method, obj);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_center:
//                Intent intent = new Intent(this,HotelMacInfoActivity.class);
//                intent.putExtra("hotel",mHotel);
//                startActivity(intent);
                break;
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
