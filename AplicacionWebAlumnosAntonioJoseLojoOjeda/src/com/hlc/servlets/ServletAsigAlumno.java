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
 * Servlet implementation class ServletAsigAlumno
 */
@WebServlet("/ServletAsigAlumno")
public class ServletAsigAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAsigAlumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Petición asistida con exito");
		AlumnoDAO alumno = new AlumnoDAO();
		String ret = ""; //resultado que voy a mandar a imprimir
		//Carga los alumnos si no hay alumnos agrega datos por defecto
		alumno.cargarAlumnos();
		if(alumno.getAlumnos().isEmpty())
			General.initialInsert();
		boolean flag = true;
		String dni = request.getParameter("dni");
		
		if(!alumno.compruebaDni(dni)) {
			flag = false;
			ret += "Error:\nEl dni no es válido\n";
		}//si el alumno existe
		if(!alumno.buscaAlumno(dni)) {
			flag = false;
			ret += "Error:\nNo existe ningún usuario con este dni";
		}
		if(flag)
			ret += alumno.asignaturasDeUnAlumno(dni);
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
