package com.savor.operations.single.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hezd on 2017/9/6.
 */

public class PositionListInfo implements Serializable {

    /**
     * list : {"version":{"last_heart_time":{"ltime":"2分","lstate":1},"last_small":{"last_small_pla":"1.1.6","last_small_state":0},"new_small":"1.0.24"},"box_info":[{"rname":"新板卡测试","boxname":"V600","mac":"A0BB3ED24A5F","ustate":0,"last_heart_time":"无","ltime":"-888","repair_record":[]},{"rname":"望海楼","boxname":"望海楼1","mac":"FCD5D900B3B1","ustate":0,"last_heart_time":"无","ltime":"-888","repair_record":[]},{"rname":"雄起","boxname":"雄起","mac":"FCD5D930B4E5","ustate":0,"last_heart_time":"无","ltime":"-888","repair_record":[{"nickname":"Admin","ctime":"2017-09-05 10:21:49"},{"nickname":"Admin","ctime":"2017-09-05 10:34:03"},{"nickname":"Admin","ctime":"2017-09-05 10:34:11"},{"nickname":"Admin","ctime":"2017-09-05 10:34:58"},{"nickname":"Admin","ctime":"2017-09-05 14:45:58"},{"nickname":"Admin","ctime":"2017-09-05 14:51:01"},{"nickname":"Admin","ctime":"2017-09-05 14:55:29"},{"nickname":"Admin","ctime":"2017-09-05 15:03:09"},{"nickname":"Admin","ctime":"2017-09-05 15:03:10"},{"nickname":"Admin","ctime":"2017-09-05 15:03:11"},{"nickname":"Admin","ctime":"2017-09-05 15:03:31"},{"nickname":"Admin","ctime":"2017-09-05 15:03:45"}]},{"rname":"何竹冬","boxname":"何竹冬","mac":"FCD5D900B842","last_heart_time":"15天20小时","ltime":1503302402,"ustate":0,"repair_record":[]},{"rname":"小九修改","boxname":"xiaojiu5g","mac":"FCD5D900B18F","last_heart_time":"2分","ltime":1504671601,"ustate":1,"repair_record":[]},{"rname":"毕超","boxname":"bicao only","mac":"FCD5D900B6E4","last_heart_time":"2分","ltime":1504671601,"ustate":1,"repair_record":[]},{"rname":"测试","boxname":"ceshizhuanyong","mac":"FCD5D900B6A4","last_heart_time":"2分","ltime":1504671601,"ustate":1,"repair_record":[]}],"banwei":"版位信息(共29个,失联超过15个小时23个)"}
     */

    private PositionInfo list;

    public PositionInfo getList() {
        return list;
    }

    public void setList(PositionInfo list) {
        this.list = list;
    }

    public static class PositionInfo implements Serializable {
        /**
         * version : {"last_heart_time":{"ltime":"2分","lstate":1},"last_small":{"last_small_pla":"1.1.6","last_small_state":0},"new_small":"1.0.24"}
         * box_info : [{"rname":"新板卡测试","boxname":"V600","mac":"A0BB3ED24A5F","ustate":0,"last_heart_time":"无","ltime":"-888","repair_record":[]},{"rname":"望海楼","boxname":"望海楼1","mac":"FCD5D900B3B1","ustate":0,"last_heart_time":"无","ltime":"-888","repair_record":[]},{"rname":"雄起","boxname":"雄起","mac":"FCD5D930B4E5","ustate":0,"last_heart_time":"无","ltime":"-888","repair_record":[{"nickname":"Admin","ctime":"2017-09-05 10:21:49"},{"nickname":"Admin","ctime":"2017-09-05 10:34:03"},{"nickname":"Admin","ctime":"2017-09-05 10:34:11"},{"nickname":"Admin","ctime":"2017-09-05 10:34:58"},{"nickname":"Admin","ctime":"2017-09-05 14:45:58"},{"nickname":"Admin","ctime":"2017-09-05 14:51:01"},{"nickname":"Admin","ctime":"2017-09-05 14:55:29"},{"nickname":"Admin","ctime":"2017-09-05 15:03:09"},{"nickname":"Admin","ctime":"2017-09-05 15:03:10"},{"nickname":"Admin","ctime":"2017-09-05 15:03:11"},{"nickname":"Admin","ctime":"2017-09-05 15:03:31"},{"nickname":"Admin","ctime":"2017-09-05 15:03:45"}]},{"rname":"何竹冬","boxname":"何竹冬","mac":"FCD5D900B842","last_heart_time":"15天20小时","ltime":1503302402,"ustate":0,"repair_record":[]},{"rname":"小九修改","boxname":"xiaojiu5g","mac":"FCD5D900B18F","last_heart_time":"2分","ltime":1504671601,"ustate":1,"repair_record":[]},{"rname":"毕超","boxname":"bicao only","mac":"FCD5D900B6E4","last_heart_time":"2分","ltime":1504671601,"ustate":1,"repair_record":[]},{"rname":"测试","boxname":"ceshizhuanyong","mac":"FCD5D900B6A4","last_heart_time":"2分","ltime":1504671601,"ustate":1,"repair_record":[]}]
         * banwei : 版位信息(共29个,失联超过15个小时23个)
         */

