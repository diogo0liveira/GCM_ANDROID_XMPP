package com.diogo.mobile.app.network;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.diogo.mobile.app.util.Extras;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created on 08/10/2015 15:36.
 *
 * @author Diogo Oliveira.
 */
public class NetworkConnection
{
    private static NetworkConnection networkConnection;
    private RequestQueue requestQueue;
    private JsonRequest jsonRequest;
    private Context context;

    public NetworkConnection(Context context)
    {
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    public static NetworkConnection getInstance(Context context)
    {
        if(networkConnection == null)
        {
            networkConnection = new NetworkConnection(context.getApplicationContext());
        }

        return networkConnection;
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(context);
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request)
    {
        getRequestQueue().add(request);
    }

    public void execute(final WrapObjectToNetwork wrapObjectToNetwork, final ResultResponse resultResponse)
    {
        JSONObject jsonObject = null;

        try
        {
            jsonObject = new JSONObject(new Gson().toJson(wrapObjectToNetwork.getObject()));
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        jsonRequest = new JsonRequest(wrapObjectToNetwork.getMethod(), Extras.SERVER, jsonObject, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject jsonObject)
            {
                resultResponse.success(jsonRequest, jsonObject);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                resultResponse.error(volleyError);
            }
        });

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addToRequestQueue(jsonRequest);
    }
}
