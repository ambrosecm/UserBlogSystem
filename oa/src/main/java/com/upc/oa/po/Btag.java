package com.upc.oa.po;


public class Btag {

  private Integer bid;
  private Integer tid;




  public Integer getBid() {
    return bid;
  }

  public void setBid(Integer bid) {
    this.bid = bid;
  }


  public Integer getTid() {
    return tid;
  }

  public void setTid(Integer tid) {
    this.tid = tid;
  }

  @Override
  public String toString() {
    return "Btag{" +
            "bid=" + bid +
            ", tid=" + tid +
            '}';
  }
}
