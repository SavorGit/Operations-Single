package com.savor.operations.single.bean;

import java.io.Serializable;

/**
 * Created by hezd on 2017/9/5.
 */

public class Hotel implements Serializable {

    /**
     * id : 53
     * name : 永峰写字楼(茶室)
     */

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != null ? !id.equals(hotel.id) : hotel.id != null) return false;
        return name != null ? name.equals(hotel.name) : hotel.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
