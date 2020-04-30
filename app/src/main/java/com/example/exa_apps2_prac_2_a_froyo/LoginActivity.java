package com.example.exa_apps2_prac_2_a_froyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//ACTIVIDAD DE INICIO DE SESION DE UN USUARIO PARA CREAR Y MODIFICAR SUS ARCHIVOS
public class LoginActivity extends AppCompatActivity {
    Intent inNotes;
    EditText user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.edUser);
        pass = findViewById(R.id.edPassword);
        inNotes = new Intent(this, NotesActivity.class);
    }
    //FUNCION PARA ACCEDER A LA INFORMACIÓN DE LOS ARCHIVOS DE UN USUARIO
    public void getin(View view){
        DB data = new DB(getApplicationContext(), null, null, 1);
        if (!user.getText().toString().equals("")&& !pass.getText().toString().equals("")) {
            int id=data.findUser(user.getText().toString(), pass.getText().toString());
            if ( id != 0) {
                inNotes.putExtra("id", id);
                startActivity(inNotes);
            }else {
                user.setText("");
                pass.setText("");
                Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
