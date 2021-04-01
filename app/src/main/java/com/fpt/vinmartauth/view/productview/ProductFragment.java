package com.fpt.vinmartauth.view.productview;

import android.content.Intent;
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
import com.fpt.vinmartauth.adapter.CategoryAdapter;
import com.fpt.vinmartauth.adapter.ProductAdapter;
import com.fpt.vinmartauth.entity.CategoryIcon;
import com.fpt.vinmartauth.entity.Product;
import com.fpt.vinmartauth.view.productDetailView.ProductDetailsActivity;
import com.fpt.vinmartauth.view.productlist.ProductListActivity;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements ProductView, ProductAdapter.ProductAdapterListener, MaterialSearchBar.OnSearchActionListener {
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
        MaterialSearchBar searchBar = view.findViewById(R.id.searchBar);
        RecyclerView rvRecommended = view.findViewById(R.id.rvRecommended);
        RecyclerView rvBestSelling = view.findViewById(R.id.rvBestSelling);
        RecyclerView rvHighlightedProduct = view.findViewById(R.id.rvHighlightedProduct);
        RecyclerView rvCategory = view.findViewById(R.id.rvCategory);
        List<CategoryIcon> icons = new ArrayList<>();
        icons.add(new CategoryIcon(R.drawable.vegetable, "Rau củ", "Cgx001"));
        icons.add(new CategoryIcon(R.drawable.drinks, "Đồ uống", "Cgx002"));
        icons.add(new CategoryIcon(R.drawable.milk, "Sữa", "Cgx003"));
        icons.add(new CategoryIcon(R.drawable.candy, "Bánh kẹo", "Cgx004"));
        icons.add(new CategoryIcon(R.drawable.noodle, "Mì, phở", "Cgx005"));
        icons.add(new CategoryIcon(R.drawable.cooking, "Gia vị", "Cgx006"));
        icons.add(new CategoryIcon(R.drawable.rice, "Gạo, bột", "Cgx007"));
        icons.add(new CategoryIcon(R.drawable.seafood, "Đồ đông lạnh", "Cgx008"));
        icons.add(new CategoryIcon(R.drawable.cleaner, "Vệ sinh nhà cửa", "Cgx009"));
        icons.add(new CategoryIcon(R.drawable.fan, "Đồ dùng gia đình", "Cgx010"));
        rvCategory.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        CategoryAdapter categoryAdapter = new CategoryAdapter(icons);
        rvCategory.setAdapter(categoryAdapter);
        rvBestSelling.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvBestSelling.setAdapter(adapterBestSelling);
        rvRecommended.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvRecommended.setAdapter(adapterRecommended);
        rvHighlightedProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvHighlightedProduct.setAdapter(adapterHighlighted);
        adapterBestSelling.setListener(this);
        adapterHighlighted.setListener(this);
        adapterRecommended.setListener(this);
        searchBar.setOnSearchActionListener(this);
        controller.fetchAllProducts();
    }

    @Override
    public void setProducts(List<Product> products) {
        adapterRecommended.setData(products);
        adapterBestSelling.setData(products);
        adapterHighlighted.setData(products);

    }

    @Override
    public void onProductClick(Product product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        Intent intent = new Intent(getContext(), ProductListActivity.class);
        intent.putExtra("keyword", text.toString().trim());
        startActivity(intent);
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }
}
