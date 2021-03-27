package com.fpt.vinmartauth.view.productview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.adapter.ProductAdapter;
import com.fpt.vinmartauth.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements ProductView {
    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    ProductAdapter adapterBestSelling = new ProductAdapter();
    ProductAdapter adapterRecommended = new ProductAdapter();
    ProductAdapter adapterHighlighted = new ProductAdapter();
    private ProductViewController controller = new ProductViewController();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //this is actually the fragments' "onCreate"
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //must declare to find view by id
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
        RecyclerView rvRecommended = view.findViewById(R.id.rvRecommended);
        RecyclerView rvBestSelling = view.findViewById(R.id.rvBestSelling);
        RecyclerView rvHighlightedProduct = view.findViewById(R.id.rvHighlightedProduct);
        RecyclerView rvCategory = view.findViewById(R.id.rvCategory);
        rvBestSelling.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvBestSelling.setAdapter(adapterBestSelling);
        rvRecommended.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvRecommended.setAdapter(adapterRecommended);
        rvHighlightedProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvHighlightedProduct.setAdapter(adapterHighlighted);
        controller.fetchAllProducts();
    }

    @Override
    public void setProducts(List<Product> products) {
        adapterRecommended.setData(products);
        adapterBestSelling.setData(products);

    }
}
