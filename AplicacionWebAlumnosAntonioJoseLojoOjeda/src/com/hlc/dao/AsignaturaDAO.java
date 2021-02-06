package com.hlc.dao;

import com.hlc.connection.DBConnection;
import com.hlc.vo.Alumno;
import com.hlc.vo.Asignatura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase con todos los métodos de la interfaz
 * @author antoniojoselojoojeda
 */
public class AsignaturaDAO implements IAsignaturaDAO{



    /**
     * Recorre una lista de asignaturas si el codigo coincide con alguna de ellas devuelve true
     * @param codigo {int}: código de la asignatura
     * @return  {int} 0: No es un numero / 1: No existe / 2: Existe
     */
    public int checkCodigo(String codigo){
        if(General.checkNumber(codigo)){
            for(Asignatura asignatura : this.buscaAsignaturas()){
                if(Integer.parseInt(codigo) == asignatura.getCodigo())
                    return 2;
            }
            return 1;
        }
        return 0;
    }

    /**
     * Recorre una lista de asignaturas y comprueba si existe o no alguna asignatura con ese nombre
     * @param nombre {String}: Nombre que se desea buscar en la base de datos
     * @return true {boolean}: Devuelve true si el nombre existe en la base de datos
     */
    public boolean checkNombre(String nombre){
        for(Asignatura asignatura : this.buscaAsignaturas()){
            if(nombre.toLowerCase().equals(asignatura.getNombre().toLowerCase()))
                return true;
        }
        return false;
    }


    /**
     *
     */
    public boolean checkCreditos(String creditos) {
        if(General.checkNumber(creditos)){
            int cr = Integer.parseInt(creditos);
            if(cr >= 20 && cr <= 200)
                return true;
            System.out.println("Error:\n Los créditos deben ser mayores o iguales a 20 y menores o iguales a 200");
            return false;
        }
        System.out.println("Error:\n El código debe ser un valor entero");
        return false;
    }


