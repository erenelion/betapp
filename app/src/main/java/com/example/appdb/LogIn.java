package com.example.appdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    Button btn_login;
    Button btn_register;
    EditText et_nickname, et_password;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        et_nickname = findViewById(R.id.et_nickname);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection conexionMySQL = null;

                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance ();


                    conexionMySQL = DriverManager.getConnection("jdbc:mysql://10.0.2.2/betapp", "root", "apuesta");

                    Toast.makeText(LogIn.this, "Connected to db", Toast.LENGTH_SHORT).show();

                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InstantiationException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }

        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);

            }

        });



    }
}