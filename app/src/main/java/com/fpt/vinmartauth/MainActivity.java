package com.fpt.vinmartauth;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment(MainFragment.newInstance());
        //call bottom nav view by id
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavView);
        //create on-click listener
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
            if (item.getItemId() == R.id.home) {
                openFragment(MainFragment.newInstance());
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