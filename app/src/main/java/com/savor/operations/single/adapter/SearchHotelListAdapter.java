package com.savor.operations.single.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.savor.operations.single.R;
import com.savor.operations.single.bean.Hotel;

import java.util.List;

/**
 * 搜索酒店列表
 * Created by hezd on 2017/9/5.
 */

public class SearchHotelListAdapter extends RecyclerView.Adapter<SearchHotelListAdapter.HotelListHolder> {
    private final Context mContext;
    private List<Hotel> mData;
    private OnHoteClickListener mOnHotelClickListener;

    public SearchHotelListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Hotel> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public HotelListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotelListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_hotel_list,parent,false));
    }

    @Override
    public void onBindViewHolder(HotelListHolder holder, int position) {
        final Hotel hotel = mData.get(position);
        holder.hotelNameTv.setText(hotel.getName());
        int itemCount = getItemCount();
        if(itemCount == 0 || position == itemCount -1) {
            holder.divider.setVisibility(View.GONE);
        }else {
            holder.divider.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnHotelClickListener!=null) {
                    mOnHotelClickListener.onHotelClick(hotel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    public class HotelListHolder extends RecyclerView.ViewHolder {
        public TextView hotelNameTv;
        public View divider;

        public HotelListHolder(View itemView) {
            super(itemView);
            hotelNameTv = (TextView) itemView.findViewById(R.id.tv_hotel_name);
            divider = itemView.findViewById(R.id.divider);
        }

    }

    public interface OnHoteClickListener {
        void onHotelClick(Hotel hotel);
    }

    public void setOnHotelClickListener(OnHoteClickListener listener) {
        this.mOnHotelClickListener = listener;
    }
}
