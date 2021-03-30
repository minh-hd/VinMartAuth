package com.fpt.vinmartauth.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.adapter.CartAdapter;
import com.fpt.vinmartauth.entity.CartItem;
import com.fpt.vinmartauth.model.FirestoreInstance;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String COLLECTION_PATH = "cartItem";

    private RecyclerView rvCartList;
    private TextView tvCartTotal;

    private List<CartItem> cartItemList;

    private CartAdapter cartAdapter;



    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        tvCartTotal = view.findViewById(R.id.tv_cart_total);
        rvCartList = view.findViewById(R.id.rvCart);
        cartItemList = new ArrayList<>();

        // *================ CREATE FAKE DATA ========================*
        // Remove later
        for(int i = 0; i < 2; i++) {
            CartItem item = new CartItem();
            item.setProductPrice("12000");
            item.setProductImage("https://cdn.tgdd.vn/Products/Images/8784/232079/bhx/cai-ngong-4kfarm-tui-300g-202103102250304942.jpg");
            item.setProductID("CI01");
            item.setProductTitle("Cải ngồng túi 300g");
            item.setQuantity("2");
            cartItemList.add(item);
        }
        // *==========================================================*

        // calculate and sum total price of all items in cart
        int cartTotal = 0;
        for (CartItem item : cartItemList) {
            cartTotal += Integer.parseInt(item.getProductPrice())
                    * Integer.parseInt(item.getQuantity());
        }

        tvCartTotal.setText(String.valueOf(cartTotal).concat("\tVND"));
        rvCartList.setHasFixedSize(true);
        cartAdapter = new CartAdapter(cartItemList,tvCartTotal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvCartList.setLayoutManager(linearLayoutManager);
        // Attach ItemTouchHepler to the RecyclerView
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvCartList);
        rvCartList.setAdapter(cartAdapter);
        return view;
    }

    public void doCheckout(View view) {}

    // Manage swipe interaction
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(10,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {
            // Show toast message of removed item
            Toast.makeText(getActivity(), "Đã xóa " + cartItemList.get(
                    viewHolder.getAdapterPosition()).getProductTitle(), Toast.LENGTH_SHORT
            ).show();
            cartItemList.remove(viewHolder.getAdapterPosition());
            // re-calculate and set total price of all items in cart
            int cartTotal = 0;
            for (CartItem item : cartItemList) {
                cartTotal += Integer.parseInt(item.getProductPrice())
                        * Integer.parseInt(item.getQuantity());
            }
            tvCartTotal.setText(String.valueOf(cartTotal).concat("\tVND"));
            cartAdapter.notifyDataSetChanged();
        }
    };
}