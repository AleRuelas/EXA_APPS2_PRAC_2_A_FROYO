package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsersActivity extends AppCompatActivity {

    EditText apellido, nombre, usuario, password;
    Intent inList;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        apellido = findViewById(R.id.edApellido);
        nombre = findViewById(R.id.edNombre);
        usuario = findViewById(R.id.edUsuario);
        password = findViewById(R.id.edPass);
        inList = new Intent(UsersActivity.this, UsersList.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {

                SharedPreferences preferences = this.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
                apellido.setText(preferences.getString("apellido", "error"));
                nombre.setText(preferences.getString("nombre", "error"));
                usuario.setText(preferences.getString("usuario", "error"));
                password.setText(preferences.getString("password", "error"));
                preferences.edit().clear().commit();
            }
        }
    }

    public void open(View view) {
        startActivityForResult(inList, 1000);
    }

    public void add(View view) {
        if (apellido.getText().toString().equals("") || nombre.getText().toString().equals("") || usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Inserta la información solicitada.", Toast.LENGTH_SHORT).show();
        } else {
            DB db = new DB(getApplicationContext(), null, null, 1);
            String ap = apellido.getText().toString();
            String nom = nombre.getText().toString();
            String user = usuario.getText().toString();
            String pass = password.getText().toString();
            String message = db.addRegister(ap, nom, user, pass);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            apellido.setText("");
            nombre.setText("");
            usuario.setText("");
            password.setText("");
        }
    }

    public void update(View view) {
        if (apellido.getText().toString().equals("") || nombre.getText().toString().equals("") || usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Inserta la información solicitada.", Toast.LENGTH_SHORT).show();
        } else {
            DB db = new DB(getApplicationContext(), null, null, 1);
            String ap = apellido.getText().toString();
            String nom = nombre.getText().toString();
            String user = usuario.getText().toString();
            String pass = password.getText().toString();
            String message = db.updateRegister(ap, nom, user, pass);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            apellido.setText("");
            nombre.setText("");
            usuario.setText("");
            password.setText("");
        }
    }

    public void delete(View view) {
        if (apellido.getText().toString().equals("") || nombre.getText().toString().equals("") || usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Inserta la información solicitada.", Toast.LENGTH_SHORT).show();
        } else {
            DB db = new DB(getApplicationContext(), null, null, 1);
            String ap = apellido.getText().toString();
            String nom = nombre.getText().toString();
            String user = usuario.getText().toString();
            String pass = password.getText().toString();
            String message = db.deleteRegister(ap, nom, user, pass);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            apellido.setText("");
            nombre.setText("");
            usuario.setText("");
            password.setText("");
        }
    }
}
