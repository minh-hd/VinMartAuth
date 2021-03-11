package com.fpt.vinmartauth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        // Inflate the layout for this fragment
        ArrayList<ProfileMenuItem> items = new ArrayList<>();
        RecyclerView rv = view.findViewById(R.id.rcvProfileMenu);
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_edit_24, getString(R.string.edit_profile)));
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_location_on_24, getString(R.string.shipping_address)));
        items.add(new ProfileMenuItem(R.drawable.ic_baseline_favorite_border_24, getString(R.string.wishlist)));
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