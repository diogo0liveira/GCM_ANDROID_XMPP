package com.diogo.mobile.app.util;

import android.app.Activity;

/**
 * Created on 14/10/2015 15:50.
 *
 * @author Diogo Oliveira.
 */
public class ResponseActivity
{
    private static Activity activity;

    public static Activity getActivity()
    {
        return activity;
    }

    public static void setActivity(Activity activity)
    {
        ResponseActivity.activity = activity;
    }
}
