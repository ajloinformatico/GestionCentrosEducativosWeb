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

/**
 * Servlet implementation class ServletModificar
 */
@WebServlet("/ServletModificar")
public class ServletModificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		if(!alumno.buscaAlumno(dni)) {
			flag = false;
			ret += "Error:\nNo existe ningún usuario con este dni\n";
		}
		if(!alumno.compruebaEdad(edad)) {
			flag = false;
			ret += "Error:\nLa edad no es válida";
		}
		if(flag) {
			ret += alumno.modificarAlumno(dni, nombre, Integer.parseInt(edad));
			//HAY QUE INDICARÑE EL TIPO
		}
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
