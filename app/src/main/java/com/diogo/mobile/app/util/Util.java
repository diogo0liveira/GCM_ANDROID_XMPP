package com.diogo.mobile.app.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

/**
 * Created on 16/10/2015 16:23.
 *
 * @author Diogo Oliveira.
 */
public class Util
{
    @SuppressWarnings("deprecation")
    public static boolean isRunning(Context context)
    {
        String packageName = context.getPackageName();
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        if(runningTasks != null && runningTasks.size() > 0)
        {
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
            String strPackageName = runningTaskInfo.baseActivity.getPackageName();

            if(strPackageName.equals(packageName))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isConnected(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }
}
