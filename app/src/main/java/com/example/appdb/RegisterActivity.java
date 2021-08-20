package com.example.appdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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

                String registerInput = "";
                Connection connection = null;

                String user = String.valueOf(et_entryNickname);
                String password = String.valueOf(et_entryPassword);
                String email = String.valueOf(et_entryEmail);
                String urlRegister = "http://10.0.2.2:8080/apiz-0.0.1-SNAPSHOT/ExecuteRegister/"+user+"/"+password+"/"+email;

                try {

                    connection = DriverManager.getConnection("http://10.0.2.2:8080/apiz-0.0.1-SNAPSHOT/ExecuteRegister/", "root", "root");


                    CallableStatement cStmt = connection.prepareCall("{call register(?,?,?)}");

                    cStmt.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }


        });


    }


}



