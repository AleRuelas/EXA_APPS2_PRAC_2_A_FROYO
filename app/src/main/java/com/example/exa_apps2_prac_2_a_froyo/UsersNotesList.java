package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UsersNotesList extends AppCompatActivity implements ListView.OnItemClickListener{
    ArrayList<NotesClass> list;
    ListView notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_notes_list);
        notesList = findViewById(R.id.notesList);
        DB db = new DB(getApplicationContext(), null, null, 1);
        if (getIntent().getIntExtra("id_nombre", 0)!=0) {
            list = db.getNotes(getIntent().getIntExtra("id_nombre", 0));
            if (list!=null) {
                notesList.setAdapter(new NotesAdapter(this, R.layout.layout_note, list));
                notesList.setOnItemClickListener(this);
            }else {
                Toast.makeText(this, "Este usuario no tiene archivos para mostrar aun", Toast.LENGTH_SHORT).show();
                finish();
            }
        }else{
            Toast.makeText(this, "no hay archivos para este usuario", Toast.LENGTH_SHORT).show();
            finish();

        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent inDatos = new Intent();
        SharedPreferences preferences = this.getSharedPreferences("nombreArchivo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", list.get(position).getNote());
        editor.commit();
        setResult(Activity.RESULT_OK, inDatos);


        //Toast.makeText(this, list.get(position).getNote(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
