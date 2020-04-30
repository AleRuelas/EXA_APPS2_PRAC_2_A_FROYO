package com.example.exa_apps2_prac_2_a_froyo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//ACTIVIDAD DEL FORMULARIO PARA AGREGAR ARCHIVOS
public class NotesActivity extends AppCompatActivity {
    //DECLARACION DE VARIABLES
    EditText edit;
    Intent inListnotes;
    int id=0;
    String nombreArchivo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        edit = findViewById(R.id.edNotes);
        inListnotes = new Intent(this, UsersNotesList.class);
        //MANEJO DE PERMISOS PARA ANDROID 10 EN ADELANTE
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        //RECUPERACION DE VALOR DEL ID DEL USUARIO LOGEADO
        id=getIntent().getIntExtra("id", 0);
    }
    //FUNCION SOBRESCRITA PARA ESPERAR RESULTADO DE LA LISTA
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                //UTILIZACION DE SHAREDREFERENCE PARA RECUPERAR DATOS
                SharedPreferences preferences = this.getSharedPreferences("nombreArchivo", Context.MODE_PRIVATE);
                nombreArchivo = preferences.getString("nombre", "error");
                preferences.edit().clear().commit();

                if(!nombreArchivo.equals("")){
                    try {
                        //CODIGO PARA LEER EL CONTENIDO DE UN ARCHIVO TXT SELECCIONADO Y COLOCARLO EN EL EDITTEXT
                        File file = new File(getExternalFilesDir(null).getPath(), nombreArchivo);
                        FileInputStream in = new FileInputStream(file);
                        InputStreamReader ist = new InputStreamReader(in);
                        BufferedReader buffer = new BufferedReader(ist);
                        String sCade;
                        while((sCade=buffer.readLine())!=null) {
                            edit.append(sCade);
                            edit.append("\n");

                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(this, "Información solicitada no disponible.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    //FUNCION PARA ABRIR LA ACTIVIDAD QUE TIENE LA LISTA DE LOS USUARIOS
    public void openListn(View view){
        edit.setText("");
        inListnotes.putExtra("id_nombre", id);
        startActivityForResult(inListnotes, 1000);
    }

    //FUNCION CREAR ARCHIVO NUEVO
    public void createNote(View view) {
        String texto = edit.getText().toString();
        //DAR NOMBRES RANDOM A LOS ARCHIVOS
        int numberRandom=(int)(Math.random()*100);
        String nameNote = "archivo"+numberRandom+".txt";
        DB db = new DB(getApplicationContext(), null, null, 1);
        if (!texto.equals("")) {
            try {
                //CODIGO PARA ESCRIBIR EN UN ARCHIVO
                File file = new File(getExternalFilesDir(null).getPath(), nameNote);
                FileOutputStream on = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(on);
                BufferedWriter buffer = new BufferedWriter(osw);

                buffer.write(texto);
                buffer.close();
                Toast.makeText(this, nameNote, Toast.LENGTH_SHORT).show();
                db.addNote(id, nameNote);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Necesitas escribir algo en el archivo", Toast.LENGTH_SHORT).show();
        }
        edit.setText("");
    }
    //FUNCION PARA MODIFICAR EL CONTENIDO DE UN ARCHIVO
    public void saveNote(View view) {
        String texto = edit.getText().toString();
        DB db = new DB(getApplicationContext(), null, null, 1);
        if (!texto.equals("")) {
            if (!nombreArchivo.equals("")) {
                try {
                    File file = new File(getExternalFilesDir(null).getPath(), nombreArchivo);
                    FileOutputStream on = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(on);
                    BufferedWriter buffer = new BufferedWriter(osw);

                    buffer.write(texto);
                    buffer.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(this, "No ha seleccionado ningun archivo", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Necesitas escribir algo en el archivo", Toast.LENGTH_SHORT).show();
        }
        edit.setText("");
    }
}
