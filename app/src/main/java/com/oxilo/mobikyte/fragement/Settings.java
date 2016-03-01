package com.oxilo.mobikyte.fragement;

/*
 All Copyright, Audianz Network Pvt ltd.
CIN:
All intellectual property, code ownership belongs un-conditionally
to Audianz Network Pvt Ltd. No unauthorised code copying,
redistribution and editing is permitted.
Author: Audianz Network Pvt Ltd
CIN:
*/

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oxilo.mobikyte.ApplicationController;
import com.oxilo.mobikyte.Constants;
import com.oxilo.mobikyte.MODAL.MobiKytePrefs;
import com.oxilo.mobikyte.POJO.ModalCheckOut;
import com.oxilo.mobikyte.POJO.ModalLogin;
import com.oxilo.mobikyte.POJO.ModalSettings;
import com.oxilo.mobikyte.R;
import com.oxilo.mobikyte.logger.Log;
import com.oxilo.mobikyte.ui.CustomTextView;
import com.oxilo.mobikyte.utility.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ModalLogin modalLogin;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
//    Toolbar toolbar;

    EditText businessNameView,userNameView,businessAddressView,
            cityNameView,stateNameView,zipCodeView,emailView,webUrlView,
            mobileNumberView,aboutView,nameView;
    AppCompatButton update_button;

    private View mLoginFormView,mProgressView;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param modalLogin Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(ModalLogin modalLogin, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, modalLogin);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Settings() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            modalLogin = getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUiWidget(view);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (toolbar!=null)
//            toolbar.setTitle("Settings");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView toolbar_title = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbar_title.setText("Settings");
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void initUiWidget(View view){
//         toolbar = (Toolbar)view.findViewById(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // update the main content by replacing fragments
//                getFragmentManager().popBackStack();
//            }
//        });
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);

        mLoginFormView = view.findViewById(R.id.login_form);
        mProgressView = view.findViewById(R.id.login_progress);

         nameView = (EditText)view.findViewById(R.id.action_view_name);
         businessNameView = (EditText)view.findViewById(R.id.action_bussiness_name);
         userNameView = (EditText)view.findViewById(R.id.action_user_name);
         businessAddressView = (EditText)view.findViewById(R.id.action_bussiness_address);
         cityNameView = (EditText)view.findViewById(R.id.action_user_city);
         stateNameView = (EditText)view.findViewById(R.id.action_user_state);
         zipCodeView = (EditText)view.findViewById(R.id.action_zip_code);
         emailView = (EditText)view.findViewById(R.id.action_email);
         webUrlView = (EditText)view.findViewById(R.id.action_web_url);
         mobileNumberView = (EditText)view.findViewById(R.id.action_mobile_no);
         aboutView = (EditText)view.findViewById(R.id.action_about);

         setInfo();

        nameView.addTextChangedListener(textWatcher);
        businessNameView.addTextChangedListener(textWatcher);
        userNameView.addTextChangedListener(textWatcher);
        businessAddressView.addTextChangedListener(textWatcher);
        cityNameView.addTextChangedListener(textWatcher);
        stateNameView.addTextChangedListener(textWatcher);
        zipCodeView.addTextChangedListener(textWatcher);
