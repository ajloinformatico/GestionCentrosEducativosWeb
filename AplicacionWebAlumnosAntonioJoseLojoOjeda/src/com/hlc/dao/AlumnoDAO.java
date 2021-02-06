package com.hlc.dao;
/*Imports de la base de datos*/
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hlc.connection.DBConnection;
/*Modelo*/
import com.hlc.vo.Alumno;
import com.hlc.vo.Asignatura;

/*clase interacción a partir del modelo imnplementando la interfaz del alumno*/
public class AlumnoDAO implements IAlumnoDAO{

    private DBConnection con;
    private final List<Alumno> alumnos;

    public AlumnoDAO() {
        alumnos = new ArrayList<Alumno>();
    }



    /**
     * Devuelve los alumnos cargados en el sistema
     * @return this.alumnos
     */
    public List<Alumno> getAlumnos() {
        return alumnos;
    }


    /**
     * Carga los alumnos en el programa a partir de la base de datos
     */
    @Override
    public void cargarAlumnos() {
        DBConnection con = new DBConnection();
        String sql = "Select * from alumnos";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                Alumno a = new Alumno(dni,nombre,edad);
                alumnos.add(a);
            }
            st.close();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            con.desconectar();
        }

    }

    /**
     * Devuelve el resultado de la query impreso.
     * Porque el ejercicio pide consultas pero lo haría
     * desde los datos cargados en memoria
     */
    @SuppressWarnings("finally")
	public String listarAlumnos() {

        DBConnection con = new DBConnection();
        String sql = "Select * from alumnos";
        String result = new String();
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                Alumno a = new Alumno(dni,nombre,edad);
                result += a + "\n";
            }
            st.close();
        }
        catch(Exception ex) {
            result = ex.getMessage();
        }finally{
            con.desconectar();
            return result;
        }
    }

    /**
     * Ingresa un nuevo alumno en la base de datos y en memoria
     * Comprueba que el dni no esté duplicado a partir de una bandera en caso de que
     * no procede a ejecutar la query y a agregarlo a la lista en memoria
     * @param dni String: String del dni del alumno
     * @param nombre String: String del nombre del alumno
     * @param edad int: edad del alumno
     */
    @SuppressWarnings("finally")
	@Override
    public String insertarAlumno(String dni, String nombre, int edad) {
        boolean flag = true;
        String result = new String();
        for(Alumno alumno: this.alumnos){
            if(alumno.getDni().equals(dni)){
                System.out.println("Error:\nEl alumno que intenta agregar ya existe");
                flag = false;
            }
        }
        
        
        
        if(flag){
            DBConnection con = new DBConnection();
            Alumno a = new Alumno(dni,nombre,edad);
            String sql = "INSERT INTO alumnos(dni, nombre, edad) VALUES('" + a.getDni()+ "', '" + a.getNombre() + "', " + a.getEdad() + ")";
            try {
                //Inserta el alumno en la base de datos
                Statement st = con.getConnection().createStatement();
                st.executeQuery(sql);
                alumnos.add(a);
                result = nombre +" con dni "+dni+" se ha registrado Exitosamente\nEstos son el resto de alumnos :\n"+this.listarAlumnos();
                
                st.close();
            } catch (Exception ex) {
                System.out.println("Error:\nSe ha producido un error en la insercción\nporfavor compruebe los datos");
                result = ex.getMessage();
            } finally {
                con.desconectar();
                return result;
            }
        }
        else {//si ya existe un alumno con este dni
        	return "Error:\nYa existe un alumno con este dni en el sistema";
        }
    }

    /**
     * Modifica un alumno tanto en la base de datos como en la base de datos
     * @param dni String: dni del alumno que se va ha eliminar
     * @param edad int: edad que modificada
     */
    @SuppressWarnings("finally")
	@Override
    public String modificarAlumno(String dni, String nombre, int edad) {
        DBConnection con = new DBConnection();
        String sql = "update alumnos SET edad ='"+edad+"', nombre = '"+nombre+"' where dni ='"+dni+"'";
        String ret = "";
        try{
        	//Modifica en la query
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);
            //Modifica en memoria
            int index = 0;
            for(Alumno a : this.alumnos){
                if(a.getDni().equals(dni)){
                	this.alumnos.get(index).setNombre(nombre);
                    this.alumnos.get(index).setEdad(edad);
                }
                index++;
            }
            ret += "El alumno con dni "+dni+" ha sido modificado correctamente\nEstos son el resto de alumnos :\n"+this.listarAlumnos();
            st.close();
                
        } catch (Exception ex) {
        	ret += ex.getMessage(); 
        } finally {
            con.desconectar();
            return ret;
        }
    }

    /**
     * Comprueba que la entrada del dni es correcta
     * el alumno no existe y por lo tanto devuelvo true
     * @param dni String: dni del cliente
     * @return true: si existe
     */
    @Override
    public boolean compruebaDni(String dni) {
        if (dni.length() == 9) {
            for (int i = 0; i < 9; i++) {
                String v = String.valueOf(dni.charAt(i));//Valor en formato string de cada iteracción para comprobar el tipo
                //El ultimo elemento no puede ser un número
                if (i == 8) {
                    try{
                        Integer.parseInt(v);
                        System.out.println("Error:\nEl formato del dni no es válido");
                        return false;
                    }catch(Exception e){
                        //Formato correcto
                        System.out.println("El formato del dni es correcto");
                        return true;
                    }
                }
                //menos el último elemento todos los valores tienen que ser un número
                try{
                    Integer.parseInt(v);
                    continue;
                }catch (Exception e){
                    //no es número
                    System.out.println("Error:\nEl formato del dni no es válido");
                    return false;
                }
            }
        }
        //fallo de longitud
        System.out.println("Error:\nEl formato del dni no es válido");
        return false;
    }

    /**
     * Comprueba si la entrada del usuario es un número
     * @param edad String: la paso como string para hacer un try catch y comprobar si se puede pasar a entero
     * @return true boolean: si es un número
     */
    @Override
    public boolean compruebaEdad(String edad) {
        try{
            int edad2 = Integer.parseInt(edad);
            if(edad2 > 0 && edad2 < 130)
                return true;
            //La edad es demasiado alta o baja
            System.out.println("Error:\nIntroduzca una edad un poco más realista");
            return false;
        }catch(Exception e){
            System.out.println("Error:\nEl formato de la edad no es correcto");
            return false;
        }
    }

    /**
     * Comprueba si existe un alumno a partir del dni
     * @param dni String: dni del alumno a buscar
     * @return true boolean: si el usuario se enucentra en el sistema
     */
    @Override
    public boolean buscaAlumno(String dni) {
        for(Alumno a : this.alumnos){
            if(a.getDni().equals(dni)){
                return true;
            }
        }
        System.out.println("Error:\nEl alumno con dni "+dni+" no existe");
        return false;
    }


    /**
     * Elimina un alumno a partir de su dni
     * @param dni String: dni del alumno a eleminar
     * @return boolean true: si el alumno existe
     */
    @SuppressWarnings("finally")
	@Override
    public String eliminarAlumno(String dni) {
    	
        DBConnection con = new DBConnection();
        String sql = "delete from alumnos where dni='"+dni+"'";
        String result = "";
        try{
            //Ejecuta la query para eliminar de la base de datos
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);
            //Elimina el alumno de la lista
            this.alumnos.removeIf(a -> a.getDni().equals(dni));
            result += "El alumno con dni "+dni+" ha sido eliminado correctamente";
            st.close();
        }catch (Exception ex){
            result += ex.getMessage();
        }finally {
            con.desconectar();
            return result;
        }
    }
        
    

    @SuppressWarnings("finally")
	@Override
    public String asignaturasDeUnAlumno(String dni) {
        DBConnection con = new DBConnection();
        String result = "";
        String sql = "select * from asignaturas where cod_asignatura in (" +
                "select cod_asignatura_fk from matricula where dni_fk = '"+ dni +"');";
        try {
            for(Alumno a : this.alumnos){
                if(a.getDni().equals(dni))
                    result += "Asignaturas de "+a.getNombre() + ":\n";
            }
            //Imprime las asignturas del alumno
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            String asignaturas = "";
            while (rs.next()) {
                int cod_asig = rs.getInt("cod_asignatura");
                String nombre = rs.getString("nombre_asignatura");
                int creditos = rs.getInt("creditos");
                Asignatura a = new Asignatura(cod_asig,nombre,creditos);
                asignaturas += a.toString() + "\n";
            }
            if(!asignaturas.equals("")) {
            	result += asignaturas;
            }else {
            	result += "Actualmente no está matriculado en ninguna asignatura";
            }
            st.close();
        } catch(Exception ex) {
        	result += ex.getMessage();
        } finally {
            con.desconectar();
            return result;
        }
    }
}
