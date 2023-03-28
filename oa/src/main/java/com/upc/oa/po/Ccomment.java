package com.upc.oa.po;


import java.util.List;

public class Ccomment {

  private Integer ccid;
  private String cctime;
  private Integer ccpraise;
  private Integer ccwriterId;
  private Integer ccBlogId;
  private String cctext;
  private Integer ccparentId;

  private List<Ccomment> childCcomments;

 private Blog blog;
  private User user;

  public Blog getBlog() {
    return blog;
  }

    public List<Ccomment> getChildCcomments() {
        return childCcomments;
    }

    public void setChildCcomments(List<Ccomment> childCcomments) {
        this.childCcomments = childCcomments;
    }

    public void setBlog(Blog blog) {
    this.blog = blog;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getCcid() {
    return ccid;
  }

  public void setCcid(Integer ccid) {
    this.ccid = ccid;
  }


  public String getCctime() {
    return cctime;
  }

  public void setCctime(String cctime) {
    this.cctime = cctime;
  }


  public Integer getCcpraise() {
    return ccpraise;
  }

  public void setCcpraise(Integer ccpraise) {
    this.ccpraise = ccpraise;
  }


  public Integer getCcwriterId() {
    return ccwriterId;
  }

  public void setCcwriterId(Integer ccwriterId) {
    this.ccwriterId = ccwriterId;
  }


  public Integer getCcBlogId() {
    return ccBlogId;
  }

  public void setCcBlogId(Integer ccBlogId) {
    this.ccBlogId = ccBlogId;
  }


  public String getCctext() {
    return cctext;
  }

  public void setCctext(String cctext) {
    this.cctext = cctext;
  }


  public Integer getCcparentId() {
    return ccparentId;
  }

  public void setCcparentId(Integer ccparentId) {
    this.ccparentId = ccparentId;
  }

  @Override
  public String toString() {
    return "Ccomment{" +
            "ccid=" + ccid +
            ", cctime='" + cctime + '\'' +
            ", ccpraise=" + ccpraise +
            ", ccwriterId=" + ccwriterId +
            ", ccBlogId=" + ccBlogId +
            ", cctext='" + cctext + '\'' +
            ", ccparentId=" + ccparentId +
            '}';
  }
}
