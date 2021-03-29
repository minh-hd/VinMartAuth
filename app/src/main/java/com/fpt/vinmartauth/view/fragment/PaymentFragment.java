package com.fpt.vinmartauth.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fpt.vinmartauth.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RadioGroup paymentGroup;
    FrameLayout cardFrame;
    RadioButton card, cash;
    LinearLayout payll;
    TextView pay;


    public PaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment PaymentFragment.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Payment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_payment, container, false);
        paymentGroup = view.findViewById(R.id.payment_group);
        card = view.findViewById(R.id.card_payment);
        cash = view.findViewById(R.id.cash_on_delivery);
        cardFrame = view.findViewById(R.id.card_frame);
        payll = view.findViewById(R.id.pay_ll);
        pay = view.findViewById(R.id.total_pay);
        Double amount = 500.000;
                //((BaseActivity) getActivity()).getTotalPrice(); // chỗ này chưa có giá của ĐỨc
        pay.append(amount + "");

        payll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                ft.replace(R.id.content_frame, new ConfirmFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        paymentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = view.findViewById(checkedId);
                if (radioButton.getId() == R.id.cash_on_delivery) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, new ConfirmFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                }
                // Toast.makeText(getContext(),radioButton.getText()+"",Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }
}