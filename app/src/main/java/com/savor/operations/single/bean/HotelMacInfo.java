package com.savor.operations.single.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 酒楼版位详细信息（mac地址）
 * Created by hezd on 2017/9/7.
 */

public class HotelMacInfo implements Serializable {

    /**
     * list : {"hotel_info":[{"hotel_name":"永峰写字楼(测试)","hotel_addr":"北京市朝阳区建外郎家园16号永峰写字楼601","is_key":"重点","level":"3A","state_change_reason":"正常","install_date":"2017-02-15","hotel_state":"正常","contractor":"郑伟e","hotel_box_type":"三代5G版","maintainer":"郑伟34","tel":"","mobile":"13717711272","remote_id":"451145809","tech_maintainer":"成通","hotel_wifi_pas":"11111111","hotel_wifi":"测试专用","gps":"116.479251,39.912544","area_name":"北京市","mac_addr":"00E04CFA798D","server_location":"1113","menu_name":"系厚","room_num":25,"box_num":30,"tv_num":"61"}],"position":[{"room_name":"大会议室","bmac_name":"SONY","bmac_addr":"FCD5D900B83F","bstate":"正常"},{"room_name":"大厅","bmac_name":"前台等候区","bmac_addr":"FCD5D900B57E","bstate":"正常"},{"room_name":"大厅","bmac_name":"FunTV","bmac_addr":"FCD5D900B810","bstate":"正常"},{"room_name":"郭春城","bmac_name":"郭春城专用","bmac_addr":"FCD5D900B8B6","bstate":"正常"},{"room_name":"张海强","bmac_name":"找酒楼ID","bmac_addr":"FCD5D900B4D8","bstate":"正常"},{"room_name":"张海强","bmac_name":"找酒楼ID","bmac_addr":"FCD5D900B4D8","bstate":"正常"}]}
     */

    private MacInfo list;

    public MacInfo getList() {
        return list;
    }

