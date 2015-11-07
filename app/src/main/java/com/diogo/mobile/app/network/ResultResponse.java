package com.diogo.mobile.app.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created on 13/10/2015 11:40.
 *
 * @author Diogo Oliveira.
 */
public interface ResultResponse
{
    void success(JsonRequest jsonRequest, JSONObject jsonObject);
    void error(VolleyError volleyError);
}
