package com.example.energymapp.model;

public class Usuario {

    private String id;
    private String username;
    private String nombre;
    private String email;
    private String password;

    public Usuario(String id, String username, String nombre, String email, String password) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Usuario(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
