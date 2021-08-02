package com.example.appdb;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //references to controls on layout
    Button btn_propose;
    Button btn_viewBets;
    EditText et_name, et_bet;
    Switch sw_public;
    RecyclerView lv_betStatus;

    ArrayAdapter userArrayAdapter;
    DataBaseHelper dataBaseHelper;

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
        lv_betStatus = findViewById(R.id.lv_betStatus);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        //ShowUsersOnRecyclerView(dataBaseHelper);

        //assign listeners for buttons

        btn_propose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel;

                try {
                    userModel = new UserModel(-1, et_name.getText().toString(), Integer.parseInt(et_bet.getText().toString()), sw_public.isChecked());
                    Toast.makeText(MainActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Wrong input", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, "error", 0, false);

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(userModel);

                Toast.makeText(getApplicationContext(), "Success = " + success, Toast.LENGTH_SHORT).show();

                //ShowUsersOnRecyclerView(dataBaseHelper);

            }
        });

        btn_viewBets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                //ShowUsersOnRecyclerView(dataBaseHelper);


            }

        });

       /* lv_betStatus.setonItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UserModel clickedBet = (UserModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedBet);
                ShowUsersOnRecyclerView(dataBaseHelper) ;
                Toast.makeText(MainActivity.this, "Deleted" + clickedBet.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void ShowUsersOnRecyclerView(DataBaseHelper dataBaseHelper2) {
        userArrayAdapter = new ArrayAdapter<UserModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getAll());
        lv_betStatus.setAdapter(userArrayAdapter);
    }
*/


    }

}