    /**
     * Devuelve una lista de asignaturas
     * @return {ArrayList<Asignatura>} asignaturas: Lista de asignaturas
     */
    @Override
    public ArrayList<Asignatura> buscaAsignaturas() {
        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        DBConnection con = new DBConnection();
        String sql = "select * from asignaturas";
        try{
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                asignaturas.add(new Asignatura(rs.getInt("cod_asignatura"),
                        rs.getString("nombre_asignatura"), rs.getInt("creditos")));
            }
            st.close();
            return asignaturas;
        }catch (Exception ex) {
            System.out.println("Error:\nHa habido un error durante la consulta");
            //Error durante la consulta
            return null;
        }finally {
            con.desconectar();
        }
    }

    /**
     * Imprime la query de todas las asignaturas
     */
    @SuppressWarnings("finally")
	@Override
    public String listarAsignaturas() {

        DBConnection con = new DBConnection();
        String sql = "Select * from asignaturas";
        String result = "";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Recorre la query
            while (rs.next()) {
                //va almacenando el resutlado en la variable result
                result += rs.getInt("cod_asignatura") + " : Nombre= " + rs.getString("nombre_asignatura")
                        + " , Crédigo= "+ rs.getInt("creditos") + "\n";
            }//Si el resultado está vacio imprime que no existe asignaturas
            if(result == ""){
                result += "Actualmente no existen asignaturas en el sistema";
            }
            st.close();

        } catch(Exception ex) {
            result += ex.getMessage();

        } finally {
            con.desconectar();
            System.out.println(result);
            return result;
        }
    }
    
    
    
    
    
    /**
     * Elena con dni 12345678s se ha registrado Exitosamente
		AEstos son el resto de alumnos :
		Alumno [dni=34543434b, nombre=Pepe, edad=23]
		Alumno [dni=44500434b, nombre=Fernando, edad=18]
		Alunno [dni=34323434b, nombre=Eleno, edad=21]
		Alumno [dni=12345678s, nombre=Elena, edad=20]

     */
    
    

    /**
     * Agrega una asignatura al sistema
     * @param codigo {int} : codigo de la asignatura a agregar
     * @param nombre {String} : nombre de la asignatura
     * @param creditos {int} : creditos de la asignatura
     */
    @SuppressWarnings("finally")
	@Override
    public String crearAsignatura(int codigo, String nombre, int creditos) {
        DBConnection con = new DBConnection();
        String result = "";
        String sql = "INSERT INTO asignaturas(cod_asignatura, nombre_asignatura, creditos) VALUES" +
                "("+codigo+", '"+nombre+"',"+codigo+")";
        try{
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);
            result += "Asignatura con código :"+codigo+", nombre "+nombre + ", créditos: "+creditos+" agregada correctamente";
            result += "Estas son el resto de asignaturas:\n"+this.listarAsignaturas();
            st.close();
        }catch (Exception ex) {
            result += ex.getMessage();
        }finally {
            con.desconectar();
            return result;
        }
    }

    /**
     * Elimina una asignatura a partir de un código
     */
    @SuppressWarnings("finally")
	@Override
    public String eliminarAsignatura(int codigo) {
        DBConnection con = new DBConnection();
        String result = "";
        String sql = "DELETE FROM asignaturas where cod_asignatura = "+codigo;
        try{
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);
            result += "Asignatura con código :"+codigo+" eliminada con exito";
            st.close();
        }catch (Exception ex){
            result += ex.getMessage();
        }finally {
            con.desconectar();
            return result;
        }
    }

    /**
     * Actualiza una signatura
     */
    @SuppressWarnings("finally")
	@Override
    public String modificarAsignatura(int codigo, String nombre, int creditos) {
    	
    	DBConnection con = new DBConnection();
    	String ret = "";
    	String sql = "update asignaturas set nombre_asignatura = '"+nombre+"', creditos = "+creditos+" where cod_asignatura = "+codigo;
    	try {
    		Statement st = con.getConnection().createStatement();
    		
    		st.executeUpdate(sql);
    		ret += "Asignatura con código: "+codigo+" modificada correctamente:\n"+
    		"nombre "+nombre + ", créditos: "+creditos;
    		ret += "\nEstas son el resto de asignaturas:\n"+this.listarAsignaturas();
    		st.close();
    		
    	}catch (Exception ex){
    		ret += ex.getMessage();
    		
    	}finally {
    		con.desconectar();
    		System.out.println(this.listarAsignaturas());
        	
    		return ret;
    	}
    }

    /**
     * Devuelve los alumnos de una asignatura
     * @param codigo {int}: codigo de la asignatura a imprimir
     */
    @SuppressWarnings("finally")
	@Override
    public String alumnosDeUnaAsignatura(int codigo) {
        DBConnection con = new DBConnection();
        String ret = "";
        String sql = "select * from alumnos where dni in (\n" +
                "\tselect dni_fk from matricula where cod_asignatura_fk = "+codigo+"\n" +
                ");";
        try {
            //Busca el nombre de la asignatura
            for(Asignatura a : this.buscaAsignaturas()){
                if(codigo == a.getCodigo())
                    ret = "Alumnos matriculados en "+a.getNombre()+":\n";
            }
            //Imprime los alumnos de una asignatura
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            //va almacenando los alumnos la uso también para comprobar que la
            //sentencia no está vacia
            String alumnos = "";
            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                Alumno a = new Alumno(dni,nombre,edad);
                alumnos+= a.toString()+"\n";
            }
            if(!alumnos.equals("")) {
            	ret += alumnos;
            }else {
            	ret += "Actualmente no existen alumnos matriculados";
            }
            st.close();
        } catch(Exception ex) {
            ret += ex.getMessage();
        } finally {
            con.desconectar();
            return ret;
        }
    }


    /**
     * Matricula un alumno en una asignatura
     */
    @SuppressWarnings("finally")
	@Override
    public String matricular(String dni, int codigo) {
    	DBConnection con = new DBConnection();
    	//variable con la asignatura
    	String Nombreasignatura = new String();
    	String ret = "";
    	//busco la asignatura para guardarla
    	for(Asignatura a : this.buscaAsignaturas()) {
    		if(codigo == a.getCodigo())
    			Nombreasignatura = a.getNombre();
    	}
    	String sql = "INSERT INTO matricula(dni_fk, cod_asignatura_fk) VALUES" +
                "('"+dni+"',"+codigo+");";
    	try {
    		Statement st = con.getConnection().createStatement();
    		st.executeQuery(sql);
    		ret += "El alumno con DNI "+dni+ " ha sido matriculado en "+Nombreasignatura;
    		st.close();
 
    	}catch(Exception ex) {
    		ret += ex.getMessage();
    	}finally {
    		con.desconectar();
    		return ret;
    	}

    }


}
