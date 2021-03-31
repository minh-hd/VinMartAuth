package com.fpt.vinmartauth.entity;


import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class User {
  private String fullName;
  private String phone;
  private String email;
  private String password;
  private String profileImageLink;

  public User() {
  }


  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getProfileImageLink() {
    return profileImageLink;
  }

  public void setProfileImageLink(String profileImageLink) {
    this.profileImageLink = profileImageLink;
  }

  @NotNull
  @Override
  public String toString() {
    return "Customer{" +
            "fullName='" + fullName + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", profileImageLink='" + profileImageLink + '\'' +
            '}';
  }
}
