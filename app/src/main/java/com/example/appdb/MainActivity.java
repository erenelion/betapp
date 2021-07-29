package com.example.appdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ListViewCompat;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //references to controls on layout
    Button btn_propose;
    Button btn_viewBets;
    EditText et_name, et_bet;
    Switch sw_public;
    ListView lv_betStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign values

        btn_propose = findViewById(R.id.btn_propose);
        btn_viewBets = findViewById(R.id.btn_viewBets);
        et_name = findViewById(R.id.et_name);
        et_bet = findViewById(R.id.et_bet);
        sw_public = findViewById(R.id.sw_public);
        //lv_betStatus = findViewById(R.id.lv_betStatus);

        //assign listeners for buttons

        btn_propose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel;

                try {
                    userModel = new UserModel(-1, et_name.getText().toString(), Integer.parseInt(et_bet.getText().toString()), sw_public.isChecked());
                    Toast.makeText(MainActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();
                }

                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Wrong input", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, "error", 0, false);

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(userModel);

                Toast.makeText(getApplicationContext(), "Success = "+ success, Toast.LENGTH_SHORT).show();

            }
        });

        btn_viewBets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<UserModel> all = dataBaseHelper.getAll();

                Toast.makeText(getApplicationContext(), all.toString(), Toast.LENGTH_SHORT).show();

            }

        });


    }

}