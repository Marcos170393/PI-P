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

import com.capa1presentacion.Rol;
import com.capa1presentacion.UsuarioLocal;
import com.capa3Persistencia.entities.UsuariosBeanService;

/**
 * Servlet implementation class CargarDatos
 */
@WebServlet("/CargarDatos")
public class CargarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	com.capa2LogicaNegocio.GestionUsuarioService gestionUsuarioService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath()+"\n");
		PrintWriter out = response.getWriter();
		UsuariosBeanService bean = new UsuariosBeanService();
		try {
			UsuarioLocal e = new UsuarioLocal("admin","admin","admin","admin@gmail.com",true,"admin",Rol.ADMINISTRADOR);
			UsuarioLocal usuarioCreado = gestionUsuarioService.agregarUsuario(e);
			out.println("Se creo el empleado:"+ usuarioCreado);
			
			UsuarioLocal e2 = new UsuarioLocal("Pedro","Martinez","123456","pedro@gmail.com",true,"Pedro",Rol.AFICIONADO);
			UsuarioLocal usuarioCreado2 = gestionUsuarioService.agregarUsuario(e2);
			out.println("Se creo el empleado:"+ usuarioCreado2);
			
			UsuarioLocal e3 = new UsuarioLocal("Gonzalo","Perez","123456","gonza@gmail.com",true,"Gonza",Rol.INVESTIGADOR);
			UsuarioLocal usuarioCreado3 = gestionUsuarioService.agregarUsuario(e3);
			out.println("Se creo el empleado:"+ usuarioCreado3);
		
			UsuarioLocal e4 = new UsuarioLocal("Martin","Morales","123456","martin@gmail.com",true,"Martin",Rol.INVESTIGADOR);
			UsuarioLocal usuarioCreado4 = gestionUsuarioService.agregarUsuario(e4);
			out.println("Se creo el empleado:"+ usuarioCreado4);
			
			UsuarioLocal e5 = new UsuarioLocal("root","root","root","root@gmail.com",true,"root",Rol.ADMINISTRADOR);
			UsuarioLocal usuarioCreado5 = gestionUsuarioService.agregarUsuario(e5);
			out.println("Se creo el empleado:"+ usuarioCreado5);
			
			UsuarioLocal e6 = new UsuarioLocal("Adrian","Suar","123456","adrian@gmail.com",true,"Adrian",Rol.INVESTIGADOR);
			UsuarioLocal usuarioCreado6 = gestionUsuarioService.agregarUsuario(e6);
			out.println("Se creo el empleado:"+ usuarioCreado6);
			
			UsuarioLocal e7 = new UsuarioLocal("Florencia","Pose","123456","mPose@gmail.com",true,"Flor",Rol.ADMINISTRADOR);
			UsuarioLocal usuarioCreado7 = gestionUsuarioService.agregarUsuario(e7);
			out.println("Se creo el empleado:"+ usuarioCreado7);


			
		}catch(Exception e) {
			out.println("No se creo el empleado:"+ e.getClass().getSimpleName()+"-"+e.getMessage());
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
