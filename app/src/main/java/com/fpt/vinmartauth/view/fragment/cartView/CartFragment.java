package com.fpt.vinmartauth.view.fragment.cartView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.CartItem;
import com.fpt.vinmartauth.view.LoginActivity;
import com.fpt.vinmartauth.view.dialog.LoadingDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    private FirebaseAuth mAuth;
    private final UserSession session = UserSession.getInstance();

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
        cartAdapter = CartAdapter.getInstance();
        rvCartList.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Attach ItemTouchHepler to the RecyclerView
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvCartList);
        // set checkout button on click listener
        btnCheckout.setOnClickListener(v -> {
            if(session.getCartID() != null || "".equals(session.getCartID())) {
            List<CartItem> cartItemList = cartAdapter.getCartItemList();
                // do checkout for current cart items
                controller.doCartCheckout(session.getCartID(), cartItemList);
                // clear cart items list and reset adapter
                cartItemList = new ArrayList<>();
                cartAdapter.setData(cartItemList);
                tvCartTotal.setText("0 đ");
                // clear UserSession cartID
                session.setCartID("");
                Log.d("checkout", session.getCartID());
            }
        });
        rvCartList.setAdapter(cartAdapter);
        init();
    }

    @Override
    public void onPause() {
        super.onPause();
        List<CartItem> cartItemList = cartAdapter.getCartItemList();
        Log.d("checkout", "Check cartID has been clear: " + session.getCartID().equals(""));
        if(!session.getCartID().isEmpty() && !cartItemList.isEmpty()) {
            controller.doCartItemsUpdate(session.getCartID(), cartItemList, getActivity());
        }
    }

    // run first to check if user is logged in
    private void init() {
        Log.d("SESSION", "Init run");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("SESSION", "Check user mAuth " + String.valueOf(user == null));
        if (user != null) {
            // run first time
            Log.d("SESSION", "User has logged in");
            if (session.getUID() == null || "".equals(session.getUID())) {
                session.setUID(user.getUid());
            }
            Log.d("SESSION", session.getUID());
            // run first time or when user need new cart
            if (session.getCartID() == null || "".equals(session.getCartID())) {
                controller.fetchUserCart(session.getUID(), getActivity());
            } else controller.fetchCartItems(session.getCartID(), getActivity());
        } else {
            // redirect to login
            Log.d("SESSION", "User not logged in");
            Intent intent = new Intent(this.getContext(), LoginActivity.class);
            startActivity(intent);
        }
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
                    viewHolder.getAdapterPosition()).getProductTitle(), Toast.LENGTH_SHORT).show();
            // invoke cart item list after delete in firebase
            controller.fetchCartAfterDelete(session.getCartID(), cartItemList.get(viewHolder.getAdapterPosition()).getDocumentID(), getActivity());
            cartAdapter.setData(cartItemList);
        }
    };


    @Override
    public void setCartItems(List<CartItem> cartItemList) {
        int itemTotal = 0;
        for (CartItem item : cartItemList)
            itemTotal += Integer.parseInt(item.getQuantity()) * Integer.parseInt(item.getProductPrice());
        tvCartTotal.setText(String.valueOf(itemTotal).concat(CURRENCY_SYMBOL));
        cartAdapter.setData(cartItemList);
    }

    @Override
    public void setMessage(String message) {
        Toast.makeText(getActivity(), message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCart(Cart cart) {
        session.setCartID(cart.getDocumentID());
        Log.d("SESSION", "Got cartID: " + session.getCartID());
        controller.fetchCartItems(session.getCartID(),getActivity());
    }
}