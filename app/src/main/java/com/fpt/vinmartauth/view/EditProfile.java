package com.fpt.vinmartauth.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.User;
import com.fpt.vinmartauth.view.authentication.AuthenticationController;
import com.fpt.vinmartauth.view.authentication.UserController;
import com.fpt.vinmartauth.view.dialog.LoadingDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class EditProfile extends AppCompatActivity {

  private ImageView btnBack;
  private ImageView imgAvatar;
  private Button btnCancel;
  private Button btnSave;
  private EditText edtFullName;
  private EditText edtEmail;
  private EditText edtPhone;
  private TextView tvChangePicture;
  private TextView tvChangePassword;
  private Uri filePath;
  private SwipeRefreshLayout refreshLayout;
  private UserController userController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_profile);

    imgAvatar = findViewById(R.id.imageProfile);

    userController = new UserController();

    tvChangePassword = findViewById(R.id.tvChangePassword);
    tvChangePassword.setOnClickListener(v -> {
      FirebaseUser user = userController.getmAuth().getCurrentUser();
      FirebaseAuth.getInstance().sendPasswordResetEmail(user.getEmail());
      Toast
              .makeText(EditProfile.this, "Yêu cầu đặt lại mật khẩu đã được gửi đến email", Toast.LENGTH_LONG + 5)
              .show();
    });

    refreshLayout = findViewById(R.id.swipeRefresh);
    refreshLayout.setOnRefreshListener(() -> {
    });

    btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(v -> finish());

    btnCancel = findViewById(R.id.btnCancel);
    btnCancel.setOnClickListener(v -> new AlertDialog.Builder(this)
            .setTitle("Thoát")
            .setMessage("Chưa lưu các thay đổi.Bạn có chắc chắn muốn thoát?")
            .setNegativeButton("Không", null)
            .setPositiveButton("Có", (args0, args1) -> finish())
            .create()
            .show());

    tvChangePicture = findViewById(R.id.tvChangePicture);
    tvChangePicture.setOnClickListener(view -> selectImage());

    User user = new AuthenticationController().getCurrentUser();

    edtFullName = findViewById(R.id.edtFullName);
    edtEmail = findViewById(R.id.edtEmail);
    edtPhone = findViewById(R.id.edtPhone);
    btnSave = findViewById(R.id.btnSave);

    edtFullName.setText(user.getFullName());
    edtEmail.setText(user.getEmail());
    edtPhone.setText(user.getPhone());
    LoadingDialog loadingDialog = new LoadingDialog(EditProfile.this);
    loadingDialog.startLoadingDialog();
    Picasso.get().load(Uri.parse(user.getProfileImageLink())).into(imgAvatar, new Callback() {
      @Override
      public void onSuccess() {
        loadingDialog.dismissDialog();
        Log.i("Loading image", "Done!");
      }

      @Override
      public void onError(Exception e) {
        loadingDialog.dismissDialog();
        Log.i("Loading image", "Failed!");
      }
    });

    edtPhone.setEnabled(false);
    edtEmail.setEnabled(false);


    btnSave.setOnClickListener(view -> {
      String name = edtFullName.getText().toString().trim();

      if (name.equals("")) {
        edtFullName.setError("Tên không được bỏ trống");
        return;
      }

      User u = new User();
      u.setFullName(name);
      userController.updateUserProfile(this, u);
      Intent i = new Intent(EditProfile.this, MainActivity.class);
      startActivity(i);
    });

  }

  public void selectImage() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(intent, 1);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1
            && resultCode == RESULT_OK
            && data != null
            && data.getData() != null) {
      filePath = data.getData();
      imgAvatar.setImageURI(filePath);
    }
    uploadPicture();
  }

  private void uploadPicture() {
    FirebaseUser user = userController.getmAuth().getCurrentUser();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images" + user.getUid());
    storageRef.putFile(filePath).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        Log.i("Upload image", "Successfully");
      } else {
        Log.i("Upload image", "Failed");
      }
    });
    String photoUrl = "https://firebasestorage.googleapis.com/v0/b/vinmartauth.appspot.com/o/images" + user.getUid() + "?alt=media";
    UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder().setPhotoUri(Uri.parse(photoUrl)).build();
    user.updateProfile(changeRequest).addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        Log.i("Save image", "Successfully");
      } else {
        Log.i("Save image", "Failed");
      }
    });
  }
}