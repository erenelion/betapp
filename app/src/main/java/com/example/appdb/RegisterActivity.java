package com.example.appdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RegisterActivity extends AppCompatActivity {

    Button btn_entryRegister;
    EditText et_entryNickname;
    EditText et_entryEmail;
    EditText et_entryPassword;
    EditText et_confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_entryRegister = findViewById(R.id.btn_entryRegister);
        et_entryNickname = findViewById(R.id.et_entryNickname);
        et_entryEmail = findViewById(R.id.et_entryEmail);
        et_entryPassword = findViewById(R.id.et_entryPassword);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);



        btn_entryRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestRegister = Volley.newRequestQueue(RegisterActivity.this);

                String user = String.valueOf(et_entryNickname);
                String password = String.valueOf(et_entryPassword);
                String email = String.valueOf(et_entryEmail);
                String urlRegister = "http://127.0.0.1:8080/apiz-0.0.1-SNAPSHOT/ExecuteRegister/"+user+"/"+password+"/"+email;

                JsonArrayRequest jRequest = new JsonArrayRequest(Request.Method.GET,urlRegister,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Toast.makeText(RegisterActivity.this,response.toString(),Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(RegisterActivity.this,"Error in login",Toast.LENGTH_SHORT).show();

                    }
                });

                MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(jRequest);

                //StringRequest stringRequest = new StringRequest(Request.Method.GET, urlRegister,
  //                      new Response.Listener<String>() {
  //                          @Override
  //                          public void onResponse(String response) {
  //                              Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_SHORT).show();
  //                          }
  //                      }, new Response.ErrorListener() {
  //                  @Override
  //                  public void onErrorResponse(VolleyError error) {
  //                      Toast.makeText(RegisterActivity.this,"Error in login",Toast.LENGTH_SHORT).show();
  //                  }
  //              });

  //              requestRegister.add(stringRequest);
            }


        });



    }


}



