package com.oxilo.mobikyte.fragement;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oxilo.mobikyte.ApplicationController;
import com.oxilo.mobikyte.MODAL.UserCampaign;
import com.oxilo.mobikyte.MyAnimator;
import com.oxilo.mobikyte.POJO.CampList;
import com.oxilo.mobikyte.POJO.InVoiceObject;
import com.oxilo.mobikyte.POJO.InvoiceList;
import com.oxilo.mobikyte.POJO.ModalAddCampign;
import com.oxilo.mobikyte.POJO.ModalAlreadyRegistred;
import com.oxilo.mobikyte.POJO.ModalCheckOut;
import com.oxilo.mobikyte.POJO.ModalFetchCampaign;
import com.oxilo.mobikyte.POJO.ModalInVoice;
import com.oxilo.mobikyte.POJO.ModalLogin;
import com.oxilo.mobikyte.R;
import com.oxilo.mobikyte.adapter.InVoiceAdapter;
import com.oxilo.mobikyte.expandcollapse.InVoiceExpandableAdapter;
import com.oxilo.mobikyte.expandcollapse.VerticalChild;
import com.oxilo.mobikyte.expandcollapse.VerticalExpandableAdapter;
import com.oxilo.mobikyte.expandcollapse.VerticalParent;
import com.oxilo.mobikyte.holder.GroupItem;
import com.oxilo.mobikyte.logger.Log;
import com.oxilo.mobikyte.utility.ActivityUtils;
import com.oxilo.mobikyte.utility.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InVoiceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InVoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InVoiceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private UserCampaign userCampaign;
    private ModalAddCampign modalAddCampign;
    private ModalLogin modalLogin;

    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    //    Toolbar toolbar;
    private GroupItem groupItem;

    private View mProgressView;
    private View mLoginFormView;
    private InVoiceExpandableAdapter mExpandableAdapter;

    public InVoiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userCampaign Parameter 1.
     * @param modalAddCampign Parameter 2.
     * @param modalLogin Parameter 3.
     * @return A new instance of fragment InVoiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InVoiceFragment newInstance(UserCampaign userCampaign,ModalAddCampign modalAddCampign, ModalLogin modalLogin) {
        InVoiceFragment fragment = new InVoiceFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, userCampaign);
        args.putParcelable(ARG_PARAM2, modalAddCampign);
        args.putParcelable(ARG_PARAM3, modalLogin);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userCampaign = getArguments().getParcelable(ARG_PARAM1);
            modalAddCampign = getArguments().getParcelable(ARG_PARAM2);
            modalLogin = getArguments().getParcelable(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_voice, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUiWidget(view);
        if (groupItem == null)
            groupItem = new GroupItem();
        getInVoice();
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
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
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
//        toolbar.setTitle("Invoice");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView toolbar_title = (TextView) getActivity().findViewById(R.id.toolbar_title);
        toolbar_title.setText("Invoice");
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
        void onFragmentInteraction(Uri uri);
    }

    private void initUiWidget(View view){
//        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // update the main content by replacing fragments
//                getFragmentManager().popBackStack();
//            }
//        });
//
//
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);

        mLoginFormView = view.findViewById(R.id.login_form);
        mProgressView = view.findViewById(R.id.login_progress);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyle_2);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                InVoiceAdapter inVoiceAdapter = new InVoiceAdapter(groupItem.items);
//                recyclerView.setAdapter(inVoiceAdapter);
//            }
//        }, 3000);


    }

    /**
     * NETWORK SIGNUP CALL
     *
     */
    public void getInVoice() {
        showProgress(true);
        String URL = getResources().getString(R.string.mobikyte_campaign_invoice_url) + "data=" + Uri.encode(makeJsonBody().toString());
        StringRequest req = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showProgress(false);
                VolleyLog.v("Response:%n %s", response);
                Gson gson = new GsonBuilder().create();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals(getResources().getString(R.string.response_success))){
                        InvoiceList invoiceList = gson.fromJson(response, InvoiceList.class);
                        groupItem.items = invoiceList.getInvoiceList();
                        // Create a new adapter with  data items
                        mExpandableAdapter = new InVoiceExpandableAdapter(getActivity(), setUpList(groupItem.items), new InVoiceExpandableAdapter.DashBoard() {
                            @Override
                            public void onItemClick(int position, View v) {

                            }
                        },modalLogin);

                        // Attach this activity to the Adapter as the ExpandCollapseListener
                        mExpandableAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
                            @Override
                            public void onListItemExpanded(int position) {
                                Log.e("CHEEE","" + position);

                            }

                            @Override
                            public void onListItemCollapsed(int position) {

                            }
                        });

                        // Set the RecyclerView's adapter to the ExpandableAdapter we just created
                        recyclerView.setAdapter(mExpandableAdapter);
                        // Set the layout manager to a LinearLayout manager for vertical list
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                    else if (jsonObject.getString("status").equals(getResources().getString(R.string.response_error))){
                        Toast.makeText(getActivity(), "Somethin went wrong, please try again", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getActivity(), "Something went wrong. please try it again", Toast.LENGTH_SHORT).show();
            }
        });

        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req,ApplicationController.REGISTRATION_TAG);
    }



    private JSONObject makeJsonBody(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clientid", "" + modalLogin.getClientid());
            jsonObject.put("api","get_invoice_list");
        } catch (JSONException e) {
            e.printStackTrace();
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

    /**
     * Method to set up test data used in the RecyclerView.
     *
     * Each child list item contains a string.
     * Each parent list item contains a number corresponding to the number of the parent and a string
     * that contains a message.
     * Each parent also contains a list of children which is generated in this. Every odd numbered
     * parent gets one child and every even numbered parent gets two children.
     *
     * @return A List of Objects that contains all parent items. Expansion of children are handled in the adapter
     */
    private List<VerticalParent> setUpList(List<InVoiceObject> invoiceList) {
//        DateUtils dateUtils = new DateUtils();
//        Collections.sort(invoiceList, dateUtils);
        List<VerticalParent> verticalParentList = new ArrayList<>();

        for (int i = 0; i < invoiceList.size(); i++) {
            List<VerticalChild> childItemList = new ArrayList<>();

            InVoiceObject inVoiceObject = invoiceList.get(i);

            VerticalChild verticalChild = new VerticalChild();
            verticalChild.setInVoiceObject(inVoiceObject);
            childItemList.add(verticalChild);

            VerticalParent verticalParent = new VerticalParent();
            verticalParent.setChildItemList(childItemList);
            verticalParent.setParentNumber(i);
            String ss = "" + inVoiceObject.getCreateDate();
            verticalParent.setParentText("" + inVoiceObject.getCampaignname()+" : "+ActivityUtils.GetMonthDate(ss));

//            verticalParent.setParentText("" + inVoiceObject.getCreateDate());

//       verticalParent.setParentText("" + ActivityUtils.GetDateTime(Long.valueOf(campList.getStartDate())));
            if (i == 0) {
                verticalParent.setInitiallyExpanded(true);
            }
            verticalParentList.add(verticalParent);
        }
        return verticalParentList;
    }
}