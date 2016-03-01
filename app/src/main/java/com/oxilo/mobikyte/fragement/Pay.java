package com.oxilo.mobikyte.fragement;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.oxilo.mobikyte.MODAL.MobiKytePlaceCampaignInfo;
import com.oxilo.mobikyte.MODAL.UserCampaign;
import com.oxilo.mobikyte.POJO.ModalAddCampign;
import com.oxilo.mobikyte.POJO.ModalAlreadyRegistred;
import com.oxilo.mobikyte.POJO.ModalLogin;
import com.oxilo.mobikyte.POJO.ModalProductsPlans;
import com.oxilo.mobikyte.POJO.Plan;
import com.oxilo.mobikyte.R;
import com.oxilo.mobikyte.adapter.PlansListAdapter;
import com.oxilo.mobikyte.holder.GroupItem;
import com.oxilo.mobikyte.utility.ActivityUtils;
import com.oxilo.mobikyte.volley.VolleyErrorHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Pay.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Pay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pay extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private UserCampaign userCampaign;
    private ModalAddCampign modalAddCampign;
    private ModalLogin modalLogin;
//    private Toolbar toolbar;

    private OnFragmentInteractionListener mListener;
    MobiKytePlaceCampaignInfo place;

    RecyclerView recyclerView;
    PlansListAdapter productListAdapter;
    GroupItem groupItem;

    LinearLayoutManager linearLayoutManager;

    private View mLoginFormView,mProgressView;

    private boolean isSelected = false;
    Plan mySeletedPlan;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userCampaign Parameter 1.
     * @param modalLogin Parameter 3.
     * @return A new instance of fragment Pay.
     */
    // TODO: Rename and change types and number of parameters
    public static Pay newInstance(MobiKytePlaceCampaignInfo place,UserCampaign userCampaign, ModalLogin modalLogin) {
        Pay fragment = new Pay();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, userCampaign);
        args.putParcelable(ARG_PARAM2,place);
        args.putParcelable(ARG_PARAM4,modalLogin);
        fragment.setArguments(args);
        return fragment;
    }

    public Pay() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userCampaign = getArguments().getParcelable(ARG_PARAM1);
            place = getArguments().getParcelable(ARG_PARAM2);
            modalLogin = getArguments().getParcelable(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUIWidget(view);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
      productListAdapter.setOnItemClickListener(new PlansListAdapter.MyClickListener() {
          @Override
          public void onItemClick(int position, View v) {
              try {
                  mySeletedPlan = groupItem.planList.get(position);
                  CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);
                  if (checkBox.isChecked()) {
                      if (PlansListAdapter.lastChecked != null) {
                          PlansListAdapter.lastChecked.setChecked(false);
                          mySeletedPlan.setChecked(false);
                      }

                      PlansListAdapter.lastChecked = checkBox;
                      PlansListAdapter.lastCheckedPos = position;
                  } else
                      PlansListAdapter.lastChecked = null;

                  mySeletedPlan.setChecked(checkBox.isChecked());

              } catch (Exception ex) {
                  ex.printStackTrace();
              }
          }
      });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView toolbar_title = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbar_title.setText("Pay");
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

    private void initUIWidget(final View v){
//        toolbar = (Toolbar)v.findViewById(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // update the main content by replacing fragments
//               getFragmentManager().popBackStack();
//            }
//        });
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);

        AppCompatButton check_out_btn = (AppCompatButton)v.findViewById(R.id.check_out_btn);
        check_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mySeletedPlan != null){
                        if (!mySeletedPlan.getChecked()){
                            final Snackbar snackBar = Snackbar.make(v.findViewById(R.id.root_layout), "Select at least one plan", Snackbar.LENGTH_LONG);
                            snackBar.setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    snackBar.dismiss();
                                }
                            });
                            snackBar.setActionTextColor(Color.WHITE);
                            // Changing action button text color
                            View sbView = snackBar.getView();
                            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            snackBar.show();
                            return;
                        }else{
                            doSomething();
                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        mLoginFormView = v.findViewById(R.id.login_form);
        mProgressView = v.findViewById(R.id.login_progress);
        recyclerView = (RecyclerView)v.findViewById(R.id.action_recyle_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        loadPlans();
    }

   private void loadPlans(){
       groupItem = new GroupItem();
       productListAdapter = new PlansListAdapter(groupItem.planList,getActivity());
       recyclerView.setAdapter(productListAdapter);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Activity activity = getActivity();
               if(activity != null && isAdded())
                   showProgress(true);
               String URL = getResources().getString(R.string.mobikyte_campaign_base_url) + "data=" + Uri.encode(makeJsonBody(1).toString());
               StringRequest req = new StringRequest(URL, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       showProgress(false);
                       VolleyLog.v("Response:%n %s", response);
                       Gson gson = new GsonBuilder().create();
                       try {
                           JSONObject jsonObject = new JSONObject(response);
                           if (jsonObject.getString("status").equals(getResources().getString(R.string.response_success))){
                               ModalProductsPlans modalProductsPlans = gson.fromJson(response, ModalProductsPlans.class);
                               for (int i = 0; i < modalProductsPlans.getPlans().size(); i++){
                                   Plan plan = modalProductsPlans.getPlans().get(i);
                                   if (i == 0){
                                       mySeletedPlan = plan;
                                       plan.setChecked(true);
                                   }else{
                                       plan.setChecked(false);
                                   }
                                   productListAdapter.addItem(plan);
                               }
                           }
                           else if (jsonObject.getString("status").equals(getResources().getString(R.string.response_error))){
                               Toast.makeText(getActivity(), "No Plans found", Toast.LENGTH_LONG).show();
                           }
                       } catch (JSONException e) {
                           Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                           e.printStackTrace();
                       }
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Activity activity = getActivity();
                       if(activity != null && isAdded())
                           showProgress(false);
                       Toast.makeText(getActivity(), VolleyErrorHelper.getErrorType(error, getActivity()), Toast.LENGTH_SHORT).show();
                   }
               });

               req.setRetryPolicy(new DefaultRetryPolicy(
                       Constants.MY_SOCKET_TIMEOUT_MS,
                       DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                       DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
               // add the request object to the queue to be executed
               ApplicationController.getInstance().addToRequestQueue(req, ApplicationController.REGISTRATION_TAG);
           }
       }, 10);
   }

    /**
     * NETWORK CALL
     *
     */
    public void doSomething() {
        showProgress(true);
        String URL = getResources().getString(R.string.mobikyte_campaign_base_url) + "data=" + Uri.encode(makeJsonBody().toString());
        StringRequest req = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showProgress(false);
                VolleyLog.v("Response:%n %s", response);
                Gson gson = new GsonBuilder().create();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals(getResources().getString(R.string.response_success))){
                        ModalAddCampign modalAddCampign = gson.fromJson(response, ModalAddCampign.class);
                        Checkout checkout = Checkout.newInstance(place, userCampaign, modalAddCampign, modalLogin,mySeletedPlan);
                        ActivityUtils.launchFragementWithAnimation(checkout,getActivity());
                    }
                    else if (jsonObject.getString("status").equals(getResources().getString(R.string.response_error))){
                        ModalAlreadyRegistred modalAlreadyRegistred = gson.fromJson(response, ModalAlreadyRegistred.class);
                        Toast.makeText(getActivity(), modalAlreadyRegistred.getErrorString(), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Activity activity = getActivity();
                if(activity != null && isAdded())
                    showProgress(false);
                Toast.makeText(getActivity(), VolleyErrorHelper.getErrorType(error, getActivity()), Toast.LENGTH_SHORT).show();
            }
        });

        req.setRetryPolicy(new DefaultRetryPolicy(
                Constants.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req, ApplicationController.REGISTRATION_TAG);
    }

    private JSONObject makeJsonBody(int k){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clientid",modalLogin.getClientid());
            jsonObject.put("api","fetch_promote_plan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("JSON STRING", "" + jsonObject.toString());
        return jsonObject;
    }
    private JSONObject makeJsonBody(){
        double lat = 0.0,lng = 0.0;
        String address = "India";
        try {
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                address = "India";
                lat = 27.00;
                lng = 27.00;
            }else{
                if (place.getAddress()!=null){
                    address = place.getAddress().toString().trim();
                    lat = place.getLat();
                    lng = place.getLng();
                }
                else
                {
                    address = "India";
                    lat = 27.00;
                    lng = 27.00;
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        String mo = mySeletedPlan.getPriceInUsd();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("accbal","25");
            jsonObject.put("camp_name",userCampaign.getCampaignTitle());
            jsonObject.put("click_to_action",userCampaign.getCall());
            jsonObject.put("clientid",modalLogin.getClientid());
            jsonObject.put("lat", lat);
            jsonObject.put("lon", lng);
            jsonObject.put("price_in_usd",mySeletedPlan.getPriceInUsd());
            jsonObject.put("promote_msg",userCampaign.getPromotionMessage());
            jsonObject.put("startTime",milliseconds(userCampaign.getDateTime()));
            jsonObject.put("store_address",address);
            jsonObject.put("web_url",userCampaign.getWebRequestUrl());
            jsonObject.put("api","add_campaign_request");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
        }
        Log.e("JSON STRING", "" + jsonObject.toString());
        return jsonObject;
    }


    public long milliseconds(String date) {
        String date_ = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milli :: " + timeInMilliseconds);
            return timeInMilliseconds;
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
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

}