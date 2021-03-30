package com.fpt.vinmartauth.view.fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.adapter.ProfileMenuItemAdapter;
import com.fpt.vinmartauth.entity.ProfileMenuItem;
import com.fpt.vinmartauth.entity.User;
import com.fpt.vinmartauth.view.authentication.AuthenticationController;
import com.fpt.vinmartauth.view.dialog.LoadingDialog;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

  private ImageView imgAvatar;
  private TextView userFullName;
  private TextView userEmail;
  private final String DEFAULT_IMAGE_LINK = "https://firebasestorage.googleapis.com/v0/b/vinmartauth.appspot.com/o/default-profile-icon-16.jpg?alt=media";

  public ProfileFragment() {
    // Required empty public constructor
  }

  public static ProfileFragment newInstance() {
    return new ProfileFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_profile, container, false);
    // Inflate the layout for this fragment
    User user = new AuthenticationController().getCurrentUser();
    ArrayList<ProfileMenuItem> items = new ArrayList<>();
    RecyclerView rv = view.findViewById(R.id.rcvProfileMenu);
    if (user != null) {
      items.add(new ProfileMenuItem(R.drawable.ic_baseline_edit_24, getString(R.string.edit_profile)));
      items.add(new ProfileMenuItem(R.drawable.ic_baseline_location_on_24, getString(R.string.shipping_address)));
      items.add(new ProfileMenuItem(R.drawable.ic_baseline_favorite_border_24, getString(R.string.favorite)));
      items.add(new ProfileMenuItem(R.drawable.ic_baseline_history_24, getString(R.string.order_history)));
      items.add(new ProfileMenuItem(R.drawable.ic_baseline_local_shipping_24, getString(R.string.track_order)));
      items.add(new ProfileMenuItem(R.drawable.ic_baseline_credit_card_24, getString(R.string.cards)));
      items.add(new ProfileMenuItem(R.drawable.ic_baseline_notifications_24, getString(R.string.notifications)));
      items.add(new ProfileMenuItem(R.drawable.ic_baseline_exit_to_app_24, getString(R.string.log_out)));
    } else  {
      items.add(new ProfileMenuItem(R.drawable.ic_baseline_sign_in, getString(R.string.sign_in)));
    }

    imgAvatar = view.findViewById(R.id.imgAvatar);

    userFullName = view.findViewById(R.id.userFullName);
    userEmail = view.findViewById(R.id.userEmail);

    LoadingDialog loadingDialog = new LoadingDialog(this.getActivity());
    loadingDialog.startLoadingDialog();
    if (user != null) {
      userFullName.setText(user.getFullName());
      userEmail.setText(user.getEmail());
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
    } else {
      userFullName.setText("Chưa đăng nhập");
      userEmail.setText("");
      Picasso.get().load(Uri.parse(DEFAULT_IMAGE_LINK)).into(imgAvatar, new Callback() {
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
    }

    ProfileMenuItemAdapter adapter = new ProfileMenuItemAdapter(items);
    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    rv.setAdapter(adapter);
    return view;
  }

}