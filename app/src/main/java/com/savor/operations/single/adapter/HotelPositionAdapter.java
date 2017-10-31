package com.savor.operations.single.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.savor.operations.single.R;
import com.savor.operations.single.bean.PositionListInfo;

import java.util.List;

/**
 * 酒楼版位信息适配器
 * Created by hezd on 2017/9/5.
 */

public class HotelPositionAdapter extends BaseAdapter {

    private final Context mContext;
    private List<PositionListInfo.PositionInfo.BoxInfoBean> data;
    private OnFixBtnClickListener mOnFixBtnClickListener;

    public HotelPositionAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<PositionListInfo.PositionInfo.BoxInfoBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_hotel_position,null);
            holder.tv_last_optime = convertView.findViewById(R.id.tv_last_optime);
            holder.tv_box_info = convertView.findViewById(R.id.tv_box_info);
            holder.tv_last_operation = convertView.findViewById(R.id.tv_last_operation);
            holder.divider = convertView.findViewById(R.id.divider);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        final PositionListInfo.PositionInfo.BoxInfoBean boxInfoBean = (PositionListInfo.PositionInfo.BoxInfoBean) getItem(position);

        holder.tv_box_info.setText(boxInfoBean.getRname()+" "+boxInfoBean.getMac()+" "+boxInfoBean.getBoxname());

        String last_ctime = boxInfoBean.getLast_ctime();
        String srtype = boxInfoBean.getSrtype();
        if(!TextUtils.isEmpty(srtype)) {
            holder.tv_last_operation.setText("最后操作状态："+srtype);
        }
        if(!TextUtils.isEmpty(last_ctime)) {
            holder.tv_last_optime.setText("最后操作时间："+last_ctime);
        }else {
            holder.tv_last_optime.setText("最后操作时间：无");
        }

        return convertView;
    }

    public class ViewHolder {
        public View divider;
        public TextView tv_box_info;
        public TextView tv_last_operation;
        public TextView tv_last_optime;
    }

    public interface OnFixBtnClickListener {
        void onFixBtnClick(int position, PositionListInfo.PositionInfo.BoxInfoBean boxInfoBean);
    }

    public void setOnFixBtnClickListener(OnFixBtnClickListener listener) {
        this.mOnFixBtnClickListener = listener;
    }

}
