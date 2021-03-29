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
import android.widget.Toast;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.Address;
import com.fpt.vinmartauth.entity.Customer;
import com.fpt.vinmartauth.validation.AuthValidation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressFragment extends Fragment {

    Context context;
    TextView txt_pyment;
    Spinner citySpinner, stateSpinner;
    ArrayList<String> stringArrayState;
    ArrayList<String> stringArrayCity;
    String spinnerStateValue, _name, _email, _mobile, _address, _city, _state;
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

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser().

        View v = inflater.inflate(R.layout.fragment_address, container, false);
        citySpinner = v.findViewById(R.id.citySpinner);
        stateSpinner = v.findViewById(R.id.stateSpinner);
        name = v.findViewById(R.id.sa_name);
        email = v.findViewById(R.id.sa_email);
        mobile = v.findViewById(R.id.sa_mobile);
        address = v.findViewById(R.id.sa_address);

        //init();
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
//                    Address cusAddress = new Address(_address,_state,_city);
//                    Customer userAddress = new Customer( _name, _mobile, _email,cusAddress);
//                    String user_address = gson.toJson(userAddress);
//                   // localStorage.createUserLoginSession(user_address);
//
//                    saveUserAddress(userAddress);


                }


            }
        });
        return v;
    }
//    private void saveUserAddress(Customer userAddress) {
//
//        Call<UserResult> call = RestClient.getRestService(getContext()).updateUser(userAddress);
//        call.enqueue(new Callback<UserResult>() {
//            @Override
//            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
//                Log.d("Response :=>", response.body() + "");
//                if (response != null) {
//
//                    UserResult userResult = response.body();
//                    if (userResult.getCode() == 200) {
//
//                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
//                        ft.replace(R.id.content_frame, new PaymentFragment());
//                        ft.commit();
//                    } else {
//                        Toast.makeText(getContext(), "Please try again", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//                hideProgressDialog();
//            }
//
//            @Override
//            public void onFailure(Call<UserResult> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    private void init() {
//        stringArrayState = new ArrayList<String>();
//        stringArrayCity = new ArrayList<String>();
//
//        //set city adapter
//        final ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(getActivity(), R.layout.spinnertextview, stringArrayCity);
//        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        citySpinner.setAdapter(adapterCity);
//
//        if (user.getCity() != null) {
//            int selectionPosition = adapterCity.getPosition(user.getCity());
//            citySpinner.setSelection(selectionPosition);
//        }
//
//        //Get state json value from assets folder
//        try {
//            JSONObject obj = new JSONObject(loadJSONFromAssetState());
//            JSONArray m_jArry = obj.getJSONArray("statelist");
//
//            for (int i = 0; i < m_jArry.length(); i++) {
//                JSONObject jo_inside = m_jArry.getJSONObject(i);
//
//                String state = jo_inside.getString("State");
//                String id = jo_inside.getString("id");
//
//                stringArrayState.add(state);
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnertextview, stringArrayState);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        stateSpinner.setAdapter(adapter);
//        if (user.getState() != null) {
//            int selectionPosition = adapter.getPosition(user.getState());
//            stateSpinner.setSelection(selectionPosition);
//        }
//
//
//        //state spinner item selected listner with the help of this we get selected value
//
//        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Object item = parent.getItemAtPosition(position);
//                String Text = stateSpinner.getSelectedItem().toString();
//
//
//                spinnerStateValue = String.valueOf(stateSpinner.getSelectedItem());
//                _state = spinnerStateValue;
//                stringArrayCity.clear();
//
//                try {
//                    JSONObject obj = new JSONObject(loadJSONFromAssetCity());
//                    JSONArray m_jArry = obj.getJSONArray("citylist");
//
//                    for (int i = 0; i < m_jArry.length(); i++) {
//                        JSONObject jo_inside = m_jArry.getJSONObject(i);
//                        String state = jo_inside.getString("State");
//                        String cityid = jo_inside.getString("id");
//
//                        if (spinnerStateValue.equalsIgnoreCase(state)) {
//                            _city = jo_inside.getString("city");
//                            stringArrayCity.add(_city);
//                        }
//
//                    }
//
//                    //notify adapter city for getting selected value according to state
//                    adapterCity.notifyDataSetChanged();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
//
//        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String spinnerCityValue = String.valueOf(citySpinner.getSelectedItem());
//                Log.e("SpinnerCityValue", spinnerCityValue);
//
//                _city = spinnerCityValue;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//    }
//
//
//    public String loadJSONFromAssetState() {
//        String json = null;
//        try {
//            InputStream is = getContext().getAssets().open("state.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, StandardCharsets.UTF_8);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }
//
//    public String loadJSONFromAssetCity() {
//        String json = null;
//        try {
//            InputStream is = getContext().getAssets().open("cityState.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, StandardCharsets.UTF_8);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }
//
//    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        }
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        //you can set the title for your toolbar here for different fragments different titles
//        getActivity().setTitle("Address");
//    }


    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }
}