package com.oxilo.mobikyte.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.oxilo.mobikyte.MODAL.UserCampaign;
import com.oxilo.mobikyte.POJO.ModalAddCampign;
import com.oxilo.mobikyte.POJO.ModalLogin;
import com.oxilo.mobikyte.R;
import com.oxilo.mobikyte.fragement.AboutFragement;
import com.oxilo.mobikyte.fragement.ChangePasswordFragment;
import com.oxilo.mobikyte.fragement.HelpFragement;
import com.oxilo.mobikyte.fragement.Map;
import com.oxilo.mobikyte.fragement.ObjectiveFragement;
import com.oxilo.mobikyte.fragement.Settings;
import com.oxilo.mobikyte.fragement.graph.HeatMapFragement;
import com.oxilo.mobikyte.fragement.graph.PieFragment;
import com.oxilo.mobikyte.logger.Log;
import com.oxilo.mobikyte.utility.ActivityUtils;

public class ObjectiveScreen extends SampleActivityBase implements
        ObjectiveFragement.OnFragmentInteractionListener,
        ReportFragement.OnFragmentInteractionListener,
        Settings.OnFragmentInteractionListener,
        HelpFragement.OnFragmentInteractionListener,
        CampaignListingFragement.OnFragmentInteractionListener,
        PieFragment.OnFragmentInteractionListener,
        InVoiceFragment.OnFragmentInteractionListener,
        HeatMapFragement.OnFragmentInteractionListener,
        ChangePasswordFragment.OnFragmentInteractionListener,
        AboutFragement.OnFragmentInteractionListener{


    ModalLogin modalLogin ; UserCampaign userCampaign;ModalAddCampign modalAddCampign;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    NavigationView navigation;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.objective_screen_2);
        if (getIntent()!=null){
            modalLogin = getIntent().getParcelableExtra(getResources().getString(R.string.praceable_modal_regsitration));
            String ccc = "" + modalLogin.getClientid();
            Log.e("SDGW","" + ccc);
        }
        initDrawerItem();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
            Intent i = new Intent(ObjectiveScreen.this,WelcomeActivity.class);
            i.putExtra(getResources().getString(R.string.praceable_modal_regsitration), modalLogin);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Fragment f = getFragmentManager().findFragmentById(R.id.main_content);
        if (f instanceof HelpFragement) {
            // do something with f
            menu.findItem(R.id.action_about).setVisible(false);
            return true;
        } else {
            menu.findItem(R.id.action_about).setVisible(true);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (drawerToggle.onOptionsItemSelected(item))
//            return true;


        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                if (drawerToggle.onOptionsItemSelected(item))
                    return true;
                return true;
            case R.id.action_about:
                Fragment fragment = HelpFragement.newInstance("", "");
                ActivityUtils.launchFragementWithAnimation(fragment, ObjectiveScreen.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

//        if (id == R.id.action_search) {
//            if (mSearchOpened) {
//                closeSearchBar();
//            } else {
//                openSearchBar();
//            }
//            return true;
//        }

//        return super.onOptionsItemSelected(item);
    }

    /**
     * Initialize all the drawer item here
     * and handling the click event of drawer item
     */
    private void initDrawerItem() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(ObjectiveScreen.this, drawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
//        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerLayout.setDrawerListener(drawerToggle);
        // mDrawerToggle.setHomeAsUpIndicator(R.drawable.menu_icon);

//        toolbar.setNavigationIcon(R.mipmap.mobikyte_logo);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(Gravity.LEFT);
//            }
//        });

        navigation = (NavigationView) findViewById(R.id.navigation);
        View header = navigation.getHeaderView(0);
        TextView userNameView = (TextView)header.findViewById(R.id.username_id);
        userNameView.setText("" + modalLogin.getName());
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem1:
                        Intent i = new Intent(ObjectiveScreen.this,ObjectiveScreen.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.putExtra(getResources().getString(R.string.praceable_modal_regsitration), modalLogin);
                        startActivity(i);
                        break;
                    case R.id.navItem2:
                        refreshFragement(2);
                        break;
                    case R.id.navItem3:
                        refreshFragement(3);
                        break;
                    case R.id.navItem4:
                        refreshFragement(4);
                        break;
                    case R.id.navItem5:
                        ActivityUtils.resetPrefs();
                        final Intent intent = new Intent(ObjectiveScreen.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        ActivityTransitionLauncher.with(ObjectiveScreen.this).from(getWindow().getDecorView().getRootView()).launch(intent);
                        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
                        break;
                    case R.id.navItem6:
                        final Intent mintent = new Intent(ObjectiveScreen.this, WelcomeActivity.class);
                        mintent.putExtra(getResources().getString(R.string.praceable_modal_regsitration), modalLogin);
                        mintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mintent);
                        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
                        break;
                    case R.id.navItem7:
                        refreshFragement(7);
                        break;
                    case R.id.navItem8:
                        refreshFragement(8);
                        break;
                    case R.id.navItem9:
                        refreshFragement(9);
                        break;
                }
                return false;
            }
        });

        refreshFragement(1);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    private void refreshFragement(int item) {
        Fragment mapFragment = null;
        fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (item) {
            case 1:
                mapFragment = ObjectiveFragement.newInstance(modalLogin,"");
                ft.add(R.id.main_content, mapFragment);
                ft.commit();
                break;
            case 2:
                mapFragment = CampaignListingFragement.newInstance(modalLogin, "");
                ActivityUtils.launchFragementWithAnimation(mapFragment, ObjectiveScreen.this);
                break;
            case 3:
                Settings settings = Settings.newInstance(modalLogin, "");
                ActivityUtils.launchFragementWithAnimation(settings, ObjectiveScreen.this);
                break;
            case 4:
                mapFragment = HelpFragement.newInstance("","");
                ActivityUtils.launchFragementWithAnimation(mapFragment, ObjectiveScreen.this);
                break;
            case 7:
                mapFragment = ChangePasswordFragment.newInstance(modalLogin, "");
                ActivityUtils.launchFragementWithAnimation(mapFragment, ObjectiveScreen.this);
                break;
            case 8:
                AboutFragement aboutFragement = AboutFragement.newInstance("", "");
                ActivityUtils.launchFragementWithAnimation(aboutFragement, ObjectiveScreen.this);
                break;
            case 9:
                mapFragment = InVoiceFragment.newInstance(userCampaign,modalAddCampign,modalLogin);
                ActivityUtils.launchFragementWithAnimation(mapFragment,ObjectiveScreen.this);
                break;
            default:
                mapFragment = Map.newInstance("", "", modalLogin);
                ft.add(R.id.main_content, mapFragment);
                ft.commit();
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}