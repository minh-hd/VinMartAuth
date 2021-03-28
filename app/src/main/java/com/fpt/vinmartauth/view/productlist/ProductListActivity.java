package com.fpt.vinmartauth.view.productlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.adapter.ProductAdapter;
import com.fpt.vinmartauth.entity.Product;
import com.fpt.vinmartauth.view.productDetailView.ProductDetailsActivity;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

public class ProductListActivity extends Activity implements ProductListView, ProductAdapter.ProductAdapterListener, MaterialSearchBar.OnSearchActionListener {
    ProductAdapter adapterProductList = new ProductAdapter();
    ProductListController controller = new ProductListController();
    RecyclerView recyclerView;
    MaterialSearchBar searchBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        recyclerView = findViewById(R.id.rcProducts);
        recyclerView.setAdapter(adapterProductList);
        searchBar = findViewById(R.id.search);
        searchBar.setOnSearchActionListener(this);
        String keyword = getIntent().getStringExtra("keyword");
        searchBar.setTextHintColor(ContextCompat.getColor(this, R.color.black));
        searchBar.setHint(keyword);
        searchBar.setPlaceHolder(keyword);
        adapterProductList.setListener(this);
        controller.setView(this);
        controller.fetchProductById(keyword);
    }

    @Override
    public void onProductClick(Product product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }

    @Override
    public void setData(List<Product> products) {
        //Khi co Adapter roi thi chi can set product nhu nay la no se tu tao cac item tren man hinh dua vao data e dua cho no, that's all
        adapterProductList.setData(products);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        searchBar.setPlaceHolder(text.toString().trim());
        controller.fetchProductById(text.toString().trim());
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }
}
