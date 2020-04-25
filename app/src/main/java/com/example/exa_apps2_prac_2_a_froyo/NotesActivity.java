package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NotesActivity extends AppCompatActivity {

    Intent inListnotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        inListnotes = new Intent(this, UsersNotesList.class);

    }

    public void openListn(View view){
        startActivity(inListnotes);

    }


}
