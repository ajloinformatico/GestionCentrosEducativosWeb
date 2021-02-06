package com.hlc.dao;

/**
 * @author antoniojoselojoojeda
 */
public interface IAlumnoDAO {

    //Contrato
    public void cargarAlumnos(); // esta No es necesario especificar si es público o provado pero por si las moscas lo dejo ahí y ya
    public String insertarAlumno(String dni, String nombre, int edad);
    public String listarAlumnos(); // esta
    public String eliminarAlumno(String dni);
    public String modificarAlumno(String dni, String nombre, int edad);
    public boolean compruebaDni(String dni);
    public boolean compruebaEdad(String edad);
    public boolean buscaAlumno(String dni);
    public String asignaturasDeUnAlumno(String dni); //Muestra tidas las asignaturas de un alumno
}
