package com.hlc.vo;

public class Asignatura {
    private int codigo;
    private String nombre;
    private int creditos;

    public Asignatura(int codigo, String nombre, int creditos){
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Asignatura [" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ']';
    }
}
