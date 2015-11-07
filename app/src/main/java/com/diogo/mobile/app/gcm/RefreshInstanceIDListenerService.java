package com.diogo.mobile.app.gcm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.diogo.mobile.app.util.Extras;
import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created on 09/10/2015 11:17.
 *
 * @author Diogo Oliveira.
 */
public class RefreshInstanceIDListenerService extends InstanceIDListenerService
{
    @Override
    public void onTokenRefresh()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putBoolean(Extras.PREFERENCES_STATUS, false).apply();
        startActivity(new Intent(this, RegistrationIntentService.class));
    }
}
