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
 * Servlet implementation class ServletAlumnosAsignatura
 */
@WebServlet("/ServletAlumnosAsignatura")
public class ServletAlumnosAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAlumnosAsignatura() {
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
		AsignaturaDAO asignaturas = new AsignaturaDAO();
		alumno.cargarAlumnos();
		if(alumno.getAlumnos().isEmpty())
			General.initialInsert();
		String codigo = request.getParameter("codigo_asig");
		if (asignaturas.checkCodigo(codigo) == 2) {
			ret += asignaturas.alumnosDeUnaAsignatura(Integer.parseInt(codigo));
		}else {
			ret += "El código no es válido";
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
