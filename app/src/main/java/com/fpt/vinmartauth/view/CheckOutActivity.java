package com.fpt.vinmartauth.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.fpt.vinmartauth.R;
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

public class CheckOutActivity extends AppCompatActivity {
    TextView fullName, email, phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String cusID;

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