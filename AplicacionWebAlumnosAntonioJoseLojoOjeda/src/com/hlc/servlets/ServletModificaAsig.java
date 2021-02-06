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
 * Servlet implementation class ServletModificaAsig
 */
@WebServlet("/ServletModificaAsig")
public class ServletModificaAsig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificaAsig() {
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
		
		boolean flag = true;
		//a continuación capturo los datos de la petición
		String codigo = request.getParameter("codigo_asig");
		String nombre = request.getParameter("nombre_asig");
		String creditos = request.getParameter("creditos");
		
		//Comprueba que el código es correcto
		if (asignaturas.checkCodigo(codigo) != 2) {
			ret += "Error:\nEl código no es válido";
			flag = false;
		}
		//Comprueba que no existe ninguna asiganatura con ese nombre
		if (asignaturas.checkNombre(nombre)) {
			ret += "Error:\nYa existen asignturas con ese nombre";
			flag = false;
			 
		}
		//Comprueba que el código es correcto
		if(!asignaturas.checkCreditos(creditos)) {
			ret += "Error:\nEl crédito de las asignaturas debe estar comprendido en tre 20 y 200";
			flag = false;
		}
		if(flag) { 
			ret += asignaturas.modificarAsignatura(Integer.parseInt(codigo), nombre, Integer.parseInt(creditos));
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
