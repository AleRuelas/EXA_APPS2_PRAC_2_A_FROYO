package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        inDatos.putExtra("apellido",list.get(position).getApellido());
        inDatos.putExtra("nombre",list.get(position).getNombre());
        inDatos.putExtra("usuario",list.get(position).getUsuario());
        inDatos.putExtra("password",list.get(position).getPassword());
        setResult(Activity.RESULT_OK, inDatos);
        finish();
    }
}
