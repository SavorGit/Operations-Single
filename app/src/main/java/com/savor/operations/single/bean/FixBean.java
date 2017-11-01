package com.savor.operations.single.bean;

import java.io.Serializable;

/**
 * 维修实体类
 * Created by hezd on 2017/10/31.
 */

public class FixBean implements Serializable {
    private String hotel_id;
    private String box_mac;
    private String boxname;
    private String remark;
    private String repair_img;
    private String repair_type;
    private String srtype;
    private String state;

    @Override
    public String toString() {
        return "FixBean{" +
                "hotel_id='" + hotel_id + '\'' +
                ", box_mac='" + box_mac + '\'' +
                ", boxname='" + boxname + '\'' +
                ", remark='" + remark + '\'' +
                ", repair_img='" + repair_img + '\'' +
                ", repair_type='" + repair_type + '\'' +
                ", srtype='" + srtype + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FixBean fixBean = (FixBean) o;

        if (hotel_id != null ? !hotel_id.equals(fixBean.hotel_id) : fixBean.hotel_id != null)
            return false;
        if (box_mac != null ? !box_mac.equals(fixBean.box_mac) : fixBean.box_mac != null)
            return false;
        if (boxname != null ? !boxname.equals(fixBean.boxname) : fixBean.boxname != null)
            return false;
        if (remark != null ? !remark.equals(fixBean.remark) : fixBean.remark != null) return false;
        if (repair_img != null ? !repair_img.equals(fixBean.repair_img) : fixBean.repair_img != null)
            return false;
        if (repair_type != null ? !repair_type.equals(fixBean.repair_type) : fixBean.repair_type != null)
            return false;
        if (srtype != null ? !srtype.equals(fixBean.srtype) : fixBean.srtype != null) return false;
        return state != null ? state.equals(fixBean.state) : fixBean.state == null;
    }

    @Override
    public int hashCode() {
        int result = hotel_id != null ? hotel_id.hashCode() : 0;
        result = 31 * result + (box_mac != null ? box_mac.hashCode() : 0);
        result = 31 * result + (boxname != null ? boxname.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (repair_img != null ? repair_img.hashCode() : 0);
        result = 31 * result + (repair_type != null ? repair_type.hashCode() : 0);
        result = 31 * result + (srtype != null ? srtype.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getBox_mac() {
        return box_mac;
    }

    public void setBox_mac(String box_mac) {
        this.box_mac = box_mac;
    }

    public String getBoxname() {
        return boxname;
    }

    public void setBoxname(String boxname) {
        this.boxname = boxname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRepair_img() {
        return repair_img;
    }

    public void setRepair_img(String repair_img) {
        this.repair_img = repair_img;
    }

    public String getRepair_type() {
        return repair_type;
    }

    public void setRepair_type(String repair_type) {
        this.repair_type = repair_type;
    }

    public String getSrtype() {
        return srtype;
    }

    public void setSrtype(String srtype) {
        this.srtype = srtype;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
