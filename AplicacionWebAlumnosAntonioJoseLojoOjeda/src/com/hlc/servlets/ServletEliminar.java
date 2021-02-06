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
 * Servlet implementation class ServletEliminar
 */
@WebServlet("/ServletEliminar")
public class ServletEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Petición asistida con exito");
		String ret = "";
		AlumnoDAO alumno = new AlumnoDAO();
		
		//Carga los alumnos si no hay alumnos agrega datos por defecto
		alumno.cargarAlumnos();
		if(alumno.getAlumnos().isEmpty())
			General.initialInsert();
		
		boolean flag = true;
		String dni = request.getParameter("dni");
		//si el dni es correcto
		if(!alumno.compruebaDni(dni)) {
			flag = false;
			ret += "Error:\nEl dni no es válido\n";
		}//si el alumno existe
		if(!alumno.buscaAlumno(dni)) {
			flag = false;
			ret += "Error:\nNo existe ningún usuario con este dni";
		}
		
		//Si no ha habido errores
		if(flag) {
			ret += alumno.eliminarAlumno(dni);
		}
		
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