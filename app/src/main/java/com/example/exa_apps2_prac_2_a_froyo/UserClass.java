package com.example.exa_apps2_prac_2_a_froyo;

public class UserClass {
    private String apellido, nombre, usuario, password;

    public UserClass(String apellido, String nombre, String usuario, String password) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
    }

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
}
