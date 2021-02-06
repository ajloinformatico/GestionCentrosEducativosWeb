package com.hlc.dao;

import com.hlc.connection.DBConnection;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Conjunto de métodos comunes que no representan de nminguna entidad en la aplicación
 * @author antoniojoselojoojeda
 */
public class General {
    /**
     * Comprueba si es un número (todas las entradas son String par evitar problemas con el buffer)
     * @param n {String}: Number to check
     * @return true {boolean}: true si es un número
     */
    public static boolean checkNumber(String n){
        try{
            Integer.parseInt(n);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public static void comandos(){
        System.out.println("COMANDOS\n0| Comandos\n1| Salir\n2| Listar alumnos\n3| Insertar alumno\n4| " +
                "Eliminar alumno\n5| Modificar alumno\n6| Listar asignaturas\n7| Crear Asignatura\n8| " +
                "Eliminar asignatura\n9| Alumnos de una asignatura\n10| Asignaturas de un alumno\n11| " +
                "Matricular");
    }
    /**
     * Método añadido por mi para hacer insercción por defecto
     * Solo se inserta si no hay usuarios en la base de datos
     */
    public static void initialInsert(){
        DBConnection con = new DBConnection();
        //Insercción de alumnos
        String sql = "INSERT INTO alumnos(dni, nombre, edad) VALUES" +
                "('34543434b', 'Pepe', 23),"+
                "('34514314a', 'Marisa', 19),"+
                "('44500434b', 'Fernando', 18)";
        //Insercción de las asignaturas
        String sql2 = "INSERT INTO asignaturas(cod_asignatura, nombre_asignatura, creditos) VALUES" +
                "(01, 'Matematicas',60),"+
                "(02, 'Programacion 2', 120),"+
                "(03, 'Latín', 70)";
        //Insercción de las matriculas (RELACIÓN ENTRE ALUMNO Y MATRICULA)
        String sql3 = "INSERT INTO matricula(dni_fk, cod_asignatura_fk) VALUES" +
                "('34543434b', 03),"+ //Pepe matricula en latín
                "('34514314a', 01),"+ //Marisa matricula en Programación y matemáticas
                "('34514314a', 02),"+
                "('44500434b', 01),"+//Fernando matricula en Programación y matemáticas
                "('44500434b', 02)";
        try{
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            st.executeQuery(sql2);
            st.executeQuery(sql3);
            st.close();
            System.out.println("Dado que no había datos en el sistema.\nSe han insertado 3 usuarios por defecto");

        }catch(Exception e){
            System.out.println("Error:\nAlgo ha ido mal en la carga de los datos");
        }finally {
            con.desconectar();
        }

    }
}
