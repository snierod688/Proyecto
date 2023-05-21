package com.example.energymapp.model;

public class MedidasUsuario {

    private String altura;
    private String peso;
    private String biceps;
    private String cintura;
    private String pecho;
    private String muslo;

    public MedidasUsuario(String altura, String peso, String biceps, String cintura, String pecho, String muslo) {
        this.altura = altura;
        this.peso = peso;
        this.biceps = biceps;
        this.cintura = cintura;
        this.pecho = pecho;
        this.muslo = muslo;
    }

    public MedidasUsuario(){}

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getBiceps() {
        return biceps;
    }

    public void setBiceps(String biceps) {
        this.biceps = biceps;
    }

    public String getCintura() {
        return cintura;
    }

    public void setCintura(String cintura) {
        this.cintura = cintura;
    }

    public String getPecho() {
        return pecho;
    }

    public void setPecho(String pecho) {
        this.pecho = pecho;
    }

    public String getMuslo() {
        return muslo;
    }

    public void setMuslo(String muslo) {
        this.muslo = muslo;
    }
}