//        emailView.addTextChangedListener(textWatcher);
        webUrlView.addTextChangedListener(textWatcher);
        mobileNumberView.addTextChangedListener(textWatcher);

        update_button = (AppCompatButton)view.findViewById(R.id.email_sign_in_button);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCheckOut();
            }
        });

        update_button.setVisibility(View.GONE);
    }

    private void setInfo(){
        try {
                nameView.setText(modalLogin.getName()!=null ? modalLogin.getName() : "name");
                businessNameView.setText(modalLogin.getBusinessName()!=null ? modalLogin.getBusinessName() : "business name");
                userNameView.setText(modalLogin.getName()!=null ? modalLogin.getName() : "user name");
                businessAddressView.setText(modalLogin.getAddress()!=null ? modalLogin.getAddress() : "address");
                cityNameView.setText(modalLogin.getCity()!=null ? modalLogin.getCity() : "city");
                stateNameView.setText(modalLogin.getState()!=null ? modalLogin.getState() : "state");
                zipCodeView.setText(modalLogin.getZip()!=null ? modalLogin.getZip() : "zip");
                emailView.setText(modalLogin.getMobile()!=null ? modalLogin.getEmailid() : "mobile");
                webUrlView.setText(modalLogin.getWebsite()!=null ? modalLogin.getWebsite() : "webUrl");
                mobileNumberView.setText(modalLogin.getMobile()!=null ? modalLogin.getMobile() : "website");
                aboutView.setText("about");

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    /**
     * NETWORK Update CALL
     *
     */
    public void doCheckOut() {
        showProgress(true);
        String URL = getResources().getString(R.string.mobikyte_base_url) + "data=" + Uri.encode(makeJsonBody().toString());
        StringRequest req = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showProgress(false);
                VolleyLog.v("Response:%n %s", response);
                Gson gson = new GsonBuilder().create();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals(getResources().getString(R.string.response_success))){
                        Toast.makeText(getActivity(),"Update successful",Toast.LENGTH_SHORT).show();
                        ModalSettings modalSettings = gson.fromJson(response, ModalSettings.class);
                        MobiKytePrefs mobiKytePrefs = ApplicationController.getInstance().getMobiKytePrefs();
                        if(mobiKytePrefs != null) {
                            ActivityUtils.updateLoginPrefsOnSettingUpdate(modalSettings,modalLogin);
                            setInfo();
                        } else {
                            android.util.Log.e("MOBIKYTE PREFS==", "Preference is null");
                        }
                    }
                    else if (jsonObject.getString("status").equals(getResources().getString(R.string.response_error))){
                        Toast.makeText(getActivity(),"Update failed",Toast.LENGTH_SHORT).show();
//                        FailFragment failFragment = FailFragment.newInstance(userCampaign,modalAddCampign,modalLogin);
//                        ActivityUtils.launchFragementWithAnimation(failFragment,getActivity());
                    }
                } catch (JSONException e) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                    e.printStackTrace();
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
                catch (NullPointerException ex){
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Activity activity = getActivity();
                if(activity != null && isAdded())
                    showProgress(false);
                Toast.makeText(getActivity(), "Something went wrong. please try it again", Toast.LENGTH_SHORT).show();
            }
        });

        req.setRetryPolicy(new DefaultRetryPolicy(
                Constants.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req, ApplicationController.REGISTRATION_TAG);

    }



    private JSONObject makeJsonBody(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("address",businessAddressView.getText().toString());
            jsonObject.put("business_name",businessNameView.getText().toString());
            jsonObject.put("city",cityNameView.getText().toString());
            jsonObject.put("clientid", modalLogin.getClientid() != null ? modalLogin.getClientid() : "253");
            jsonObject.put("email", emailView.getText().toString()!= null ? emailView.getText().toString() : "us@us.com");
            jsonObject.put("mobileno",mobileNumberView.getText().toString() != null ? mobileNumberView.getText().toString() : "7737502784");
            jsonObject.put("name",nameView.getText().toString()!=null ? nameView.getText().toString() : "Test");
            jsonObject.put("state",stateNameView.getText().toString()!=null ? stateNameView.getText().toString() : "Delhi");
            jsonObject.put("web_url",webUrlView.getText().toString() != null ? webUrlView.getText().toString() : "http://www.mobikyte.com");
            jsonObject.put("zip",zipCodeView.getText().toString());
            jsonObject.put("password",modalLogin.getPassword().toString());
            jsonObject.put("api","update_register_request");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
        }
        Log.e("JSON STRING", "" + jsonObject.toString());
        return jsonObject;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           update_button.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
