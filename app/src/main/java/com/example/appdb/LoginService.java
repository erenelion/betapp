package com.example.appdb;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginService {

    public static final String QUERY_FOR_LOGIN() {
        return "http://127.0.0.1:8080/apiz-0.0.1-SNAPSHOT/ExecuteLogin/";
    }


    Context context;

    public LoginService(Context context) {
        this.context = context;
    }

    public String getLoginData (String getUser, String getPassword) {


        String urlLogin = QUERY_FOR_LOGIN() + getUser + "/" + getPassword;

        JsonArrayRequest jRequest = new JsonArrayRequest(Request.Method.GET,urlLogin,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String userData = "";

                try {
                    JSONObject userNickname = response.getJSONObject(0);
                    String userData = userInfo.getString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,"Error in login",Toast.LENGTH_SHORT).show();

            }
        });

            

        MySingleton.getInstance(context).addToRequestQueue(jRequest);

        return userData;
    }

}
