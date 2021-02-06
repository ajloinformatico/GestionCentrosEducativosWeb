package com.hlc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlc.dao.AlumnoDAO;
import com.hlc.dao.AsignaturaDAO;
import com.hlc.dao.General;

/**
 * Servlet implementation class ServletListarAsig
 * Lista todas las asignaturas
 */
@WebServlet("/ServletListarAsig")
public class ServletListarAsig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarAsig() {
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
		AsignaturaDAO asignaturas = new AsignaturaDAO();
		//Carga los alumnos si no hay alumnos agrega datos por defecto
		alumno.cargarAlumnos();
		if(alumno.getAlumnos().isEmpty())
			General.initialInsert();
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(asignaturas.listarAsignaturas()); //Envio directamente los datos de las asignaturas
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
