package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UsersNotesList extends AppCompatActivity implements ListView.OnItemClickListener{

    ListView notesList;
    NotesClass[] notes = {
            new NotesClass("hola1"),
            new NotesClass("hola2"),
            new NotesClass("hola3"),
            new NotesClass("hola4"),
            new NotesClass("hola5"),
            new NotesClass("hola6"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_notes_list);
        notesList = findViewById(R.id.notesList);
        notesList.setAdapter(new NotesAdapter(this, R.layout.layout_note,notes));
        notesList.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent inDatos = new Intent();
        inDatos.putExtra("archivo",notes[position].getNote());

        setResult(Activity.RESULT_OK, inDatos);


        Toast.makeText(this, notes[position].getNote(), Toast.LENGTH_SHORT).show();
    }
}
