package com.savor.operations.single.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.api.widget.pulltorefresh.library.PullToRefreshBase;
import com.common.api.widget.pulltorefresh.library.PullToRefreshListView;
import com.savor.operations.single.R;
import com.savor.operations.single.adapter.HotelMacInfoAdapter;
import com.savor.operations.single.bean.Hotel;
import com.savor.operations.single.bean.HotelMacInfo;
import com.savor.operations.single.core.AppApi;

import java.util.List;

/**
 * 酒店版位详细信息(mac)
 */
public class HotelMacInfoActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener, View.OnClickListener {

    private Hotel mHotel;
    private View headerView;
    private TextView mHotelNameTv;
    private TextView mHotelAddressTv;
    private TextView mHotelBelongsTv;
    private TextView mIfImportantTv;
    private TextView mHotelLevelTv;
    private TextView mChangeDescTv;
    private TextView mInstallDateTv;
    private TextView mHotelStateTv;
    private TextView mHotelContantTv;
    private TextView mBoxTypeTv;
    private TextView mPreservePersonTv;
    private TextView mSpAddressTv;
    private TextView mSpMacTv;
    private TextView mPlaneTv;
    private TextView mTelePhoneTv;
    private TextView mOperationPersonTv;
    private TextView mHotelLocationTv;
    private TextView mHotelWifiNameTv;
    private TextView mProgramTv;
    private TextView mPositionInfoTv;
    private PullToRefreshListView mRefreshListView;
    private HotelMacInfoAdapter mAdapter;
    private TextView mSPId;
    private TextView mWifiPwdTv;
    private TextView mTitleTv;
    private ImageView mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail_info);

        handleIntent();
        getViews();
        setViews();
        setListeners();
        getData();
    }

    private void getData() {
        AppApi.getHotelMacInfo(this,mHotel.getId(),this);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        mHotel = (Hotel) intent.getSerializableExtra("hotel");
    }

    @Override
    public void getViews() {
        mTitleTv = (TextView) findViewById(R.id.tv_center);
        mBackBtn = (ImageView) findViewById(R.id.iv_left);

        mRefreshListView = (PullToRefreshListView) findViewById(R.id.wl_listview );

        headerView = View.inflate(this, R.layout.header_view_hotel_detail,null);
        mHotelNameTv = (TextView) headerView.findViewById(R.id.tv_hotel_name);
        mHotelAddressTv = (TextView) headerView.findViewById(R.id.tv_hotel_address);
        mHotelBelongsTv = (TextView) headerView.findViewById(R.id.tv_hotel_belongs);
        mIfImportantTv = (TextView) headerView.findViewById(R.id.tv_if_important);
        mHotelLevelTv = (TextView) headerView.findViewById(R.id.tv_hotel_level);
        mChangeDescTv = (TextView) headerView.findViewById(R.id.tv_change_desc);
        mInstallDateTv = (TextView) headerView.findViewById(R.id.tv_install_date);
        mHotelStateTv = (TextView) headerView.findViewById(R.id.tv_hotel_state);
        mHotelContantTv = (TextView) headerView.findViewById(R.id.tv_hotel_contant);
        mBoxTypeTv = (TextView) headerView.findViewById(R.id.tv_box_type);
        mPreservePersonTv = (TextView) headerView.findViewById(R.id.tv_preserve_person);
        mSpAddressTv = (TextView) headerView.findViewById(R.id.tv_sp_address);
        mSpMacTv = (TextView) headerView.findViewById(R.id.tv_sp_mac);
        mPlaneTv = (TextView) headerView.findViewById(R.id.tv_plane);
        mTelePhoneTv = (TextView) headerView.findViewById(R.id.tv_telephone);
        mOperationPersonTv = (TextView) headerView.findViewById(R.id.tv_operation_persion);
        mHotelLocationTv = (TextView) headerView.findViewById(R.id.tv_hotel_location);
        mHotelWifiNameTv = (TextView) headerView.findViewById(R.id.tv_hotel_wifiname);
        mProgramTv = (TextView) headerView.findViewById(R.id.tv_program);
        mPositionInfoTv = (TextView) headerView.findViewById(R.id.position_info);
        mSPId = (TextView) headerView.findViewById(R.id.tv_sp_id);
        mWifiPwdTv = (TextView) headerView.findViewById(R.id.tv_hotel_wifipwd);
    }

    @Override
    public void setViews() {
        mTitleTv.setText(mHotel.getName());
        mAdapter = new HotelMacInfoAdapter(this);
        mRefreshListView.setAdapter(mAdapter);
        mRefreshListView.getRefreshableView().addHeaderView(headerView);
    }

    @Override
    public void setListeners() {
        mRefreshListView.setOnRefreshListener(this);
        mBackBtn.setOnClickListener(this);
    }

    private void init(HotelMacInfo hotelMacInfo) {
        initHeaderView(hotelMacInfo);
        initMacList(hotelMacInfo);
    }

    private void initHeaderView(HotelMacInfo hotelMacInfo) {
        HotelMacInfo.MacInfo list = hotelMacInfo.getList();
        List<HotelMacInfo.MacInfo.HotelInfoBean> hotel_info = list.getHotel_info();
        if(hotel_info!=null&&hotel_info.size()>0) {
            HotelMacInfo.MacInfo.HotelInfoBean hotelInfoBean = hotel_info.get(0);
            mHotelNameTv.setText("酒店名称："+formatStr(hotelInfoBean.getHotel_name()));
            mHotelAddressTv.setText("酒店地址："+formatStr(hotelInfoBean.getHotel_addr()));
            mHotelBelongsTv.setText("所属区域："+formatStr(hotelInfoBean.getArea_name()));
            mIfImportantTv.setText("是否重点："+formatStr(hotelInfoBean.getIs_key()));
            mHotelLevelTv.setText("酒店级别："+formatStr(hotelInfoBean.getLevel()));
            mChangeDescTv.setText("变更说明："+formatStr(hotelInfoBean.getState_change_reason()));
            mInstallDateTv.setText("安装日期："+formatStr(hotelInfoBean.getInstall_date()));
            mHotelStateTv.setText("酒楼状态："+formatStr(hotelInfoBean.getHotel_state()));
            mHotelContantTv.setText("酒店联系人："+formatStr(hotelInfoBean.getContractor()));
            mBoxTypeTv.setText("机顶盒类型："+formatStr(hotelInfoBean.getHotel_box_type()));
            mPreservePersonTv.setText("合作维护人："+formatStr(hotelInfoBean.getMaintainer()));
            mSpAddressTv.setText("小平台存放位置："+formatStr(hotelInfoBean.getServer_location()));
            mPlaneTv.setText("座机："+formatStr(hotelInfoBean.getTel()));
            mSPId.setText("小平台远程ID："+formatStr(hotelInfoBean.getRemote_id()));
            mTelePhoneTv.setText("手机："+formatStr(hotelInfoBean.getMobile()));
            mOperationPersonTv.setText("技术运维人："+formatStr(hotelInfoBean.getTech_maintainer()));
            mSpMacTv.setText("小平台mac地址："+formatStr(hotelInfoBean.getMac_addr()));
            mWifiPwdTv.setText("酒楼wifi密码："+formatStr(hotelInfoBean.getHotel_wifi_pas()));
            mHotelLocationTv.setText("酒楼位置坐标："+formatStr(hotelInfoBean.getGps()));
            mHotelWifiNameTv.setText("酒楼wifi名称："+formatStr(hotelInfoBean.getHotel_wifi()));
            mProgramTv.setText("节目单："+formatStr(hotelInfoBean.getMenu_name()));
            mPositionInfoTv.setText("版位信息（包间："+hotelInfoBean.getRoom_num()+" 机顶盒"+hotelInfoBean.getBox_num()+" 电视："+hotelInfoBean.getTv_num()+"）");

        }
    }

    public String formatStr(String str) {
        return TextUtils.isEmpty(str)?"":str;
    }

    private void initMacList(HotelMacInfo hotelMacInfo) {
        if(hotelMacInfo !=null) {
            HotelMacInfo.MacInfo list = hotelMacInfo.getList();
            if(list!=null) {
                List<HotelMacInfo.MacInfo.PositionBean> position = list.getPosition();
                mAdapter.setData(position);
            }

        }
    }

    @Override
    public void onSuccess(AppApi.Action method, Object obj) {
        mRefreshListView.onRefreshComplete();
        switch (method) {
            case POST_HOTEL_MACINFO_JSON:
                if(obj instanceof HotelMacInfo) {
                    HotelMacInfo hotelMacInfo = (HotelMacInfo) obj;
                    init(hotelMacInfo);
                }
                break;
        }

    }


    @Override
    public void onError(AppApi.Action method, Object obj) {
        super.onError(method, obj);
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
