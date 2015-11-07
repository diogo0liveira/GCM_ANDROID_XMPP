package com.diogo.mobile.app.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.diogo.mobile.app.R;
import com.diogo.mobile.app.receiver.NotificationReceiver;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created on 08/10/2015 16:33.
 *
 * @author Diogo Oliveira.
 */
public class GCMListenerService extends GcmListenerService
{
    @Override
    public void onMessageReceived(String from, Bundle data)
    {
        /* Abre "ResultActivity" com a pilha de volta para a "MainActivity" */

        //        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        //        notification.setSmallIcon(R.mipmap.ic_cloud_white_36dp);
        //
        //        notification.setContentTitle("GCM Notificação");
        //        notification.setContentText(data.getString("message"));
        //        notification.setWhen(System.currentTimeMillis());
        //        notification.setAutoCancel(true);
        //
        //        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        //        notification.setLights(Color.RED, 3000, 3000);
        //
        //        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        //        taskStackBuilder.addParentStack(ResultActivity.class);
        //        taskStackBuilder.addNextIntent(new Intent(this, ResultActivity.class));
        //
        //        notification.setContentIntent(taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT));
        //        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        //        notificationManager.notify(1, notification.build());


        /* Abre "SigleActivity" que trabalha com apenas uma instancia e que só é aberta bela notificação */

        //        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        //        notification.setSmallIcon(R.mipmap.ic_cloud_white_36dp);
        //
        //        notification.setContentTitle("GCM Notificação");
        //        notification.setContentText(data.getString("message"));
        //        notification.setWhen(System.currentTimeMillis());
        //        notification.setAutoCancel(true);
        //
        //        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        //        notification.setLights(Color.RED, 3000, 3000);
        //
        //        Intent intent = new Intent(this, SingleActivity.class);
        //        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //
        //        notification.setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        //        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        //        notificationManager.notify(1, notification.build());


        /* Notificação com botões de ação (Consultar para exibir buttons para APIs < 21 "https://www.youtube.com/watch?v=Libe-6QCv9A") */

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setSmallIcon(R.mipmap.ic_cloud_white_36dp);

        notification.setContentTitle("GCM Notificação");
        notification.setContentText(data.getString("message"));
        notification.setWhen(System.currentTimeMillis());

        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        notification.setLights(Color.RED, 3000, 3000);

        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.setAction(NotificationReceiver.YES_NOTIFICATION_BUTTON);
        notification.addAction(R.mipmap.ic_cloud_white_36dp, "YES", PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT));

        intent = new Intent(this, NotificationReceiver.class);
        intent.setAction(NotificationReceiver.MAYBE_NOTIFICATION_BUTTON);
        notification.addAction(R.mipmap.ic_cloud_white_36dp, "MAYBE", PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT));

        intent = new Intent(this, NotificationReceiver.class);
        intent.setAction(NotificationReceiver.NO_NOTIFICATION_BUTTON);
        notification.addAction(R.mipmap.ic_cloud_white_36dp, "NO", PendingIntent.getBroadcast(this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT));

        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification.build());
    }
}
