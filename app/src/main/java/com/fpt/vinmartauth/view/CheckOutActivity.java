package com.fpt.vinmartauth.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.adapter.CheckoutCartAdapter;
import com.fpt.vinmartauth.controller.ConfirmCheckoutController;
import com.fpt.vinmartauth.controller.CreateOrderController;
import com.fpt.vinmartauth.entity.Card;
import com.fpt.vinmartauth.entity.Order;
import com.fpt.vinmartauth.view.fragment.AddressFragment;
import com.fpt.vinmartauth.view.fragment.ConfirmFragment;
import com.fpt.vinmartauth.view.fragment.MainFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Random;

public class CheckOutActivity extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        openFragment(AddressFragment.newInstance());


// Set Fragmentclass Arguments
       //   fullName = findViewById(R.id.sa_name);
//        email = findViewById(R.id.sa_email);
//        phone = findViewById(R.id.sa_mobile);
//        fAuth = FirebaseAuth.getInstance();
//        fstore = FirebaseFirestore.getInstance();
//
//
//        cusID = fAuth.getCurrentUser().getUid();
//
//
//        DocumentReference documentReference = fstore.collection("customers").document(cusID);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                phone.setText(value.getString("phone"));
//                fullName.setText(value.getString("fullName"));
//                email.setText(value.getString("email"));
//            }
//        });
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();
        customerID = user.getUid();

        RadioGroup rg = (RadioGroup) findViewById(R.id.payment_group);
        final String value = ((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString();
        int selectedId = rg.getCheckedRadioButtonId();
        paymentID = value;
        cardOwner = findViewById(R.id.card_owner);
        cardNumber = findViewById(R.id.card_number);
        cardExpiry = findViewById(R.id.card_expiry);

        if (value.equalsIgnoreCase("Thanh toán bằng thẻ"))
        {
            Random generator = new Random();
            cardOwn = cardOwner.getText().toString();
            cardNo = cardNumber.getText().toString();
            cardExp = cardExpiry.getText().toString();
            card = new Card("Cdx"+ generator.nextInt(100),cardExp,cardNo,cardOwn );
        }
        shipSpinner = findViewById(R.id.citySpinner);
        shipID = String.valueOf(shipSpinner.getSelectedItem());
        address = findViewById(R.id.sa_address);
        _address = address.getText().toString();
        statusID = "Confirmed";
        controller.fetchCart(customerID);
        Order order = new Order();
        Random generator1 = new Random();
        order.setID("Ox"+ generator1.nextInt(100));
        order.setAddress(_address);
        order.setCardID(card);
        order.setCart(cartID);
        order.setUID(customerID);
        order.setPayment(paymentID);
        order.setShipID(shipID);
        order.setStatusID(statusID);
        controllerCO.createOrder(order);
    }

    private void openFragment(Fragment fragment) {
        Log.d("Fragment", "Trying to add");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //replace this fragment into the old one. There are more e.g add, remove etc
        transaction.replace(R.id.content_frame, fragment, "New Fragment");
        transaction.addToBackStack(null);
        transaction.commit();

    }


}