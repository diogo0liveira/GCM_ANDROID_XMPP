package com.diogo.mobile.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.diogo.mobile.app.gcm.PushGoogleCloudMessaging;
import com.google.android.gms.gcm.GoogleCloudMessaging;

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


        final EditText editText = (EditText)findViewById(R.id.acti_push_editText_mensagem);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.acti_push_fab);

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(((editText.getText() != null) && TextUtils.isEmpty(editText.getText())))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("action", "upstream_message");
                    bundle.putString("data", editText.getText().toString());

                    PushGoogleCloudMessaging.push(getBaseContext(), bundle);
                }
            }
        });
    }
}
