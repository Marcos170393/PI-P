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
		UsuariosBean bean = new UsuariosBean();
		try {
			Usuario e = new Usuario("Martanata","Robertinho","123456","robert@tarasca",true,"marta",Rol.valueOf("ADMINISTRADOR"));
			Usuario usuarioCreado = gestionUsuarioService.agregarUsuario(e);
			out.println("Se creo el empleado:"+ usuarioCreado);
			
			Usuario e2 = new Usuario("Pedroteto","Sancheze","123456","pedro@gmail.com",true,"pedro",Rol.AFICIONADO);
			Usuario usuarioCreado2 = gestionUsuarioService.agregarUsuario(e2);
			out.println("Se creo el empleado:"+ usuarioCreado2);
			
			Usuario e3 = new Usuario("Gonzalos","Perezeito","123456","gonza@gmail.com",true,"gonza",Rol.INVESTIGADOR);
			Usuario usuarioCreado3 = gestionUsuarioService.agregarUsuario(e3);
			out.println("Se creo el empleado:"+ usuarioCreado3);


			
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
