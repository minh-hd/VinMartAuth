package com.fpt.vinmartauth.view.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.adapter.CheckoutCartAdapter;
import com.fpt.vinmartauth.controller.ConfirmCheckoutController;
import com.fpt.vinmartauth.controller.CreateOrderController;
import com.fpt.vinmartauth.entity.Card;
import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.CartItem;
import com.fpt.vinmartauth.entity.Order;
import com.fpt.vinmartauth.view.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmFragment extends Fragment implements ConfirmCheckoutController.CartItemCheckoutView, CheckoutCartAdapter.CartItemAdapterListener {
    TextView back, order;
    public static ConfirmFragment newInstance() {
        return new ConfirmFragment();
    }

    CheckoutCartAdapter adapterCart = new CheckoutCartAdapter();
    private ConfirmCheckoutController controller = new ConfirmCheckoutController();
    TextView fullName, email, phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    Spinner shipSpinner;
    EditText address, cardOwner, cardNumber, cardExpiry;
    String customerID,cartID,paymentID,shipID,statusID,_address, cardOwn, cardNo, cardExp;
    Card card;
    AddressFragment adddressViwe;
    private CreateOrderController controllerCO = new CreateOrderController();


    TextView totalPayment, totalAmount;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types and number of parameters
    public static ConfirmFragment newInstance(String param1, String param2) {
        ConfirmFragment fragment = new ConfirmFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller.setView(this);
        RecyclerView rvCartCheckout = view.findViewById(R.id.cart_rv);
        rvCartCheckout.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvCartCheckout.setAdapter(adapterCart);
        Log.d("kienct", "pff");
        controller.fetchAllCartItem();
        totalPayment = view.findViewById(R.id.total);
        controller.fetchCartTotal();
        totalAmount = view.findViewById(R.id.total_amount);
        controller.fetchAmountTotal();
             // totalAmount.setText( + "");
        //total.setText(controller.fetchCartTotal().toSt);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);

        back = view.findViewById(R.id.back);
        order = view.findViewById(R.id.place_order);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                // Include dialog.xml file
                dialog.setContentView(R.layout.success_dialog);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    }
                });
                // Set dialog title

                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog.isShowing()){
                            dialog.dismiss();
                            startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        }
                    }
                }, 5000);
            }
        });
        return view;
    }



    @Override
    public void setProductsCO(List<CartItem> cartItems) {
        adapterCart.setData(cartItems);
    }

    @Override
    public void setTotal(int total) {
        totalPayment.setText(total + "");
    }

    @Override
    public void setAmount(int amount) {
        totalAmount.setText(amount + "");
    }

    @Override
    public void setCart(Cart cart) {
        cartID = cart.getDocumentID();
    }

    @Override
    public void onCartItemClick(CartItem cartItem) {

    }

}