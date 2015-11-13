package com.diogo.mobile.app.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.diogo.mobile.app.model.Device;
import com.diogo.mobile.app.util.Extras;
import com.diogo.mobile.app.util.JsonKey;
import com.diogo.mobile.app.util.JsonValue;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created on 08/10/2015 15:06.
 *
 * @author Diogo Oliveira.
 */
public class RegistrationIntentService extends IntentService
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
                //if(!bolStatus)
                //{
                    String strToken = instanceID.getToken(Extras.SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                    preferences.edit().putBoolean(Extras.PREFERENCES_STATUS, ((strToken != null) && (!TextUtils.isEmpty(strToken.trim())))).apply();
                    sendRegistrationToServer(strToken);
                    Log.e(Extras.TAG, strToken);
                //}
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void sendRegistrationToServer(String token)
    {
        try
        {
            Bundle bundle = new Bundle();
            JSONObject jsonObject = new JSONObject(new Gson().toJson(new Device(token)));
            Iterator<String> iterator = jsonObject.keys();

            while(iterator.hasNext())
            {
                String jsonKey = iterator.next();
                bundle.putString(jsonKey, jsonObject.getString(jsonKey));
            }

            bundle.putString(JsonKey.ACTION, JsonValue.REGISTER_USER);

            PushGoogleCloudMessaging.push(this, bundle);
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }
}
