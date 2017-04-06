package hgq.example.com.jsontobean.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/26.
 */

public class Employee implements Serializable {
    private int id;

    private String mobile;

    private String name;

    private int status = 0;

    private String permission;

    private int barberShopId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getBarberShopId() {
        return barberShopId;
    }

    public void setBarberShopId(int barberShopId) {
        this.barberShopId = barberShopId;
    }
}
