package com.example.energymapp.model;

public class Serie {

    private String idSerie;
    private String idRutina;
    private String nombreEjercicio;
    private String numReps;
    private String peso;

    public Serie(String nombreEjercicio){
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }
}
