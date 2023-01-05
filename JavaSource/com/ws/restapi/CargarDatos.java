package com.ws.restapi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa1presentacion.Ciudad;
import com.capa1presentacion.Rol;
import com.capa1presentacion.Usuario;
import com.capa3Persistencia.entities.UsuariosBean;

/**
 * Servlet implementation class CargarDatos
 */
@WebServlet("/CargarDatos")
public class CargarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	com.capa2LogicaNegocio.GestionUsuarioService gestionUsuarioService;

	@EJB
	com.capa2LogicaNegocio.GestionCiudadService gestionCiudadService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CargarDatos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath() + "\n");
		PrintWriter out = response.getWriter();
		UsuariosBean bean = new UsuariosBean();
		try {
			Ciudad c = new Ciudad("Durazno");
			c = gestionCiudadService.agregarCiudad(c);
			out.println("Se ha creado la ciudad con exito");

			Ciudad dur = gestionCiudadService.buscarCiudadEntity(1L);

			Usuario u = new Usuario("Martin", "Rodriguez", "martin123", "martin@gmail.com", true, "martin.lti",
					"52304816", "Joaquin Suarez 702", dur, Long.valueOf(92723073), Rol.ADMINISTRADOR);

			u = gestionUsuarioService.agregarUsuario(u);
			out.println("Se ha creado el usuario con exito" + u);

			Usuario e = new Usuario("admin", "admin", "admin", "admin@gmail.com", true, "admin", "31022972",
					"Artigas 123", dur, Long.valueOf(93934432), Rol.ADMINISTRADOR);
			Usuario usuarioCreado = gestionUsuarioService.agregarUsuario(e);
			out.println("Se creo el empleado:" + usuarioCreado);

			/*
			 * Usuario e2 = new
			 * Usuario("Pedro","Martinez","123456","pedro@gmail.com",true,"Pedro",Rol.
			 * AFICIONADO); Usuario usuarioCreado2 =
			 * gestionUsuarioService.agregarUsuario(e2); out.println("Se creo el empleado:"+
			 * usuarioCreado2);
			 * 
			 * Usuario e3 = new
			 * Usuario("Gonzalo","Perez","123456","gonza@gmail.com",true,"Gonza",Rol.
			 * INVESTIGADOR); Usuario usuarioCreado3 =
			 * gestionUsuarioService.agregarUsuario(e3); out.println("Se creo el empleado:"+
			 * usuarioCreado3);
			 * 
			 * Usuario e4 = new
			 * Usuario("Martin","Morales","123456","martin@gmail.com",true,"Martin",Rol.
			 * INVESTIGADOR); Usuario usuarioCreado4 =
			 * gestionUsuarioService.agregarUsuario(e4); out.println("Se creo el empleado:"+
			 * usuarioCreado4);
			 * 
			 * Usuario e5 = new
			 * Usuario("root","root","root","root@gmail.com",true,"root",Rol.ADMINISTRADOR);
			 * Usuario usuarioCreado5 = gestionUsuarioService.agregarUsuario(e5);
			 * out.println("Se creo el empleado:"+ usuarioCreado5);
			 * 
			 * Usuario e6 = new
			 * Usuario("Adrian","Suar","123456","adrian@gmail.com",true,"Adrian",Rol.
			 * INVESTIGADOR); Usuario usuarioCreado6 =
			 * gestionUsuarioService.agregarUsuario(e6); out.println("Se creo el empleado:"+
			 * usuarioCreado6);
			 * 
			 * Usuario e7 = new
			 * Usuario("Florencia","Pose","123456","mPose@gmail.com",true,"Flor",Rol.
			 * ADMINISTRADOR); Usuario usuarioCreado7 =
			 * gestionUsuarioService.agregarUsuario(e7); out.println("Se creo el empleado:"+
			 * usuarioCreado7);
			 */

		} catch (Exception e) {
			out.println("No se creo el empleado:" + e.getClass().getSimpleName() + "-" + e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
