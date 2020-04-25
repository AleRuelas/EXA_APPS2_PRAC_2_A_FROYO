package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UsersActivity extends AppCompatActivity {

    Intent inList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        inList = new Intent(this, UsersList.class);
    }

    public void open(View view){

        startActivity(inList);
    }
}
