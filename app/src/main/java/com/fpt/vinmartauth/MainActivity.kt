package com.fpt.vinmartauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val defaultFragment = MainFragment.newInstance()
        openFragment(defaultFragment)
        //call bottom nav view by id
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavView)
        //create on-click listener
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                //"if" by id
                R.id.home -> {
                    //create new instance
                    val mainFragment = MainFragment.newInstance()
                    openFragment(mainFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    val profileFragment = ProfileFragment.newInstance()
                    openFragment(profileFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.cart -> {
                    val cartFragment = CartFragment.newInstance()
                    openFragment(cartFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            true
        }
        //set the listener
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    //open fragments
    private fun openFragment(fragment: Fragment) {
        Log.d("Fragment", "Trying to add")
        val transaction = supportFragmentManager.beginTransaction()
        //replace this fragment into the old one. There are more e.g add, remove etc
        transaction.replace(R.id.fragment_container, fragment, "New Fragment")
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
