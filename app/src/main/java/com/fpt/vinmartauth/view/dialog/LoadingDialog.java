package com.fpt.vinmartauth.view.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.fpt.vinmartauth.R;

public class LoadingDialog {
  private Activity activity;
  private AlertDialog dialog;

  public LoadingDialog(Activity activity) {
    this.activity = activity;
  }

  @SuppressLint("InflateParams")
  public void startLoadingDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    LayoutInflater inflater = activity.getLayoutInflater();
    builder.setView(inflater.inflate(R.layout.loading_spinner, null));
    builder.setCancelable(false);

    dialog = builder.create();
    dialog.show();
  }

  public void dismissDialog() {
    dialog.dismiss();
  }
}
