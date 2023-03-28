package com.upc.oa.po;


public class Video {

  private Integer vid;
  private String vtitle;
  private Integer vtypeId;
  private String vdescription;
  private String vsrc;
  private String vtime;
  private String vteacher;
  private String vlong;
  private String voutline;
  private Integer vplayNum;
  private Integer vpraise;
  private Double vprice;


//  private Video video;
//
//  public Video getVideo() {
//    return video;
//  }
//
//  public void setVideo(Video video) {
//    this.video = video;
//  }
//
//  public Integer getVid() {
//    return vid;
//  }
//
//  public void setVid(Integer vid) {
//    this.vid = vid;
//  }

  private Type type;

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Integer getVid() {
    return vid;
  }

  public void setVid(Integer vid) {
    this.vid = vid;
  }



  public String getVtitle() {
    return vtitle;
  }

  public void setVtitle(String vtitle) {
    this.vtitle = vtitle;
  }


  public Integer getVtypeId() {
    return vtypeId;
  }

  public void setVtypeId(Integer vtypeId) {
    this.vtypeId = vtypeId;
  }


  public String getVdescription() {
    return vdescription;
  }

  public void setVdescription(String vdescription) {
    this.vdescription = vdescription;
  }


  public String getVsrc() {
    return vsrc;
  }

  public void setVsrc(String vsrc) {
    this.vsrc = vsrc;
  }


  public String getVtime() {
    return vtime;
  }

  public void setVtime(String vtime) {
    this.vtime = vtime;
  }


  public String getVteacher() {
    return vteacher;
  }

  public void setVteacher(String vteacher) {
    this.vteacher = vteacher;
  }


  public String getVlong() {
    return vlong;
  }

  public void setVlong(String vlong) {
    this.vlong = vlong;
  }


  public String getVoutline() {
    return voutline;
  }

  public void setVoutline(String voutline) {
    this.voutline = voutline;
  }


  public Integer getVplayNum() {
    return vplayNum;
  }

  public void setVplayNum(Integer vplayNum) {
    this.vplayNum = vplayNum;
  }


  public Integer getVpraise() {
    return vpraise;
  }

  public void setVpraise(Integer vpraise) {
    this.vpraise = vpraise;
  }


  public Double getVprice() {
    return vprice;
  }

  public void setVprice(Double vprice) {
    this.vprice = vprice;
  }

  @Override
  public String toString() {
    return "Video{" +
            "vid=" + vid +
            ", vtitle='" + vtitle + '\'' +
            ", vtypeId=" + vtypeId +
            ", vdescription='" + vdescription + '\'' +
            ", vsrc='" + vsrc + '\'' +
            ", vtime='" + vtime + '\'' +
            ", vteacher='" + vteacher + '\'' +
            ", vlong='" + vlong + '\'' +
            ", voutline='" + voutline + '\'' +
            ", vplayNum=" + vplayNum +
            ", vpraise=" + vpraise +
            ", vprice=" + vprice +
            ", type=" + type +
            '}';
  }
}
