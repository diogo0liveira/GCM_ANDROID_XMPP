package com.diogo.mobile.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.diogo.mobile.app.gcm.RegistrationIntentService;
import com.diogo.mobile.app.model.Device;
import com.diogo.mobile.app.util.JsonKey;
import com.diogo.mobile.app.util.JsonValue;
import com.diogo.mobile.app.util.OnReceiverActivity;
import com.diogo.mobile.app.util.ResponseActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class MainActivity extends AppCompatActivity implements OnReceiverActivity
{
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private TextView textViewId;
    private TextView textViewRegistrationId;
    private TextView textViewRegistrationDate;

    @Override
    protected void onStart()
    {
        super.onStart();
        ResponseActivity.setActivity(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        ResponseActivity.removeActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)findViewById(R.id.acti_main_toolbar));
        ResponseActivity.setActivity(this);

        textViewId = (TextView)findViewById(R.id.acti_main_textView_id);
        textViewRegistrationId = (TextView)findViewById(R.id.acti_main_textView_registration_id);
        textViewRegistrationDate = (TextView)findViewById(R.id.acti_main_textView_registration_date);

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.acti_main_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Comunicando com GCM...", Snackbar.LENGTH_SHORT).show();

                if(isPlayServices())
                {
                    startService(new Intent(MainActivity.this, RegistrationIntentService.class));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //noinspection SimplifiableIfStatement
        if(item.getItemId() == R.id.action_settings)
        {
            startActivity(new Intent(this, PushActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isPlayServices()
    {
        int intResultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if(intResultCode != ConnectionResult.SUCCESS)
        {
            if(GooglePlayServicesUtil.isUserRecoverableError(intResultCode))
            {
                GooglePlayServicesUtil.getErrorDialog(intResultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            else
            {
                Log.i("LOG", "Este dispositivo não é suportado.");
                finish();
            }

            return false;
        }

        return true;
    }

    @Override
    public void onReceiver(Bundle bundle)
    {
        switch(bundle.getString(JsonKey.ACTION, null))
        {
            case JsonValue.REGISTER_USER:
            {
                textViewId.setText(bundle.getInt(Device.ID));
                textViewRegistrationId.setText(bundle.getString(Device.REGISTRATION_ID));
                textViewRegistrationDate.setText(bundle.getString(Device.REGISTRATION_DATE));
            }
        }
    }
}
