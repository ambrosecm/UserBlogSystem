package com.upc.oa.po;


import java.util.List;

public class Vcomment {

  private Integer vcid;
  private String vctime;
  private Integer vcpraise;
  private Integer vcwriterId;
  private Integer vcVideoId;
  private String vctext;
  private Integer vcparentId;

  private User user;
  private Video video;
  private List<Vcomment> childVcomments;


  public List<Vcomment> getChildVcomments() {
    return childVcomments;
  }

  public void setChildVcomments(List<Vcomment> childVcomments) {
    this.childVcomments = childVcomments;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Video getVideo() {
    return video;
  }

  public void setVideo(Video video) {
    this.video = video;
  }

  public Integer getVcid() {
    return vcid;
  }

  public void setVcid(Integer vcid) {
    this.vcid = vcid;
  }


  public String getVctime() {
    return vctime;
  }

  public void setVctime(String vctime) {
    this.vctime = vctime;
  }


  public Integer getVcpraise() {
    return vcpraise;
  }

  public void setVcpraise(Integer vcpraise) {
    this.vcpraise = vcpraise;
  }


  public Integer getVcwriterId() {
    return vcwriterId;
  }

  public void setVcwriterId(Integer vcwriterId) {
    this.vcwriterId = vcwriterId;
  }


  public Integer getVcVideoId() {
    return vcVideoId;
  }

  public void setVcVideoId(Integer vcVideoId) {
    this.vcVideoId = vcVideoId;
  }


  public String getVctext() {
    return vctext;
  }

  public void setVctext(String vctext) {
    this.vctext = vctext;
  }


  public Integer getVcparentId() {
    return vcparentId;
  }

  public void setVcparentId(Integer vcparentId) {
    this.vcparentId = vcparentId;
  }

  @Override
  public String toString() {
    return "Vcomment{" +
            "vcid=" + vcid +
            ", vctime='" + vctime + '\'' +
            ", vcpraise=" + vcpraise +
            ", vcwriterId=" + vcwriterId +
            ", vcVideoId=" + vcVideoId +
            ", vctext='" + vctext + '\'' +
            ", vcparentId=" + vcparentId +
            '}';
  }
}
