package com.fpt.vinmartauth.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.view.fragment.cartView.CartFragment;
import com.fpt.vinmartauth.view.productview.ProductFragment;
import com.fpt.vinmartauth.view.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment(ProductFragment.newInstance());
        //call bottom nav view by id
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavView);
        //create on-click listener
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
            if (item.getItemId() == R.id.home) {
                openFragment(ProductFragment.newInstance());
            } else if (item.getItemId() == R.id.cart) {
                openFragment(CartFragment.newInstance());
            } else if (item.getItemId() == R.id.profile) {
                openFragment(ProfileFragment.newInstance());
            }
            return true;
        };
        //set the listener
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    //when back button pressed
    @Override
    public void onBackPressed() {
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavView);
        int selectedItemId = bottomNavigation.getSelectedItemId();
        //if main fragment is not currently active, back to main fragment
        if (R.id.home != selectedItemId) {
            setHomeItem(MainActivity.this);
        } else {//if user is already in the main fragment
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận")
                    .setMessage("Thoát ứng dụng?")
                    .setNegativeButton("Hủy", null) //if no, show main activity.
                    .setPositiveButton("Thoát", (arg0, arg1) -> {//if yes, back to home screen.
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }).create().show();
        }
    }

    //to set home icon on button nav state to "selected"
    private static void setHomeItem(Activity activity) {
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    //open fragments
    private void openFragment(Fragment fragment) {
        Log.d("Fragment", "Trying to add");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //replace this fragment into the old one. There are more e.g add, remove etc
        transaction.replace(R.id.fragment_container, fragment, "New Fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}