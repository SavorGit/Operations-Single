package com.savor.operations.single.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.api.utils.ShowMessage;
import com.savor.operations.single.R;
import com.savor.operations.single.adapter.SearchHotelListAdapter;
import com.savor.operations.single.bean.Hotel;
import com.savor.operations.single.bean.HotelListResponse;
import com.savor.operations.single.core.AppApi;

import java.util.List;

public class SearchHotelActivity extends BaseActivity implements View.OnClickListener, SearchHotelListAdapter.OnHoteClickListener {

    private ImageView mBackBtn;
    private EditText mSearchEt;
    private RecyclerView mHotelListView;
    private SearchHotelListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);
    }

    @Override
    public void getViews() {
        mBackBtn = (ImageView) findViewById(R.id.iv_left);
        mSearchEt = (EditText) findViewById(R.id.et_search_hotel);
        mHotelListView = (RecyclerView) findViewById(R.id.rlv_hotel_list);
    }

    @Override
    public void setViews() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mHotelListView.setLayoutManager(manager);
        mAdapter = new SearchHotelListAdapter(this);
        mHotelListView.setAdapter(mAdapter);
    }

    @Override
    public void setListeners() {
        mBackBtn.setOnClickListener(this);
        mAdapter.setOnHotelClickListener(this);
        mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String content = mSearchEt.getText().toString();
                    if (!TextUtils.isEmpty(content)) {
                        AppApi.searchHotel(SearchHotelActivity.this, content, SearchHotelActivity.this);
                    } else {
                        ShowMessage.showToast(SearchHotelActivity.this, "请输入酒楼名称");
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    @Override
    public void onHotelClick(Hotel hotel) {
//        Intent intent = new Intent(this,HotelPositionInfoAcitivty.class);
//        intent.putExtra("hotel",hotel);
//        startActivity(intent);
    }

    @Override
    public void onSuccess(AppApi.Action method, Object obj) {
        switch (method) {
            case POST_SEARCH_HOTEL_JSON:
                if(obj instanceof HotelListResponse) {
                    HotelListResponse data = (HotelListResponse) obj;
                    List<Hotel> list = data.getList();
                    if(list!=null&&list.size()>0) {
                        mAdapter.setData(list);
                    }else {
                        ShowMessage.showToast(this,"无相关酒楼");
                    }
                }
                break;
        }
    }

    @Override
    public void onError(AppApi.Action method, Object obj) {
        super.onError(method, obj);
    }
}