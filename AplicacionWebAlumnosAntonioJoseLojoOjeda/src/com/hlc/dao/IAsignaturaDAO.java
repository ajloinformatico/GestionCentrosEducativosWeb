package com.hlc.dao;

import com.hlc.vo.Asignatura;

import java.util.ArrayList;

/**
 * Interfaz para las asignaturas
 * @author antoniojoselojoojeda
 */
public interface IAsignaturaDAO {
    //Contrato
    public String listarAsignaturas();
    public ArrayList<Asignatura> buscaAsignaturas(); //devuelve una lista de asignaturas
    public String crearAsignatura(int codigo, String nombtre, int creditos); //Crea una asignatura
    public String eliminarAsignatura(int codigo); //Elimina una asignatura
    public String modificarAsignatura(int codigo, String nombre, int creditos); //Modifica una asignatura
    public String alumnosDeUnaAsignatura(int codigo); //muestra los alumnos de una asignatura
    public String matricular(String dni, int codigo); //matricula un alumno en una asignatura


}