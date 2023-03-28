package com.upc.oa.po;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {

  private Integer uid;
  private String userName;
  private String password;
  private String upic;
  private String email;
  private String udate;
  private String phone;
  private Integer sex;
  private String ubirth;
  private String address;
  private Integer role;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date date;


  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }


  public String getUserName() {

    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getUpic() {
    return upic;
  }

  public void setUpic(String upic) {
    this.upic = upic;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getUdate() {
    return udate;
  }

  public void setUdate(String udate) {
    this.udate = udate;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }


  public String getUbirth() {
    return ubirth;
  }

  public void setUbirth(String ubirth) {
    this.ubirth = ubirth;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User{" +
            "uid=" + uid +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", upic='" + upic + '\'' +
            ", email='" + email + '\'' +
            ", udate='" + udate + '\'' +
            ", phone='" + phone + '\'' +
            ", sex=" + sex +
            ", ubirth='" + ubirth + '\'' +
            ", address='" + address + '\'' +
            ", role=" + role +
            '}';
  }
}