        private String banwei;
        private List<BoxInfoBean> box_info;

        @Override
        public String toString() {
            return "PositionInfo{" +
                    "banwei='" + banwei + '\'' +
                    ", box_info=" + box_info +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PositionInfo that = (PositionInfo) o;

            if (banwei != null ? !banwei.equals(that.banwei) : that.banwei != null) return false;
            return box_info != null ? box_info.equals(that.box_info) : that.box_info == null;
        }

        @Override
        public int hashCode() {
            int result = banwei != null ? banwei.hashCode() : 0;
            result = 31 * result + (box_info != null ? box_info.hashCode() : 0);
            return result;
        }

        public String getBanwei() {
            return banwei;
        }

        public void setBanwei(String banwei) {
            this.banwei = banwei;
        }

        public List<BoxInfoBean> getBox_info() {
            return box_info;
        }

        public void setBox_info(List<BoxInfoBean> box_info) {
            this.box_info = box_info;
        }

        public class BoxInfoBean implements Serializable {

            /**
             * rname : 新板卡测试
             * boxname : V600
             * mac : A0BB3ED24A5F
             * ustate : 0
             * last_heart_time : 无
             * ltime : -888
             * repair_record : []
             */

            private String rname;
            private String current_location;
            private String boxname;
            private String bid;
            private String mac;
            private String srtype;
            private String last_ctime;

            @Override
            public String toString() {
                return "BoxInfoBean{" +
                        "rname='" + rname + '\'' +
                        ", current_location='" + current_location + '\'' +
                        ", boxname='" + boxname + '\'' +
                        ", bid='" + bid + '\'' +
                        ", mac='" + mac + '\'' +
                        ", srtype='" + srtype + '\'' +
                        ", last_ctime='" + last_ctime + '\'' +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                BoxInfoBean that = (BoxInfoBean) o;

                if (rname != null ? !rname.equals(that.rname) : that.rname != null) return false;
                if (current_location != null ? !current_location.equals(that.current_location) : that.current_location != null)
                    return false;
                if (boxname != null ? !boxname.equals(that.boxname) : that.boxname != null)
                    return false;
                if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
                if (mac != null ? !mac.equals(that.mac) : that.mac != null) return false;
                if (srtype != null ? !srtype.equals(that.srtype) : that.srtype != null)
                    return false;
                return last_ctime != null ? last_ctime.equals(that.last_ctime) : that.last_ctime == null;
            }

            @Override
            public int hashCode() {
                int result = rname != null ? rname.hashCode() : 0;
                result = 31 * result + (current_location != null ? current_location.hashCode() : 0);
                result = 31 * result + (boxname != null ? boxname.hashCode() : 0);
                result = 31 * result + (bid != null ? bid.hashCode() : 0);
                result = 31 * result + (mac != null ? mac.hashCode() : 0);
                result = 31 * result + (srtype != null ? srtype.hashCode() : 0);
                result = 31 * result + (last_ctime != null ? last_ctime.hashCode() : 0);
                return result;
            }

            public String getRname() {
                return rname;
            }

            public void setRname(String rname) {
                this.rname = rname;
            }

            public String getCurrent_location() {
                return current_location;
            }

            public void setCurrent_location(String current_location) {
                this.current_location = current_location;
            }

            public String getBoxname() {
                return boxname;
            }

            public void setBoxname(String boxname) {
                this.boxname = boxname;
            }

            public String getBid() {
                return bid;
            }

            public void setBid(String bid) {
                this.bid = bid;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getSrtype() {
                return srtype;
            }

            public void setSrtype(String srtype) {
                this.srtype = srtype;
            }

            public String getLast_ctime() {
                return last_ctime;
            }

            public void setLast_ctime(String last_ctime) {
                this.last_ctime = last_ctime;
            }
        }
    }
}
