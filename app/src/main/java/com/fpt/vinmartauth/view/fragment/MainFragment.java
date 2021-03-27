package com.fpt.vinmartauth.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.authentication.UserAuthentication;
import com.fpt.vinmartauth.view.LoginActivity;


public class MainFragment extends Fragment {
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //this is actually the fragments' "onCreate"
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //must declare to find view by id
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button btnSignIn = view.findViewById(R.id.btnHomeSignIn);
        btnSignIn.setOnClickListener(view1 -> {
            //must be this.getContext(), not just "this".
           if (!new UserAuthentication().isLoggedIn()) {
             Intent i = new Intent(this.getContext(), LoginActivity.class);
             startActivity(i);
           }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
