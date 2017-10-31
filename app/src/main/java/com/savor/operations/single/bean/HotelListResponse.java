package com.savor.operations.single.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索酒店返回列表数据
 * Created by hezd on 2017/9/5.
 */

public class HotelListResponse implements Serializable {

    private List<Hotel> list;

    public List<Hotel> getList() {
        return list;
    }

    public void setList(List<Hotel> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelListResponse that = (HotelListResponse) o;

        return list != null ? list.equals(that.list) : that.list == null;

    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HotelListResponse{" +
                "list=" + list +
                '}';
    }
}
