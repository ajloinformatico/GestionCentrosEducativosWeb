package com.hlc.vo;

public class Alumno {
    private String dni;
    private String nombre;
    private int edad;

    public Alumno(String dni, String nombre, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
    }

    //Getters && Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    //toString
    @Override
    public String toString() {
        return "Alumno [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad + "]";
    }
}