package com.example.energymapp.model;

public class Ejercicio {

    private String idSerie;
    private String idRutina;
    private String nombreEjercicio;
    private String numReps;
    private String peso;

    public Ejercicio(String nombreEjercicio){
        this.nombreEjercicio = nombreEjercicio;
    }

    public Ejercicio(String nombreEjercicio, String numReps, String peso){
        this.nombreEjercicio = nombreEjercicio;
        this.numReps = numReps;
        this.peso = peso;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
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
