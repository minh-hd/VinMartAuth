package com.fpt.vinmartauth.entity;

//import androidx.annotation.RecentlyNonNull;

import java.util.Date;

public class Customer {
  private String fullName;
  private String phone;
  private String email;
  private String password;
  private Enum<Gender> gender;
  private Date dateOfBirth;
  private String profileImageLink;
  private String address;

  public Customer() {
  }

  public Customer(String fullName, String phone, String email, String address) {
    this.fullName = fullName;
    this.phone = phone;
    this.email = email;
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Customer(String fullName, String phone, String email, String password, Enum<Gender> gender, Date dateOfBirth, String profileImageLink) {
    this.fullName = fullName;
    this.phone = phone;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.profileImageLink = profileImageLink;
  }

  public Customer(String fullName, String phone, String email) {
    this.fullName = fullName;
    this.phone = phone;
    this.email = email;
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

  public Enum<Gender> getGender() {
    return gender;
  }

  public void setGender(Enum<Gender> gender) {
    this.gender = gender;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getProfileImageLink() {
    return profileImageLink;
  }

  public void setProfileImageLink(String profileImageLink) {
    this.profileImageLink = profileImageLink;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "fullName='" + fullName + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", gender=" + gender +
            ", dateOfBirth=" + dateOfBirth +
            ", profileImageLink='" + profileImageLink + '\'' +
            '}';
  }
}
