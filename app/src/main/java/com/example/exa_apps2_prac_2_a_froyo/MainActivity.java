package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent inUsers,inLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inUsers = new Intent(this, UsersActivity.class);
        inLogin = new Intent(this, LoginActivity.class);
    }

    public void usersClick(View view){

        startActivity(inUsers);
    }
    public void loginClick(View view){

        startActivity(inLogin);
    }
    public void exitClick(View view){
        System.exit(0);
    }

}
