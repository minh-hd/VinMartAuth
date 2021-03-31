package com.fpt.vinmartauth.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.Customer;
import com.fpt.vinmartauth.entity.Ship;
import com.fpt.vinmartauth.validation.AuthValidation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressFragment extends Fragment{
    Context context;
    TextView txt_pyment;
    Spinner shipSpinner;
    ArrayList<String> stringArrayState;
    ArrayList<String> stringArrayCity;
    String spinnerStateValue, _name, _email, _mobile, _address, _ship;
    EditText name, email, mobile, address;
    Customer customer;
    View progress;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener authStateListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public static AddressFragment newInstance() {
        return new AddressFragment();
    }
    public AddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment AddressFragment.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_address, container, false);
        shipSpinner = v.findViewById(R.id.citySpinner);

        name = v.findViewById(R.id.sa_name);
        email = v.findViewById(R.id.sa_email);
        mobile = v.findViewById(R.id.sa_mobile);
        address = v.findViewById(R.id.sa_address);



        init();
        context = container.getContext();
        txt_pyment = v.findViewById(R.id.txt_pyment);

        txt_pyment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _name = name.getText().toString();
                _email = email.getText().toString();
                _mobile = mobile.getText().toString();
                _address = address.getText().toString();

                Pattern p = Pattern.compile(AuthValidation.regEx);

                Matcher m = p.matcher(_email);

                if (_name.length() == 0) {
                    name.setError("Điền tên");
                    name.requestFocus();
                } else if (_email.length() == 0) {
                    email.setError("Điền email");
                    email.requestFocus();
                } else if (!m.find()) {
                    email.setError("Điền email đúng");
                    email.requestFocus();
                } else if (_mobile.length() == 0) {
                    mobile.setError("Điền số điện thoại");
                    mobile.requestFocus();
                } else if (_mobile.length() < 10) {
                    mobile.setError("Điền lại số điện thoại");
                    mobile.requestFocus();
                } else if (_address.length() == 0) {
                    address.setError("Điền địa chỉ");
                    address.requestFocus();
                } else {
                    String spinnerShipValue = String.valueOf(shipSpinner.getSelectedItem());


                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    //replace this fragment into the old one. There are more e.g add, remove etc
                    transaction.replace(R.id.content_frame, new PaymentFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();


                }


            }
        });

        return v;
    }

//
public static Ship[] getShipment()  {
    Ship sh1 = new Ship("SH01", "Standard", "As usual");
    Ship sh2 = new Ship("SH02", "Fast", "Within 24 hours");
    return new Ship[] {sh1, sh2};
}
    private void init() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        name.setText(user.getDisplayName());
        email.setText(user.getEmail());
        mobile.setText(user.getPhoneNumber());


        Ship[] shipment = getShipment();
        final ArrayAdapter<Ship> adapterShip = new ArrayAdapter<Ship>(getActivity(), R.layout.spinnertextview, shipment);
        adapterShip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shipSpinner.setAdapter(adapterShip);


        shipSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerShipValue = String.valueOf(shipSpinner.getSelectedItem());
                Log.e("SpinnerShipValue", spinnerShipValue);

                _ship = spinnerShipValue;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Address");
    }


    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }


}