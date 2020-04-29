package com.example.exa_apps2_prac_2_a_froyo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Usuarios", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Usuarios(" +
                "id integer PRIMARY KEY autoincrement, " +
                "apellido text," +
                "nombre text, " +
                "usuario text, " +
                "password text);");
        db.execSQL("create table if not exists Archivos(" +
                "id integer PRIMARY KEY autoincrement, " +
                "nombre text," +
                "id_usuario integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public String addRegister(String ap, String nom, String user, String pass) {
        String message = "";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("apellido", ap);
        row.put("nombre", nom);
        row.put("usuario", user);
        row.put("password", pass);
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();
            db.insertOrThrow("Usuarios", null, row);
            message = "¡El registro fue añadido con éxito!";
        } catch (SQLException e) {
            message = "No se ha podido ingresar el registro.";
        } finally {
            db.endTransaction();
        }
        db.close();
        return message;
    }

    public String updateRegister(String ap, String nom, String user, String pass) {
        String message = "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();
            ContentValues contentValues = new ContentValues();
            contentValues.put("apellido", ap);
            contentValues.put("nombre", nom);
            contentValues.put("password", pass);
            db.update("Usuarios", contentValues, "usuario='" + user + "'", null);
            message = "¡El registro fue modificado con éxito!";
        } catch (SQLException e) {
            message = "No se ha podido actualizar el registro.";
        } finally {
            db.endTransaction();
        }
        db.close();
        return message;
    }

    public String deleteRegister(String ap, String nom, String user, String pass) {
        String message = "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();
            db.delete("Usuarios", "apellido='" + ap + "' and nombre='" + nom + "'and usuario='" + user + "' and password=" + pass, null);
            message = "¡El registro fue eliminado con éxito!";
        } catch (SQLException e) {
            message = "No se ha podido eliminar el registro.";
        } finally {
            db.endTransaction();
        }
        db.close();
        return message;
    }
    public int findUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();

            Cursor c=db.rawQuery("SELECT * FROM Usuarios WHERE usuario='"+user+"' AND password='"+password+"'", null);
            if (c!=null) {
                c.moveToFirst();
                do{
                    return c.getInt(0);
                }while(c.moveToNext());
            }
        } catch (SQLException e) {
            return 0;
        } finally {
            db.endTransaction();
        }
        return 0;
    }

    public ArrayList<UserClass> selectList() {
        UserClass user;
        ArrayList<UserClass> list = new ArrayList<UserClass>();
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL = "select * from Usuarios";
        Cursor cursor = db.rawQuery(SQL, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new UserClass(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public void addNote(int id, String name){

        String message = "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table if not exists Archivos(" +
                "id integer PRIMARY KEY autoincrement, " +
                "nombre text," +
                "id_usuario integer);");
        ContentValues row = new ContentValues();
        row.put("nombre", name);
        row.put("id_usuario", id);
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();
            db.insertOrThrow("Archivos", null, row);
            Log.wtf("xd", "¡El registro fue añadido con éxito!");
        } catch (SQLException e) {
            Log.wtf("xd", "¡No se pudo agregar con exito!");
        } finally {
            db.endTransaction();
        }
        db.close();
    }
    public String getNotes(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String notes="";
        try {
            db.setTransactionSuccessful();

            Cursor c=db.rawQuery("SELECT * FROM Archivos WHERE id_usuario="+id+"", null);
            if (c!=null) {
                c.moveToFirst();
                do{
                    notes+=c.getString(c.getColumnIndex("nombre"));
                }while(c.moveToNext());
                return notes;
            }
        } catch (SQLException e) {
            return "";
        } finally {
            db.endTransaction();
        }
        return notes;
    }
}

