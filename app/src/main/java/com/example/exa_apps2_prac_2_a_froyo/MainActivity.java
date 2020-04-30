package com.example.exa_apps2_prac_2_a_froyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/*ACTIVIDAD PRINCIPAL DE LOS BOTONES PARA VER USUARIOS, ARCHIVOS Y PARA SALIR

    Integrantes:
    Ramirez Luna Francisco Javier
    Ruelas Najera Alejandra
    Vazquez Lerma Cinthia Paola
 */
public class MainActivity extends AppCompatActivity {

    Intent inUsers,inLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SE DECLARAN INTENTOS PARA LANZAR LAS ACTIVIDADES CORRESPONDIENTES
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
        //SE FINALIZA LA ACTIVIDAD PRINCIPAL
        System.exit(0);
    }

}
