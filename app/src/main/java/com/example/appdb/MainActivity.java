package com.example.appdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {


    Button btn_login;
    Button btn_register;
    EditText et_nickname, et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        et_nickname = findViewById(R.id.et_nickname);
        et_password = findViewById(R.id.et_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Connection connection = null;

                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance ();


                    connection = DriverManager.getConnection("jdbc:mysql://10.0.2.2/betapp", "root", "apuesta");

                    CallableStatement cStmt = connection.prepareCall("{call insert_user_procedure_andList(?,?)}");
                    cStmt.setString(1, String.valueOf(et_nickname));
                    cStmt.setString(2, String.valueOf(et_password));

                    cStmt.execute();
    
                    Toast.makeText(MainActivity.this, "Connected to db", Toast.LENGTH_SHORT).show();

                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InstantiationException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (
                        SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }

        });



    }
}