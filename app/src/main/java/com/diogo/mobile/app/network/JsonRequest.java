package com.diogo.mobile.app.network;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created on 14/10/2015 10:55.
 *
 * @author Diogo Oliveira.
 */
public class JsonRequest extends JsonObjectRequest
{
    private int responseStatusCode;

    public int getResponseStatusCode()
    {
        return responseStatusCode;
    }

    public JsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener)
    {
        super(method, url, jsonRequest, responseListener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
    {
        responseStatusCode = response.statusCode;
        return super.parseNetworkResponse(response);
    }
}
