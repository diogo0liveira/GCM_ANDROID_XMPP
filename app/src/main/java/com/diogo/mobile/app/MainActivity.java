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

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.diogo.mobile.app.gcm.RegistrationIntentService;
import com.diogo.mobile.app.network.JsonRequest;
import com.diogo.mobile.app.network.ResultResponse;
import com.diogo.mobile.app.util.ResponseActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.text.DateFormat;

public class MainActivity extends AppCompatActivity implements ResultResponse
{
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private TextView textViewCode;
    private TextView textViewMethod;
    private TextView textViewJson;

    private TextView textViewCliente;
    private TextView textViewId;
    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)findViewById(R.id.acti_main_toolbar));
        ResponseActivity.setActivity(this);

        textViewCode = (TextView)findViewById(R.id.acti_main_textView_code);
        textViewMethod = (TextView)findViewById(R.id.acti_main_textView_method);
        textViewJson = (TextView)findViewById(R.id.acti_main_textView_json);
        textViewCliente = (TextView)findViewById(R.id.acti_main_textView_cliente);
        textViewId = (TextView)findViewById(R.id.acti_main_textView_id);
        textViewDate = (TextView)findViewById(R.id.acti_main_textView_date);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(JsonRequest jsonRequest, JSONObject jsonObject)
    {
        try
        {
            if(jsonObject.getInt("id") > 0)
            {
                String strMethod;

                switch(jsonRequest.getMethod())
                {
                    case Request.Method.GET:
                    {
                        strMethod = "GET";
                        break;
                    }
                    case Request.Method.POST:
                    {
                        strMethod = "POST";
                        break;
                    }
                    case Request.Method.PUT:
                    {
                        strMethod = "PUT";
                        break;
                    }
                    case Request.Method.DELETE:
                    {
                        strMethod = "DELETE";
                        break;
                    }
                    case Request.Method.HEAD:
                    {
                        strMethod = "HEAD";
                        break;
                    }
                    case Request.Method.OPTIONS:
                    {
                        strMethod = "OPTIONS";
                        break;
                    }
                    case Request.Method.TRACE:
                    {
                        strMethod = "TRACE";
                        break;
                    }
                    case Request.Method.PATCH:
                    {
                        strMethod = "PATCH";
                        break;
                    }
                    default:
                    {
                        strMethod = "";
                        break;
                    }
                }

                textViewCode.setText(String.format("CODE: %d", jsonRequest.getResponseStatusCode()));
                textViewMethod.setText(String.format("METHOD: %s", strMethod));
                textViewJson.setText(new Gson().toJson(jsonObject));


                textViewCliente.setText("[CLIENTE]");
                textViewId.setText(String.format("ID: %d", jsonObject.getInt("id")));
                textViewDate.setText(String.format("DATE: %s", DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.DEFAULT).format(jsonObject.getLong("registrationDate"))));
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void error(VolleyError volleyError)
    {
        if(volleyError.getCause().getMessage() != null)
        {
            textViewCode.setText(volleyError.getCause().getMessage());
        }

        textViewMethod.setText("");
        textViewJson.setText("");
        textViewCliente.setText("");
        textViewId.setText("");
        textViewDate.setText("");
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
}
