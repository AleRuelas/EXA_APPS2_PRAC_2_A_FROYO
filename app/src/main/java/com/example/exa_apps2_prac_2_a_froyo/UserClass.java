package com.example.exa_apps2_prac_2_a_froyo;

//CLASE PARA USUARIOS
import java.io.Serializable;

public class UserClass implements Serializable {
    private String apellido, nombre, usuario, password;
    private int id;

    public UserClass(int id, String apellido, String nombre, String usuario, String password) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /* @Override
    public String toString(){
        return nombre;
    }*/
}
