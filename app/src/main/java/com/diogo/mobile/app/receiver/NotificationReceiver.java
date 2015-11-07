package com.diogo.mobile.app.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created on 16/10/2015 17:06.
 *
 * @author Diogo Oliveira.
 */
public class NotificationReceiver extends BroadcastReceiver
{
    public static final String YES_NOTIFICATION_BUTTON = "com.diogo.mobile.app.receiver.YES_NOTIFICATION_BUTTON";
    public static final String MAYBE_NOTIFICATION_BUTTON = "com.diogo.mobile.app.receiver.MAYBE_NOTIFICATION_BUTTON";
    public static final String NO_NOTIFICATION_BUTTON = "com.diogo.mobile.app.receiver.NO_NOTIFICATION_BUTTON";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        switch(intent.getAction())
        {
            case NotificationReceiver.YES_NOTIFICATION_BUTTON:
            {
                Toast.makeText(context, "YES CLICKED", Toast.LENGTH_SHORT).show();
                break;
            }
            case NotificationReceiver.MAYBE_NOTIFICATION_BUTTON:
            {
                Toast.makeText(context, "MAYBE CLICKED", Toast.LENGTH_SHORT).show();
                break;
            }
            case NotificationReceiver.NO_NOTIFICATION_BUTTON:
            {
                Toast.makeText(context, "NO CLICKED", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}
