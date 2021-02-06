package com.hlc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlc.dao.AlumnoDAO;
import com.hlc.dao.General;

//import com.hlc.dao.AlumnoDAOBBDD;
/**
 * Servlet implementation class ServletInsertar
 * Esta es la clase que va ha atender todas las peticiones desde el cliente haciendo
 * de unión entre java y js por ajax con scripts.js cargado en el html 13:54
 * @author Antonio José Lojo Ojeada
 */
@WebServlet("/ServletInsertar")
public class ServletInsertar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletInsertar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * lo que hace cuando recibe datos por POST
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//sout de info
		System.out.println("Petición asistida con exito");
		String ret = ""; //resultado que voy a mandar a imprimir
		AlumnoDAO alumno = new AlumnoDAO();
		
		//Carga los alumnos si no hay alumnos agrega datos por defecto
		alumno.cargarAlumnos();
		if(alumno.getAlumnos().isEmpty())
			General.initialInsert();
		
		boolean flag = true;
		
		//a continuación capturo los datos de la petición
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String edad = request.getParameter("edad");
		
		//imprimo a modo de log
		if(!alumno.compruebaDni(dni)) {
			ret += "Error:\nEl dni no es válido";
			flag = false;
		}
		if(!alumno.compruebaEdad(edad)){
			ret += "Error:\nLa edad no es válida";
			flag = false;
		}
		
		//si pasa 
		if(flag)
			ret += alumno.insertarAlumno(dni, nombre, Integer.parseInt(edad)); //almacena el resultado del metodo
		
		//Envia dicho resultado al script
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(ret);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