    public void setList(MacInfo list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HotelMacInfo{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelMacInfo that = (HotelMacInfo) o;

        return list != null ? list.equals(that.list) : that.list == null;

    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }

    public static class MacInfo {
        private List<HotelInfoBean> hotel_info;
        private List<PositionBean> position;

        public List<HotelInfoBean> getHotel_info() {
            return hotel_info;
        }

        public void setHotel_info(List<HotelInfoBean> hotel_info) {
            this.hotel_info = hotel_info;
        }

        public List<PositionBean> getPosition() {
            return position;
        }

        public void setPosition(List<PositionBean> position) {
            this.position = position;
        }

        public class HotelInfoBean implements Serializable {
            /**
             * hotel_name : 永峰写字楼(测试)
             * hotel_addr : 北京市朝阳区建外郎家园16号永峰写字楼601
             * is_key : 重点
             * level : 3A
             * state_change_reason : 正常
             * install_date : 2017-02-15
             * hotel_state : 正常
             * contractor : 郑伟e
             * hotel_box_type : 三代5G版
             * maintainer : 郑伟34
             * tel :
             * mobile : 13717711272
             * remote_id : 451145809
             * tech_maintainer : 成通
             * hotel_wifi_pas : 11111111
             * hotel_wifi : 测试专用
             * gps : 116.479251,39.912544
             * area_name : 北京市
             * mac_addr : 00E04CFA798D
             * server_location : 1113
             * menu_name : 系厚
             * room_num : 25
             * box_num : 30
             * tv_num : 61
             */

            private String hotel_name;
            private String hotel_addr;
            private String is_key;
            private String level;
            private String state_change_reason;
            private String install_date;
            private String hotel_state;
            private String contractor;
            private String hotel_box_type;
            private String maintainer;
            private String tel;
            private String mobile;
            private String remote_id;
            private String tech_maintainer;
            private String hotel_wifi_pas;
            private String hotel_wifi;
            private String gps;
            private String area_name;
            private String mac_addr;
            private String server_location;
            private String menu_name;
            private int room_num;
            private int box_num;
            private String tv_num;

            public String getHotel_name() {
                return hotel_name;
            }

            public void setHotel_name(String hotel_name) {
                this.hotel_name = hotel_name;
            }

            public String getHotel_addr() {
                return hotel_addr;
            }

            public void setHotel_addr(String hotel_addr) {
                this.hotel_addr = hotel_addr;
            }

            public String getIs_key() {
                return is_key;
            }

            public void setIs_key(String is_key) {
                this.is_key = is_key;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getState_change_reason() {
                return state_change_reason;
            }

            public void setState_change_reason(String state_change_reason) {
                this.state_change_reason = state_change_reason;
            }

            public String getInstall_date() {
                return install_date;
            }

            public void setInstall_date(String install_date) {
                this.install_date = install_date;
            }

            public String getHotel_state() {
                return hotel_state;
            }

            public void setHotel_state(String hotel_state) {
                this.hotel_state = hotel_state;
            }

            public String getContractor() {
                return contractor;
            }

            public void setContractor(String contractor) {
                this.contractor = contractor;
            }

            public String getHotel_box_type() {
                return hotel_box_type;
            }

            public void setHotel_box_type(String hotel_box_type) {
                this.hotel_box_type = hotel_box_type;
            }

            public String getMaintainer() {
                return maintainer;
            }

            public void setMaintainer(String maintainer) {
                this.maintainer = maintainer;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getRemote_id() {
                return remote_id;
            }

            public void setRemote_id(String remote_id) {
                this.remote_id = remote_id;
            }

            public String getTech_maintainer() {
                return tech_maintainer;
            }

            public void setTech_maintainer(String tech_maintainer) {
                this.tech_maintainer = tech_maintainer;
            }

            public String getHotel_wifi_pas() {
                return hotel_wifi_pas;
            }

            public void setHotel_wifi_pas(String hotel_wifi_pas) {
                this.hotel_wifi_pas = hotel_wifi_pas;
            }

            public String getHotel_wifi() {
                return hotel_wifi;
            }

            public void setHotel_wifi(String hotel_wifi) {
                this.hotel_wifi = hotel_wifi;
            }

            public String getGps() {
                return gps;
            }

            public void setGps(String gps) {
                this.gps = gps;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public String getMac_addr() {
                return mac_addr;
            }

            public void setMac_addr(String mac_addr) {
                this.mac_addr = mac_addr;
            }

            public String getServer_location() {
                return server_location;
            }

            public void setServer_location(String server_location) {
                this.server_location = server_location;
            }

            public String getMenu_name() {
                return menu_name;
            }

            public void setMenu_name(String menu_name) {
                this.menu_name = menu_name;
            }

            public int getRoom_num() {
                return room_num;
            }

            public void setRoom_num(int room_num) {
                this.room_num = room_num;
            }

            public int getBox_num() {
                return box_num;
            }

            public void setBox_num(int box_num) {
                this.box_num = box_num;
            }

            public String getTv_num() {
                return tv_num;
            }

            public void setTv_num(String tv_num) {
                this.tv_num = tv_num;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                HotelInfoBean that = (HotelInfoBean) o;

                if (room_num != that.room_num) return false;
                if (box_num != that.box_num) return false;
                if (hotel_name != null ? !hotel_name.equals(that.hotel_name) : that.hotel_name != null)
                    return false;
                if (hotel_addr != null ? !hotel_addr.equals(that.hotel_addr) : that.hotel_addr != null)
                    return false;
                if (is_key != null ? !is_key.equals(that.is_key) : that.is_key != null)
                    return false;
                if (level != null ? !level.equals(that.level) : that.level != null) return false;
                if (state_change_reason != null ? !state_change_reason.equals(that.state_change_reason) : that.state_change_reason != null)
                    return false;
                if (install_date != null ? !install_date.equals(that.install_date) : that.install_date != null)
                    return false;
                if (hotel_state != null ? !hotel_state.equals(that.hotel_state) : that.hotel_state != null)
                    return false;
                if (contractor != null ? !contractor.equals(that.contractor) : that.contractor != null)
                    return false;
                if (hotel_box_type != null ? !hotel_box_type.equals(that.hotel_box_type) : that.hotel_box_type != null)
                    return false;
                if (maintainer != null ? !maintainer.equals(that.maintainer) : that.maintainer != null)
                    return false;
                if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
                if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null)
                    return false;
                if (remote_id != null ? !remote_id.equals(that.remote_id) : that.remote_id != null)
                    return false;
                if (tech_maintainer != null ? !tech_maintainer.equals(that.tech_maintainer) : that.tech_maintainer != null)
                    return false;
                if (hotel_wifi_pas != null ? !hotel_wifi_pas.equals(that.hotel_wifi_pas) : that.hotel_wifi_pas != null)
                    return false;
                if (hotel_wifi != null ? !hotel_wifi.equals(that.hotel_wifi) : that.hotel_wifi != null)
                    return false;
                if (gps != null ? !gps.equals(that.gps) : that.gps != null) return false;
                if (area_name != null ? !area_name.equals(that.area_name) : that.area_name != null)
                    return false;
                if (mac_addr != null ? !mac_addr.equals(that.mac_addr) : that.mac_addr != null)
                    return false;
                if (server_location != null ? !server_location.equals(that.server_location) : that.server_location != null)
                    return false;
                if (menu_name != null ? !menu_name.equals(that.menu_name) : that.menu_name != null)
                    return false;
                return tv_num != null ? tv_num.equals(that.tv_num) : that.tv_num == null;

            }

            @Override
            public int hashCode() {
                int result = hotel_name != null ? hotel_name.hashCode() : 0;
                result = 31 * result + (hotel_addr != null ? hotel_addr.hashCode() : 0);
                result = 31 * result + (is_key != null ? is_key.hashCode() : 0);
                result = 31 * result + (level != null ? level.hashCode() : 0);
                result = 31 * result + (state_change_reason != null ? state_change_reason.hashCode() : 0);
                result = 31 * result + (install_date != null ? install_date.hashCode() : 0);
                result = 31 * result + (hotel_state != null ? hotel_state.hashCode() : 0);
                result = 31 * result + (contractor != null ? contractor.hashCode() : 0);
                result = 31 * result + (hotel_box_type != null ? hotel_box_type.hashCode() : 0);
                result = 31 * result + (maintainer != null ? maintainer.hashCode() : 0);
                result = 31 * result + (tel != null ? tel.hashCode() : 0);
                result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
                result = 31 * result + (remote_id != null ? remote_id.hashCode() : 0);
                result = 31 * result + (tech_maintainer != null ? tech_maintainer.hashCode() : 0);
                result = 31 * result + (hotel_wifi_pas != null ? hotel_wifi_pas.hashCode() : 0);
                result = 31 * result + (hotel_wifi != null ? hotel_wifi.hashCode() : 0);
                result = 31 * result + (gps != null ? gps.hashCode() : 0);
                result = 31 * result + (area_name != null ? area_name.hashCode() : 0);
                result = 31 * result + (mac_addr != null ? mac_addr.hashCode() : 0);
                result = 31 * result + (server_location != null ? server_location.hashCode() : 0);
                result = 31 * result + (menu_name != null ? menu_name.hashCode() : 0);
                result = 31 * result + room_num;
                result = 31 * result + box_num;
                result = 31 * result + (tv_num != null ? tv_num.hashCode() : 0);
                return result;
            }

            @Override
            public String toString() {
                return "HotelInfoBean{" +
                        "hotel_name='" + hotel_name + '\'' +
                        ", hotel_addr='" + hotel_addr + '\'' +
                        ", is_key='" + is_key + '\'' +
                        ", level='" + level + '\'' +
                        ", state_change_reason='" + state_change_reason + '\'' +
                        ", install_date='" + install_date + '\'' +
                        ", hotel_state='" + hotel_state + '\'' +
                        ", contractor='" + contractor + '\'' +
                        ", hotel_box_type='" + hotel_box_type + '\'' +
                        ", maintainer='" + maintainer + '\'' +
                        ", tel='" + tel + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", remote_id='" + remote_id + '\'' +
                        ", tech_maintainer='" + tech_maintainer + '\'' +
                        ", hotel_wifi_pas='" + hotel_wifi_pas + '\'' +
                        ", hotel_wifi='" + hotel_wifi + '\'' +
                        ", gps='" + gps + '\'' +
                        ", area_name='" + area_name + '\'' +
                        ", mac_addr='" + mac_addr + '\'' +
                        ", server_location='" + server_location + '\'' +
                        ", menu_name='" + menu_name + '\'' +
                        ", room_num=" + room_num +
                        ", box_num=" + box_num +
                        ", tv_num='" + tv_num + '\'' +
                        '}';
            }
        }

        public  class PositionBean implements Serializable {
            /**
             * room_name : 大会议室
             * bmac_name : SONY
             * bmac_addr : FCD5D900B83F
             * bstate : 正常
             */

            private String room_name;
            private String bmac_name;
            private String bmac_addr;
            private String bstate;

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public String getBmac_name() {
                return bmac_name;
            }

            public void setBmac_name(String bmac_name) {
                this.bmac_name = bmac_name;
            }

            public String getBmac_addr() {
                return bmac_addr;
            }

            public void setBmac_addr(String bmac_addr) {
                this.bmac_addr = bmac_addr;
            }

            public String getBstate() {
                return bstate;
            }

            public void setBstate(String bstate) {
                this.bstate = bstate;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                PositionBean that = (PositionBean) o;

                if (room_name != null ? !room_name.equals(that.room_name) : that.room_name != null)
                    return false;
                if (bmac_name != null ? !bmac_name.equals(that.bmac_name) : that.bmac_name != null)
                    return false;
                if (bmac_addr != null ? !bmac_addr.equals(that.bmac_addr) : that.bmac_addr != null)
                    return false;
                return bstate != null ? bstate.equals(that.bstate) : that.bstate == null;

            }

            @Override
            public int hashCode() {
                int result = room_name != null ? room_name.hashCode() : 0;
                result = 31 * result + (bmac_name != null ? bmac_name.hashCode() : 0);
                result = 31 * result + (bmac_addr != null ? bmac_addr.hashCode() : 0);
                result = 31 * result + (bstate != null ? bstate.hashCode() : 0);
                return result;
            }

            @Override
            public String toString() {
                return "PositionBean{" +
                        "room_name='" + room_name + '\'' +
                        ", bmac_name='" + bmac_name + '\'' +
                        ", bmac_addr='" + bmac_addr + '\'' +
                        ", bstate='" + bstate + '\'' +
                        '}';
            }
        }
    }
}
