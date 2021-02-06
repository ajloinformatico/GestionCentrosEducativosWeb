/**
 * 
 */
package com.hlc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author antoniojoselojoojeda
 *
 */
public class DBConnection {

    /**Parametros de conexion*/
    static String bd = "instituto";
    static String login = "root"; //your mysql user
    static String password = ""; //your mysql password
    static String url = "jdbc:mysql://localhost/"+bd;
    Connection connection = null;

    /** Constructor de DbConnection */
    public DBConnection() {
        try{
            //obtenemos el driver de para mysql
            Class.forName("org.mariadb.jdbc.Driver");
            //obtenemos la conexión
            connection = DriverManager.getConnection(url,login,password);

            if (connection!=null){
                //System.out.println("Conexión a base de datos "+bd+" OK\n");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    /**Permite retornar la conexión*/
    public Connection getConnection(){
        return connection;
    }

    public void desconectar(){
        connection = null;
    }

}
