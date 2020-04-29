package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //test francisco commit
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
