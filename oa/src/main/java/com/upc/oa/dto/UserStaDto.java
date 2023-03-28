package com.upc.oa.dto;

import com.upc.oa.po.User;

public class UserStaDto extends User {
    private Integer dnum;
    private String address;

    public Integer getDnum() {
        return dnum;
    }

    public void setDnum(Integer dnum) {
        this.dnum = dnum;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }
}
