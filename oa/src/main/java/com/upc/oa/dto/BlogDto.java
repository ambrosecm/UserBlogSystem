package com.upc.oa.dto;

import com.upc.oa.po.Blog;
import com.upc.oa.po.Tag;

import java.util.List;

public class BlogDto extends Blog {
    private String userName;
    private Integer uid;
    private Integer[] tags;



    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public Integer[] getTags() {
        return tags;
    }

    public void setTags(Integer[] tags) {
        this.tags = tags;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
