package com.example.energymapp.model;

public class Rutina {

    private String idUsuario;
    private String idRutina;
    private String nombreRutina;
    private String totalReps;
    private String totalPeso;
    private String tiempo;

    public Rutina(String idUsuario, String nombreRutina){

        this.nombreRutina = nombreRutina;
        this.idUsuario = idUsuario;
    }
    public Rutina(){

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(String idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }

    public String getTotalReps() {
        return totalReps;
    }

    public void setTotalReps(String totalReps) {
        this.totalReps = totalReps;
    }

    public String getTotalPeso() {
        return totalPeso;
    }

    public void setTotalPeso(String totalPeso) {
        this.totalPeso = totalPeso;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
