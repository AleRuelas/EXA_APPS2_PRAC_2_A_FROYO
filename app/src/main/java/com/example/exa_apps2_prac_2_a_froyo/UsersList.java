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
        DB db = new DB(getApplicationContext(),null,null,1); //CONEXION A LA BASE DE DATOS
        list = db.selectList(); //SE MANDA A LLARMAR AL METODO QUE LLENARÁ LA LISTA
        //SE VINCULA CON EL ADAPTADOR CORRESPONDIENTE
        UserAdapter adapter  = new UserAdapter(this,list);
        usersList.setAdapter(adapter);
    }

    //MÉTODO QUE SE EJECUTA CUANDO EL USUARIO DA CLIC A UN ELEMENTO EN LA LISTA
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //SE MANDA POR MEDIO DE UN INTENTO LA INFORMACIÓN DE LA LISTA A LA CLASE USERSACTIVITY
        Intent inDatos = new Intent(this, UsersActivity.class);
        SharedPreferences preferences = this.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", list.get(position).getId());
        editor.putString("apellido", list.get(position).getApellido());
        editor.putString("nombre", list.get(position).getNombre());
        editor.putString("usuario", list.get(position).getUsuario());
        editor.putString("password", list.get(position).getPassword());
        editor.commit();
        setResult(Activity.RESULT_OK, inDatos); //SE MANDA EL RESULTADO AL MÉTODO ONACTIVITYRESULT
        finish(); //SE FINALIZA LA ACTIVIDAD
    }
}
