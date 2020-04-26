package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class UsersList extends AppCompatActivity implements ListView.OnItemClickListener {

    ListView usersList;
    UserClass[] users = {
            new UserClass("Ruelas","Alejandra","ale03","12345"),
            new UserClass("Vazquez","Cinthia","cinthia15","12345"),
            new UserClass("Ramirez","Francisco","fran87","12345"),
            new UserClass("Lopez","Andres","andres2","12345"),
            new UserClass("Dominguez","Lola","Lola2","12345"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        usersList = findViewById(R.id.usersList);
        usersList.setAdapter(new UserAdapter(this, R.layout.layout_user,users));
        usersList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent inDatos = new Intent();
        inDatos.putExtra("usuario",users[position].getUsuario());
        inDatos.putExtra("apellido",users[position].getApellido());
        inDatos.putExtra("nombre",users[position].getNombre());

        setResult(Activity.RESULT_OK, inDatos);

        Toast.makeText(this, users[position].getUsuario(), Toast.LENGTH_SHORT).show();
    }
}
