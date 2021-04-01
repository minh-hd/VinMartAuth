package com.fpt.vinmartauth.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.ProfileMenuItem;
import com.fpt.vinmartauth.view.EditProfile;
import com.fpt.vinmartauth.view.LoginActivity;
import com.fpt.vinmartauth.view.MainActivity;
import com.fpt.vinmartauth.view.fragment.cartView.UserSession;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.Adapter;

public class ProfileMenuItemAdapter extends Adapter {
  private final ArrayList<ProfileMenuItem> items = new ArrayList<>();

  public ProfileMenuItemAdapter(final ArrayList<ProfileMenuItem> i) {
    if (i != null) {
      this.items.addAll(i);
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_menu_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ((ViewHolder) holder).bind(items.get(position));

  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder implements UserAuthentication {
    private final TextView itemName;
    private final ImageView itemIcon;

    public ViewHolder(final View view) {
      super(view);
      itemName = view.findViewById(R.id.tvProfileMenuItemName);
      itemIcon = view.findViewById(R.id.ivProfileMenuItemIcon);
      view.setOnClickListener(v -> {
        String item = itemName.getText().toString();
        Intent intent;
        switch (item) {
          case "Chỉnh sửa hồ sơ":
            intent = new Intent(v.getContext(), EditProfile.class);
            v.getContext().startActivity(intent);
            break;
          case "Đăng nhập":
            intent = new Intent(v.getContext(), LoginActivity.class);
            v.getContext().startActivity(intent);
            break;
          case "Đăng xuất":
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Đăng xuất")
                    .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                    .setNegativeButton("Không", null)
                    .setPositiveButton("Có", (args0, args1) ->{
                       signOut();
                       Intent i = new Intent(v.getContext(), MainActivity.class);
                       v.getContext().startActivity(i);
                    })
                    .create()
                    .show();
            break;
        }
      });
    }

    public void bind(final ProfileMenuItem item) {
      itemName.setText(item.getItemName());
      itemIcon.setImageResource(item.getIconID());
    }

    @Override
    public void signOut() {
      UserSession session = UserSession.getInstance();
      session.setCartID("");
      session.setUID("");
      CartAdapter cartAdapter = CartAdapter.getInstance();
      cartAdapter.setData(new ArrayList<>());
      FirebaseAuth.getInstance().signOut();
    }
  }

  public interface UserAuthentication {
    void signOut();
  }

}
