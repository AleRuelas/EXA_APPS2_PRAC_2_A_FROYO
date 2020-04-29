package com.example.exa_apps2_prac_2_a_froyo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NotesActivity extends AppCompatActivity {
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        id=getIntent().getIntExtra("id", 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                SharedPreferences preferences = this.getSharedPreferences("nombreArchivo", Context.MODE_PRIVATE);
                nombreArchivo = preferences.getString("nombre", "error");
                preferences.edit().clear().commit();

                //Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
                if(!nombreArchivo.equals("")){
                    try {

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

    public void openListn(View view){
        inListnotes.putExtra("id_nombre", id);
        startActivityForResult(inListnotes, 1000);
    }


    public void createNote(View view) {
        String texto = edit.getText().toString();
        int numberRandom=(int)(Math.random()*100);
        String nameNote = "archivo"+numberRandom+".txt";
        DB db = new DB(getApplicationContext(), null, null, 1);
        if (!texto.equals("")) {
            try {
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
