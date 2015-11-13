package com.diogo.mobile.app.gcm;

import android.content.Context;
import android.os.Bundle;

import com.diogo.mobile.app.util.Extras;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.UUID;

/**
 * Created on 12/11/2015 09:52.
 *
 * @author Diogo Oliveira.
 */
public class PushGoogleCloudMessaging
{
    private static String SENDER = Extras.SENDER_ID + "@gcm.googleapis.com";

    public static void push(Context context, Bundle bundle)
    {
        push(context, UUID.randomUUID().toString(), bundle);
    }

    public static void push(Context context, String messageId, Bundle bundle)
    {
        try
        {
            GoogleCloudMessaging googleCloudMessaging = GoogleCloudMessaging.getInstance(context);
            googleCloudMessaging.send(SENDER, messageId, bundle);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void push(Context context, long timeToLive, Bundle bundle)
    {
        try
        {
            GoogleCloudMessaging googleCloudMessaging = GoogleCloudMessaging.getInstance(context);
            googleCloudMessaging.send(SENDER, UUID.randomUUID().toString(), timeToLive, bundle);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void push(Context context, String messageId, long timeToLive, Bundle bundle)
    {
        try
        {
            GoogleCloudMessaging googleCloudMessaging = GoogleCloudMessaging.getInstance(context);
            googleCloudMessaging.send(SENDER, messageId, timeToLive, bundle);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
