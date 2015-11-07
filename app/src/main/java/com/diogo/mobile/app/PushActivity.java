package com.diogo.mobile.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.diogo.mobile.app.util.Extras;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class PushActivity extends AppCompatActivity
{

    private GoogleCloudMessaging googleCloudMessaging;

    @Override
    protected void onStart()
    {
        super.onStart();

        if(googleCloudMessaging == null)
        {
            googleCloudMessaging = GoogleCloudMessaging.getInstance(this);
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if(googleCloudMessaging != null)
        {
            googleCloudMessaging.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        Toolbar toolbar = (Toolbar)findViewById(R.id.acti_push_toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.acti_push_fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle bundle = new Bundle();
                bundle.putString("ACTION", "UPSTREAM_MESSAGE");
                bundle.putString("EXTRA_KEY_MESSAGE", "DIOGO ARAUJO OLIVEIRA");

                try
                {
                    googleCloudMessaging.send(Extras.SENDER_ID + "@gcm.googleapis.com", "10000", bundle);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
