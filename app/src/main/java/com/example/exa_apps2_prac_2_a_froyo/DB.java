package com.example.exa_apps2_prac_2_a_froyo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

//CLASE DE LA BASE DE DATOS
public class DB extends SQLiteOpenHelper {
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Usuarios", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SE CREAN LAS TABLAS USUARIOS Y ARCHIVOS
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

    //MÉTODO PARA AÑADIR UN REGISTRO
    public String addRegister(boolean v, String ap, String nom, String user, String pass) {
        String message = "";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("apellido", ap);
        row.put("nombre", nom);
        row.put("usuario", user);
        row.put("password", pass);
        db.beginTransaction();
        try {
            //VALIDACIÓN DE EXISTENCIA DE USUARIO
            if (v) {
                message = "El usuario " + user + " ya existe.";
            } else {
                db.setTransactionSuccessful();
                db.insert("Usuarios", null, row);
                message = "¡El registro fue añadido con éxito!";
            }
        } catch (SQLException e) {
            message = "No se ha podido ingresar el registro.";
        } finally {
            db.endTransaction();
        }
        db.close();
        return message;
    }

    //MÉTODO PARA ACTUALIZAR UN REGISTRO
    public String updateRegister(int id, String ap, String nom, String user, String pass) {
        String message = "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();
            ContentValues contentValues = new ContentValues();
            contentValues.put("apellido", ap);
            contentValues.put("nombre", nom);
            contentValues.put("password", pass);
            db.update("Usuarios", contentValues, "id=" + id, null);
            message = "¡El registro fue modificado con éxito!";

        } catch (SQLException e) {
            message = "No se ha podido actualizar el registro.";
        } finally {
            db.endTransaction();
        }
        db.close();
        return message;
    }

    //MÉTODO PARA ELIMINAR UN REGISTRO
    public String deleteRegister(int id) {
        String message = "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();
            db.delete("Usuarios", "id=" + id, null);
            message = "¡El registro fue eliminado con éxito!";
        } catch (SQLException e) {
            message = "No se ha podido eliminar el registro.";
        } finally {
            db.endTransaction();
        }
        db.close();
        return message;
    }

    //MÉTODO PARA VALIDAR SI EL USUARIO YA EXISTE O NO
    public boolean searchUser(String user) {
        boolean v = false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();
            Cursor c = db.rawQuery("SELECT * FROM Usuarios WHERE usuario='" + user + "'", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    v = true;
                    return v;
                } else {
                    v = false;
                    return v;
                }
            }
        } catch (SQLException e) {
            return v;
        } finally {
            db.endTransaction();
        }
        return v;
    }

    //MÉTODO PARA ENCONTRAR EL ID DEL USUARIO QUE INICIA SESIÓN
    public int findUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.setTransactionSuccessful();
            Cursor c = db.rawQuery("SELECT * FROM Usuarios WHERE usuario='" + user + "' AND password='" + password + "'", null);
            if (c != null) {
                if (c.moveToFirst()) {

                    do {
                        return c.getInt(0);
                    } while (c.moveToNext());
                } else {
                    Log.wtf("error", "No hay registro.");
                    return 0;
                }
            }
        } catch (SQLException e) {
            return 0;
        } finally {
            db.endTransaction();
        }
        return 0;
    }

    //LLENA LA LISTA DE USUARIOS
    public ArrayList<UserClass> selectList() {
        UserClass user;
        ArrayList<UserClass> list = new ArrayList<UserClass>();
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL = "select * from Usuarios";
        Cursor cursor = db.rawQuery(SQL, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new UserClass(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //MÉTODO PARA AÑADIR UN ARCHIVO
    public void addNote(int id, String name) {
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

    //LLENA LA LISTA DE ARCHIVOS SEGÚN EL USUARIO CORRESPONDIENTE
    public ArrayList<NotesClass> getNotes(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ArrayList<NotesClass> notes = new ArrayList<NotesClass>();
        try {
            db.setTransactionSuccessful();
            Cursor c = db.rawQuery("SELECT * FROM Archivos WHERE id_usuario=" + id + "", null);
            if (c != null) {
                if (c.moveToFirst()) {

                    do {
                        notes.add(new NotesClass(c.getString(c.getColumnIndex("nombre"))));
                    } while (c.moveToNext());
                    return notes;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            return null;
        } finally {
            db.endTransaction();
        }
        return notes;
    }
}

