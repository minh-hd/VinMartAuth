package com.fpt.vinmartauth.validation;

import android.util.Log;

import java.util.regex.Pattern;

public class AuthValidation {
  private final String EMAIL_PATTERN = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
  private final String PHONE_PATTERN = "^\\+?\\d{10,11}$";
  private final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\w\\d]).{8,20}$";

  public boolean isEmail(String s) {
    return s.matches(EMAIL_PATTERN);
  }

  public boolean isPhone(String s) {
    return s.matches(PHONE_PATTERN);
  }

  public boolean isValidPassword(String s) {
    return s.matches(PASSWORD_PATTERN);
  }
}
