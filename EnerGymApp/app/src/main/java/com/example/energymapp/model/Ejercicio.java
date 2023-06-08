package com.example.energymapp.model;

public class Ejercicio {

    private String idRutina;
    private String idUsuario;
    private String nombreEjercicio;

    private int numRepsTotal;
    private int pesoTotal;

    private String reps1;
    private String reps2;
    private String reps3;
    private String reps4;

    private String peso1;
    private String peso2;
    private String peso3;
    private String peso4;

    public Ejercicio(String nombreEjercicio, int numRepsTotal, int pesoTotal, String peso1, String peso2, String peso3, String peso4, String reps1, String reps2, String reps3, String reps4){
        this.nombreEjercicio = nombreEjercicio;
        this.numRepsTotal = numRepsTotal;
        this.pesoTotal = pesoTotal;
        this.peso1 = peso1;
        this.peso2 = peso2;
        this.peso3 = peso3;
        this.peso4 = peso4;
        this.reps1 = reps1;
        this.reps2 = reps2;
        this.reps3 = reps3;
        this.reps4 = reps4;
    }

    public Ejercicio(String nombreEjercicio){
        this.nombreEjercicio = nombreEjercicio;
    }

    public Ejercicio(){

    }

    /*public Ejercicio(String nombreEjercicio, String reps1, String reps2, String reps3, String reps4){
        this.nombreEjercicio = nombreEjercicio;
        this.reps1 = reps1;
        this.reps2 = reps2;
        this.reps3 = reps3;
        this.reps4 = reps4;
    }*/

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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNumRepsTotal() {
        return numRepsTotal;
    }

    public void setNumRepsTotal(int numRepsTotal) {
        this.numRepsTotal = numRepsTotal;
    }

    public int getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(int pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public String getReps1() {
        return reps1;
    }

    public void setReps1(String reps1) {
        this.reps1 = reps1;
    }

    public String getReps2() {
        return reps2;
    }

    public void setReps2(String reps2) {
        this.reps2 = reps2;
    }

    public String getReps3() {
        return reps3;
    }

    public void setReps3(String reps3) {
        this.reps3 = reps3;
    }

    public String getReps4() {
        return reps4;
    }

    public void setReps4(String reps4) {
        this.reps4 = reps4;
    }

    public String getPeso1() {
        return peso1;
    }

    public void setPeso1(String peso1) {
        this.peso1 = peso1;
    }

    public String getPeso2() {
        return peso2;
    }

    public void setPeso2(String peso2) {
        this.peso2 = peso2;
    }

    public String getPeso3() {
        return peso3;
    }

    public void setPeso3(String peso3) {
        this.peso3 = peso3;
    }

    public String getPeso4() {
        return peso4;
    }

    public void setPeso4(String peso4) {
        this.peso4 = peso4;
    }
}
