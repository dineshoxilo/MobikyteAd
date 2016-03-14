package com.oxilo.mobikyte.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.kogitune.activity_transition.ExitActivityTransition;
import com.oxilo.mobikyte.POJO.ModalLogin;
import com.oxilo.mobikyte.R;
import com.oxilo.mobikyte.fragement.LearnMoreFragment;

public class Mobikyte extends SampleActivityBase implements LearnMoreFragment.OnFragmentInteractionListener{

    //Activity Transition Instance
    ExitActivityTransition exitTransition;
    private static final int CONTENT_VIEW_ID = 10101010;
    private ModalLogin modalLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobikyte);
//        exitTransition = ActivityTransition.with(getIntent()).to(findViewById(R.id.image)).start(savedInstanceState);

        final TextView learn_more = (TextView)findViewById(R.id.learn_more);
//        learn_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Intent intent = new Intent(Mobikyte.this, Learn_More_Activity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//               startActivity(intent);
//                return;
//            }
//        });
        TextView get_started = (TextView)findViewById(R.id.get_started);
        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Mobikyte.this, SignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ActivityTransitionLauncher.with(Mobikyte.this).from(view).launch(intent);
                return;
            }
        });
        TextView already_registered = (TextView)findViewById(R.id.already_registered);
        already_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Mobikyte.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ActivityTransitionLauncher.with(Mobikyte.this).from(view).launch(intent);
                return;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (exitTransition!=null){
            exitTransition.exit(Mobikyte.this);
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
