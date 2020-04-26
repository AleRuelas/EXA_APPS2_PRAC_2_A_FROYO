package com.example.exa_apps2_prac_2_a_froyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsersActivity extends AppCompatActivity {

    EditText apellido, nombre, usuario, password;
    SQLiteDatabase db;
    Intent inList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        apellido = findViewById(R.id.edApellido);
        nombre = findViewById(R.id.edNombre);
        usuario = findViewById(R.id.edUsuario);
        password = findViewById(R.id.edPass);
        db = this.openOrCreateDatabase("myDB", MODE_PRIVATE, null);
        inList = new Intent(this, UsersList.class);
    }

    public void open(View view) {
        try {
            db.beginTransaction();
            db.execSQL("create table if not exists Usuarios(" +
                    "id integer PRIMARY KEY autoincrement, " +
                    "apellido text," +
                    "nombre text, " +
                    "usuario text, " +
                    "password);");
            String SQL = "select * from Usuarios";
            Cursor cursor = db.rawQuery(SQL, null);
            String list = "";
            while (cursor.moveToNext()) {
                list += cursor.getInt(0) + "    " + cursor.getString(1) + "    " + cursor.getString(2) + "    " + cursor.getString(3) +"    " + cursor.getString(4) + "\n";
            }
            Intent intent = new Intent(this, UsersList.class);
            intent.putExtra("usersList", list);
            startActivity(intent);
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void add(View view) {
        if (apellido.getText().toString().equals("") || nombre.getText().toString().equals("") || usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Inserta la información solicitada.", Toast.LENGTH_SHORT).show();
        } else {
            String ap = apellido.getText().toString();
            String nom = nombre.getText().toString();
            String user = usuario.getText().toString();
            String pass = password.getText().toString();
            ContentValues row = new ContentValues();
            row.put("apellido", ap);
            row.put("nombre", nom);
            row.put("usuario", user);
            row.put("password", pass);
            db.beginTransaction();
            try {
                db.setTransactionSuccessful();
                db.execSQL("create table if not exists Usuarios(" +
                        "id integer PRIMARY KEY autoincrement, " +
                        "apellido text," +
                        "nombre text, " +
                        "usuario text, " +
                        "password);");
                db.insert("Usuarios", null, row);
                apellido.setText("");
                nombre.setText("");
                usuario.setText("");
                password.setText("");
                Toast.makeText(this, "¡El registro fue añadido con éxito!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                db.endTransaction();
            }
        }
    }

    public void update(View view) {
        if (apellido.getText().toString().equals("") || nombre.getText().toString().equals("") || usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Inserta la información solicitada.", Toast.LENGTH_SHORT).show();
        } else {
            String ap = apellido.getText().toString();
            String nom = nombre.getText().toString();
            String user = usuario.getText().toString();
            String pass = password.getText().toString();
            db.beginTransaction();
            try {
                db.setTransactionSuccessful();
                ContentValues contentValues = new ContentValues();
                contentValues.put("apellido", ap);
                contentValues.put("nombre", nom);
                contentValues.put("password", pass);
                String[] args = {user};
                db.update("Usuarios", contentValues, "usuario=?", args);
                apellido.setText("");
                nombre.setText("");
                usuario.setText("");
                password.setText("");
                Toast.makeText(this, "¡El registro fue modificado con éxito!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                db.endTransaction();
            }
        }
    }

    public void delete(View view) {
        if (apellido.getText().toString().equals("") || nombre.getText().toString().equals("") || usuario.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Inserta la información solicitada.", Toast.LENGTH_SHORT).show();
        } else {
            String ap = apellido.getText().toString();
            String nom = nombre.getText().toString();
            String user = usuario.getText().toString();
            String pass = password.getText().toString();
            db.beginTransaction();
            try {
                db.setTransactionSuccessful();
                db.delete("Usuarios", "apellido='" + ap + "' and nombre='" + nom + "'and usuario='" + user + "'and password=" + pass, null);
                apellido.setText("");
                nombre.setText("");
                usuario.setText("");
                password.setText("");
                Toast.makeText(this, "¡El registro fue eliminado con éxito!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                db.endTransaction();
            }
        }
    }
}
