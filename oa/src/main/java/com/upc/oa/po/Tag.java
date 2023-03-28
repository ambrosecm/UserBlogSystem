package com.upc.oa.po;


public class Tag {

  private Integer tid;
  private String tname;

  private Blog blog;
  private Btag btag;

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

  public Btag getBtag() {
    return btag;
  }

  public void setBtag(Btag btag) {
    this.btag = btag;
  }

  public Integer getTid() {
    return tid;
  }

  public void setTid(Integer tid) {
    this.tid = tid;
  }


  public String getTname() {
    return tname;
  }

  public void setTname(String tname) {
    this.tname = tname;
  }

  @Override
  public String toString() {
    return "Tag{" +
            "tid=" + tid +
            ", tname='" + tname + '\'' +
            '}';
  }
}
