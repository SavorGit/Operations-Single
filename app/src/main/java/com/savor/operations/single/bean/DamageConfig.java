package com.savor.operations.single.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 损坏配置表
 * Created by hezd on 2017/9/7.
 */

public class DamageConfig implements Serializable {

    private List<DamageInfo> list;

    public List<DamageInfo> getList() {
        return list;
    }

    public void setList(List<DamageInfo> list) {
        this.list = list;
    }

    /**
     * 损坏信息
     */
    public static class DamageInfo implements Serializable {
        /**
         * id : 1
         * reason : 线坏了
         */

        private int id;
        private String reason;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        @Override
        public String toString() {
            return "DamageInfo{" +
                    "id=" + id +
                    ", reason='" + reason + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DamageInfo that = (DamageInfo) o;

            if (id != that.id) return false;
            return reason != null ? reason.equals(that.reason) : that.reason == null;

        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (reason != null ? reason.hashCode() : 0);
            return result;
        }
    }
}
