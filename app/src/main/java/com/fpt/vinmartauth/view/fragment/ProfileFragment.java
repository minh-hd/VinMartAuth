package com.fpt.vinmartauth.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.adapter.ProfileMenuItemAdapter;
import com.fpt.vinmartauth.entity.ProfileMenuItem;
import com.fpt.vinmartauth.view.LoginActivity;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView tvLogIn = view.findViewById(R.id.tvLogin);
        tvLogIn.setOnClickListener(view1 -> {
            Intent i = new Intent(this.getContext(), LoginActivity.class);
            startActivity(i);
        });
        ArrayList<ProfileMenuItem> items = new ArrayList<>();
        RecyclerView rv = view.findViewById(R.id.rcvProfileMenu);
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_edit_24, getString(R.string.edit_profile)));
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_location_on_24, getString(R.string.shipping_address)));
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_favorite_border_24, getString(R.string.favorite)));
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_history_24, getString(R.string.order_history)));
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_local_shipping_24, getString(R.string.track_order)));
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_credit_card_24, getString(R.string.cards)));
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_notifications_24, getString(R.string.notifications)));
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_exit_to_app_24, getString(R.string.log_out)));
        ProfileMenuItemAdapter adapter = new ProfileMenuItemAdapter(items);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        return view;
    }
}