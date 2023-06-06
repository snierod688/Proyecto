package com.example.energymapp.model;

public class Ejercicio {

    private String idRutina;
    private String idUsuario;
    private String nombreEjercicio;
    private String numReps;
    private String peso;

    public Ejercicio(String idUsuario, String idRutina, String nombreEjercicio){
        this.idUsuario = idUsuario;
        this.idRutina = idRutina;
        this.nombreEjercicio = nombreEjercicio;
    }

    public Ejercicio(String nombreEjercicio, String idUsuario, String idRutina, String numReps, String peso){
        this.nombreEjercicio = nombreEjercicio;
        this.numReps = numReps;
        this.peso = peso;
        this.idRutina = idRutina;
        this.idUsuario = idUsuario;
    }

    public Ejercicio(String nombreEjercicio){
        this.nombreEjercicio = nombreEjercicio;
    }

    public Ejercicio(){

    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(String idRutina) {
        this.idRutina = idRutina;
    }

    public String getNumReps() {
        return numReps;
    }

    public void setNumReps(String numReps) {
        this.numReps = numReps;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}
