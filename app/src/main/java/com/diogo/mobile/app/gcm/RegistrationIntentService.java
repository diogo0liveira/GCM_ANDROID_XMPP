package com.diogo.mobile.app.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.diogo.mobile.app.model.Cliente;
import com.diogo.mobile.app.network.JsonRequest;
import com.diogo.mobile.app.network.NetworkConnection;
import com.diogo.mobile.app.network.ResultResponse;
import com.diogo.mobile.app.network.wrapper.WrapCliente;
import com.diogo.mobile.app.util.Extras;
import com.diogo.mobile.app.util.ResponseActivity;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created on 08/10/2015 15:06.
 *
 * @author Diogo Oliveira.
 */
public class RegistrationIntentService extends IntentService implements ResultResponse
{
    private static final String LOG = "LOG";

    public RegistrationIntentService()
    {
        super(LOG);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        synchronized(LOG)
        {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean bolStatus = preferences.getBoolean(Extras.PREFERENCES_STATUS, false);
            InstanceID instanceID = InstanceID.getInstance(this);

            try
            {
                if(!bolStatus)
                {
                    String strToken = instanceID.getToken(Extras.SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                    preferences.edit().putBoolean(Extras.PREFERENCES_STATUS, ((strToken != null) && (TextUtils.isEmpty(strToken.trim())))).apply();
                    sendRegistrationToServer(strToken);
                    Log.e(Extras.TAG, strToken);
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void sendRegistrationToServer(String token)
    {
        NetworkConnection.getInstance(this).execute(new WrapCliente(Request.Method.POST, new Cliente(token)), this);
    }

    @Override
    public void success(JsonRequest jsonRequest, JSONObject jsonObject)
    {
        ((ResultResponse)ResponseActivity.getActivity()).success(jsonRequest, jsonObject);
    }

    @Override
    public void error(VolleyError volleyError)
    {
        ((ResultResponse)ResponseActivity.getActivity()).error(volleyError);
    }
}
