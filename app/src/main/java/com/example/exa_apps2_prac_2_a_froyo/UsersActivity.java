package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsersActivity extends AppCompatActivity {

    EditText apellido, nombre, usuario, password;
    Button btnBorrar, btnGuardar;
    Intent inList;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        //SE VINCULA CADA ELEMENTO CON EL CORRESPONDIENTE EN EL LAYOUT ACTIVITY_USERS
        apellido = findViewById(R.id.edApellido);
        nombre = findViewById(R.id.edNombre);
        usuario = findViewById(R.id.edUsuario);
        password = findViewById(R.id.edPass);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnGuardar = findViewById(R.id.btnGuardar);
        //SE DESHABILITAN LOS BOTONES GUARDAR Y BORRAR
        btnBorrar.setEnabled(false);
        btnGuardar.setEnabled(false);
        inList = new Intent(UsersActivity.this, UsersList.class); //INTENTO PARA PASAR A LA CLASE USERSLIST CUANDO SEA LLAMADO
    }

    //SE EJECUTA DESPUES DE VOLVER DE LA ACTIVIDAD USERSLIST LANZADA POR INTENTO INLIST
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //SE VERIFICA EL RESQUESTCODE
        if (requestCode == 1000) {
            //SE VERIFICA RESULTCODE MANDADO DESDE USERSLIST
            if (resultCode == Activity.RESULT_OK) {
                SharedPreferences preferences = this.getSharedPreferences("usuarios", Context.MODE_PRIVATE);
                //SE OBTIENE EL ID DEL REGISTRO CORRESPONDIENTE
                id = preferences.getInt("id",0);
                //SE LLENAN LOS EDITTEXT CON LA INFORMACIÓN RECIBIDA DE LA CLASE USERSLIST
                apellido.setText(preferences.getString("apellido", "error"));
                nombre.setText(preferences.getString("nombre", "error"));
                usuario.setText(preferences.getString("usuario", "error"));
                password.setText(preferences.getString("password", "error"));
                preferences.edit().clear().commit();

                //SE HABILITAN/DESHABILITAN LOS COMPONENTES
                btnBorrar.setEnabled(true);
                btnGuardar.setEnabled(true);
                usuario.setEnabled(false);
            }
        }
    }

    //MÉTODO QUE INICIA LA ACTIVIDAD CORRESPONDIENTE AL INTENTO
    public void open(View view) {
        startActivityForResult(inList, 1000);//MANDA EL RESQUESTCODE AL INICIAR LA ACTIVIDAD
    }

    public void add(View view) {
        if (apellido.getText().toString().equals("") || nombre.getText().toString().equals("") || usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Inserta la información solicitada.", Toast.LENGTH_SHORT).show();
        } else {
            DB db = new DB(getApplicationContext(), null, null, 1);
            //SE OBTIENE LA INFORMACIÓN DE CADA EDITTEXT
            String ap = apellido.getText().toString();
            String nom = nombre.getText().toString();
            String user = usuario.getText().toString();
            String pass = password.getText().toString();
            //SE VERIFICA SI EL USUARIO YA EXISTE
            boolean v = db.searchUser(user);
            //SE MANDA LLAMAR AL MÉTODO PARA AÑADIR EL REGISTRO
            String message = db.addRegister(v,ap, nom, user, pass);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        //SE VACIAN LOS CAMPOS
        apellido.setText("");
        nombre.setText("");
        usuario.setText("");
        password.setText("");
        //SE HABILITAN/DESHABILITAN LOS COMPONENTES
        btnBorrar.setEnabled(false);
        btnGuardar.setEnabled(false);
        usuario.setEnabled(true);
    }

    public void update(View view) {
        if (apellido.getText().toString().equals("") || nombre.getText().toString().equals("") || usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Inserta la información solicitada.", Toast.LENGTH_SHORT).show();
        } else {
            DB db = new DB(getApplicationContext(), null, null, 1);
            //SE OBTIENE LA INFORMACIÓN DE CADA EDITTEXT
            String ap = apellido.getText().toString();
            String nom = nombre.getText().toString();
            String user = usuario.getText().toString();
            String pass = password.getText().toString();
            //SE MANDA LLAMAR AL MÉTODO PARA MODIFICAR EL REGISTRO
            String message = db.updateRegister(id,ap, nom, user, pass);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
        //SE VACIAN LOS CAMPOS
        apellido.setText("");
        nombre.setText("");
        usuario.setText("");
        password.setText("");
        //SE HABILITAN/DESHABILITAN LOS COMPONENTES
        btnBorrar.setEnabled(false);
        btnGuardar.setEnabled(false);
        usuario.setEnabled(true);
    }

    public void delete(View view) {
        if (apellido.getText().toString().equals("") || nombre.getText().toString().equals("") || usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Inserta la información solicitada.", Toast.LENGTH_SHORT).show();
        } else {
            DB db = new DB(getApplicationContext(), null, null, 1);
            //SE MANDA LLAMAR AL MÉTODO PARA ELIMINAR EL REGISTRO
            String message = db.deleteRegister(id);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
        //SE VACIAN LOS CAMPOS
        apellido.setText("");
        nombre.setText("");
        usuario.setText("");
        password.setText("");
        //SE HABILITAN/DESHABILITAN LOS COMPONENTES
        btnBorrar.setEnabled(false);
        btnGuardar.setEnabled(false);
        usuario.setEnabled(true);
    }
}
