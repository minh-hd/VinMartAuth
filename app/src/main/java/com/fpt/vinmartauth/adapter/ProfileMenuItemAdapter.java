package com.fpt.vinmartauth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.ProfileMenuItem;

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

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemName;
        private final ImageView itemIcon;

        public ViewHolder(final View view) {
            super(view);
            itemName = view.findViewById(R.id.tvProfileMenuItemName);
            itemIcon = view.findViewById(R.id.ivProfileMenuItemIcon);
        }

        public void bind(final ProfileMenuItem item) {
            itemName.setText(item.getItemName());
            itemIcon.setImageResource(item.getIconID());
        }
    }

}
