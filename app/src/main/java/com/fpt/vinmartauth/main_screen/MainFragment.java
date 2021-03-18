package com.fpt.vinmartauth.main_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.main_screen.product.Product;
import com.fpt.vinmartauth.main_screen.product.ProductAdapter;

import java.util.ArrayList;


public class MainFragment extends Fragment {
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //this is actually the fragments' "onCreate"
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //must declare to find view by id
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ArrayList<Product> itemBestSelling = new ArrayList<>();
        ArrayList<Product> itemRecommended = new ArrayList<>();
        RecyclerView rvRecommended = view.findViewById(R.id.rvRecommended);
        RecyclerView rvBestSelling = view.findViewById(R.id.rvBestSelling);
        itemBestSelling.add(new Product(R.drawable.product1, "BeoPlay Speaker", "Bang and Olufsen", 600000));
        itemBestSelling.add(new Product(R.drawable.product2, "Leather Wristwatch", "Tag Heuer", 9800000));
        itemBestSelling.add(new Product(R.drawable.product3, "Smart Bluetooth Speaker", "Google", 500000));
        itemBestSelling.add(new Product(R.drawable.product4, "Smart Luggage", "Smart Inc", 1200000));
        itemRecommended.add(new Product(R.drawable.product2, "Leather Wristwatch", "Tag Heuer", 9800000));
        itemRecommended.add(new Product(R.drawable.product1, "BeoPlay Speaker", "Bang and Olufsen", 600000));
        itemRecommended.add(new Product(R.drawable.product4, "Smart Luggage", "Smart Inc", 1200000));
        itemRecommended.add(new Product(R.drawable.product3, "Smart Bluetooth Speaker", "Google", 500000));
        ProductAdapter adapterBestSelling = new ProductAdapter(itemBestSelling);
        ProductAdapter adapterRecommended = new ProductAdapter(itemRecommended);
        rvBestSelling.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvBestSelling.setAdapter(adapterBestSelling);
        rvRecommended.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvRecommended.setAdapter(adapterRecommended);
        // Inflate the layout for this fragment
        return view;
    }
}
