package com.savor.operations.single.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.savor.operations.single.R;
import com.savor.operations.single.bean.HotelMacInfo;

import java.util.List;

/**
 * 酒楼版位详细信息(mac)
 * Created by hezd on 2017/9/7.
 */

public class HotelMacInfoAdapter extends BaseAdapter {

    private final Context mContext;
    private List<HotelMacInfo.MacInfo.PositionBean> mData;

    public HotelMacInfoAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<HotelMacInfo.MacInfo.PositionBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null?0:mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_position_detail,null);
            holder.tv_box_name = (TextView) convertView.findViewById(R.id.tv_box_name);
            holder.tv_room_name = (TextView) convertView.findViewById(R.id.tv_room_name);
            holder.tv_mac = (TextView) convertView.findViewById(R.id.tv_mac);
            holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        HotelMacInfo.MacInfo.PositionBean bean = (HotelMacInfo.MacInfo.PositionBean) getItem(position);
        String room_name = bean.getRoom_name();
        String bmac_name = bean.getBmac_name();
        String bmac_addr = bean.getBmac_addr();
        String bstate = bean.getBstate();
        holder.tv_room_name.setText(room_name);
        holder.tv_box_name.setText(bmac_name);
        holder.tv_mac.setText(bmac_addr);
        holder.tv_state.setText(bstate);


        return convertView;
    }

    public class ViewHolder {
        public TextView tv_room_name;
        public TextView tv_box_name;
        public TextView tv_mac;
        public TextView tv_state;
    }
}
