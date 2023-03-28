package com.upc.oa.po;


import java.util.List;

public class Blog {

  private Integer bid;
  private String btitle;
  private Integer bwriter;
  private String btext;
  private String btime;
  private Integer bviewNum;
  private Integer bpraise;

  private List<Tag> blogtags;
  private User user;
  private Btag btag;
  private Tag tag;
  private Ccomment ccomment;

  public List<Tag> getBlogtags() {
    return blogtags;
  }

  public void setBlogtags(List<Tag> blogtags) {
    this.blogtags = blogtags;
  }

  public Ccomment getCcomment() {
    return ccomment;
  }

  public void setCcomment(Ccomment ccomment) {
    this.ccomment = ccomment;
  }

  public Btag getBtag() {
    return btag;
  }

  public void setBtag(Btag btag) {
    this.btag = btag;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getBid() {
    return bid;
  }

  public void setBid(Integer bid) {
    this.bid = bid;
  }


  public String getBtitle() {
    return btitle;
  }

  public void setBtitle(String btitle) {
    this.btitle = btitle;
  }


  public Integer getBwriter() {
    return bwriter;
  }

  public void setBwriter(Integer bwriter) {
    this.bwriter = bwriter;
  }


  public String getBtext() {
    return btext;
  }

  public void setBtext(String btext) {
    this.btext = btext;
  }


  public String getBtime() {
    return btime;
  }

  public void setBtime(String btime) {
    this.btime = btime;
  }


  public Integer getBviewNum() {
    return bviewNum;
  }

  public void setBviewNum(Integer bviewNum) {
    this.bviewNum = bviewNum;
  }


  public Integer getBpraise() {
    return bpraise;
  }

  public void setBpraise(Integer bpraise) {
    this.bpraise = bpraise;
  }

  @Override
  public String toString() {
    return "Blog{" +
            "bid=" + bid +
            ", btitle='" + btitle + '\'' +
            ", bwriter=" + bwriter +
            ", btext='" + btext + '\'' +
            ", btime='" + btime + '\'' +
            ", bviewNum=" + bviewNum +
            ", bpraise=" + bpraise +
            '}';
  }
}
