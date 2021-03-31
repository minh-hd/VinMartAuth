package com.fpt.vinmartauth.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.adapter.CartAdapter;
import com.fpt.vinmartauth.entity.CartItem;

import java.util.ArrayList;
import java.util.List;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartView{

    private RecyclerView rvCartList;
    public static TextView tvCartTotal;
    private CartAdapter cartAdapter;
    private CartViewController controller = new CartViewController();
    private final String TOAST_DELETE_MESSAGE_HEAD = "Đã xóa: ";
    private static final String CURRENCY_SYMBOL = " đ";
    private Button btnCheckout;


    public CartFragment() {}

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
        tvCartTotal = view.findViewById(R.id.tv_cart_total);
        rvCartList = view.findViewById(R.id.rvCart);
        btnCheckout = view.findViewById(R.id.btn_checkout);
        rvCartList.setHasFixedSize(true);
        cartAdapter = new CartAdapter();
        rvCartList.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Attach ItemTouchHepler to the RecyclerView
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvCartList);
        // set checkout button on click listener
        btnCheckout.setOnClickListener(v -> {
            List<CartItem> cartItemList = cartAdapter.getCartItemList();
            // do checkout for current cart items
            controller.doCartCheckout(cartItemList);
            cartItemList = new ArrayList<>();
            // clear cart items list and reset adapter
            cartAdapter.setData(cartItemList);
        });
        rvCartList.setAdapter(cartAdapter);
        controller.fetchCartItems();
        controller.fetchCartItemsTotal();
    }

    @Override
    public void onPause() {
        super.onPause();
        List<CartItem> cartItemList = cartAdapter.getCartItemList();
        controller.doCartItemsUpdate(cartItemList);
    }

    // Manage swipe interaction
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(10,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {return false;}

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {
            List<CartItem> cartItemList = cartAdapter.getCartItemList();
            // Show toast message of removed item
            Toast.makeText(getActivity(), TOAST_DELETE_MESSAGE_HEAD + cartItemList.get(
                    viewHolder.getAdapterPosition()).getProductTitle(), Toast.LENGTH_SHORT
            ).show();
            // invoke cart item list after delete in firebase
            controller.fetchCartAfterDelete(cartItemList.get(viewHolder.getAdapterPosition()).getDocumentID());
            cartAdapter.setData(cartItemList);
        }
    };

    @Override
    public void setCart(List<CartItem> cartItemList) {
        cartAdapter.setData(cartItemList);
    }

    @Override
    public void setTotal(int cartTotal) {
        tvCartTotal.setText(String.valueOf(cartTotal).concat(CURRENCY_SYMBOL));
    }

    @Override
    public void setMessage(String message) {
        Toast.makeText(getActivity(), message,Toast.LENGTH_SHORT).show();
    }
}