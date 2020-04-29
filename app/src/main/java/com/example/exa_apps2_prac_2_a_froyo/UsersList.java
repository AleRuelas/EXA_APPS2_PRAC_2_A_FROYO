package com.example.exa_apps2_prac_2_a_froyo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UsersList extends AppCompatActivity implements ListView.OnItemClickListener {
    ArrayList<UserClass> list;
    ListView usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        usersList = findViewById(R.id.usersList);
        usersList.setOnItemClickListener(this);
        DB db = new DB(getApplicationContext(),null,null,1);
        list = db.selectList();
        UserAdapter adapter  = new UserAdapter(this,list);
        usersList.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent inDatos = new Intent(this, UsersActivity.class);

        SharedPreferences preferences = this.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", list.get(position).getId());
        editor.putString("apellido", list.get(position).getApellido());
        editor.putString("nombre", list.get(position).getNombre());
        editor.putString("usuario", list.get(position).getUsuario());
        editor.putString("password", list.get(position).getPassword());
        editor.commit();

        setResult(Activity.RESULT_OK, inDatos);
        finish();
    }
}
