package com.upc.oa.dto;

import com.upc.oa.po.Vcomment;

public class VcommentDto extends Vcomment {
    private String uid;
    private Integer vid;
    private String userName;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
