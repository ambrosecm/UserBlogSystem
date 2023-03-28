package com.upc.oa.dto;

import com.upc.oa.po.Type;
import com.upc.oa.po.Video;

public class VideoDto extends Video {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
