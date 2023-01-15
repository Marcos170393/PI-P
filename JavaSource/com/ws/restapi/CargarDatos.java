package com.ws.restapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capa1presentacion.Casilla;
import com.capa1presentacion.Ciudad;
import com.capa1presentacion.Departamento;
import com.capa1presentacion.Formulario;
import com.capa1presentacion.Parametro;
import com.capa1presentacion.Rol;
import com.capa1presentacion.TipoDato;
import com.capa1presentacion.TipoMedicion;
import com.capa1presentacion.Usuario;
import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.entities.CasillasBean;
import com.capa3Persistencia.entities.CiudadEntity;
import com.capa3Persistencia.entities.CiudadesBean;
import com.capa3Persistencia.entities.DepartamentoEntity;
import com.capa3Persistencia.entities.DepartamentosBean;
import com.capa3Persistencia.entities.ParametroEntity;
import com.capa3Persistencia.entities.ParametrosBean;
import com.capa3Persistencia.entities.TipoDatoEntity;
import com.capa3Persistencia.entities.TiposDatoBean;
import com.capa3Persistencia.entities.UsuarioEntity;
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
	
	@EJB
	com.capa2LogicaNegocio.GestionParametroService gestionParametroService;
	
	@EJB
	com.capa2LogicaNegocio.GestionTipoDatoService gestionTipoDatoService;
	
	@EJB
	com.capa2LogicaNegocio.GestionCasillaService gestionCasillaService;
	
	@EJB
	com.capa2LogicaNegocio.GestionDepartamentoService gestionDptoService;
	
	@EJB
	com.capa2LogicaNegocio.GestionFormularioService gestionFormularioService;
	
	@EJB
	UsuariosBean usuarioBean = new UsuariosBean();

	@EJB
	CiudadesBean ciudadBean = new CiudadesBean();
	
	@EJB
	ParametrosBean parametroBean = new ParametrosBean();
	
	@EJB
	TiposDatoBean tipoDatoBean = new TiposDatoBean();
	
	@EJB
	DepartamentosBean departamentoBean = new DepartamentosBean();

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
			CiudadEntity c = new CiudadEntity("Durazno");
			c = ciudadBean.crear(c);
			out.println("Se ha creado la ciudad con exito " + c);

			CiudadEntity dur = ciudadBean.buscarCiudadName(c.getNombre());

			Usuario u = new Usuario("Martin", "Rodriguez", "martin123", "martin@gmail.com", true, "martin.lti",
					"12345678", "Joaquin Suarez 702", dur, Long.valueOf(92723073), Rol.ADMINISTRADOR);

			u = gestionUsuarioService.agregarUsuario(u);
			out.println("Se ha creado el usuario con exito " + u);
			
			
			Usuario user = new Usuario("Marcos", "Correa", "marcos123", "marcos@gmail.com", true, "marcos.lti", Rol.AFICIONADO);
			user = gestionUsuarioService.agregarUsuario(user);
			out.println("Usuario agregado" + user);
		
			
			
			/*Usuario e = new Usuario("admin", "admin", "admin", "admin@gmail.com", true, "admin", "31022972",
					"Artigas 123", dur, Long.valueOf(93934432), Rol.ADMINISTRADOR);
			Usuario usuarioCreado = gestionUsuarioService.agregarUsuario(e);
			out.println("Se creo el empleado:" + usuarioCreado);
*/
			Parametro p = new Parametro("Longitud");
			Parametro p2 = new Parametro("Latitud");
			Parametro p3 = new Parametro("Temperatura");
			p = gestionParametroService.agregarParametro(p);
			p2 = gestionParametroService.agregarParametro(p2);
			p3 = gestionParametroService.agregarParametro(p3);
			out.println("Se agregaron los parametros");
	
			
			TipoDato t = new TipoDato("Grados", "DECIMAL");
			TipoDato t2 = new TipoDato("Kpa", "DECIMAL");
			TipoDato t3 = new TipoDato("Co2", "DECIMAL");
			TipoDato t4 = new TipoDato("G/m3", "ENTERO");
			t = gestionTipoDatoService.agregarTipoDato(t);
			t2 = gestionTipoDatoService.agregarTipoDato(t2);
			t3 = gestionTipoDatoService.agregarTipoDato(t3);
			t4 = gestionTipoDatoService.agregarTipoDato(t4);
			out.println("Se agregaron los tipos de dato");
			
			ParametroEntity p1 = parametroBean.buscarParametro(1L);
			TipoDatoEntity t1 = tipoDatoBean.obtenerTipoDato(1L);
			UsuarioEntity use =  usuarioBean.buscarNombreUsuarioEntity("martin.lti");
			
			out.println(use);
			Casilla casilla = new Casilla("Creando casilla", "Casilla", true, p1, t1, use);
			Casilla casilla2 = new Casilla("Creando asdasd", "Casilla2", false, p1, t1, use);
			casilla = gestionCasillaService.agregarCasilla(casilla);
			casilla2 = gestionCasillaService.agregarCasilla(casilla2);
			out.println("Casilla creada.. " + casilla);
			out.println("Casilla creada.. " + casilla2);
			
	
			List<CasillaEntity> lista = new ArrayList<>();
			lista.add(gestionCasillaService.forCasillaEntity(casilla));
			lista.add(gestionCasillaService.forCasillaEntity(casilla2));
			out.println(lista);
			
			Departamento dpto = new Departamento("Durazno");
			dpto = gestionDptoService.agregarDepartamento(dpto);
			out.println("Departamento creado " + dpto);
			dpto = gestionDptoService.buscarDepartamentoEntity(dpto.getIdDepartamento());
			
			
			//DepartamentoEntity depar = departamentoBean.buscarDepartamento(dpto.getIdDepartamento());
			
			
			Formulario formulario = new Formulario("Formulario 1", new Date(), TipoMedicion.MANUAL, "Se crea formulario de prueba", lista, use, dpto);
			out.println(formulario);
			formulario = gestionFormularioService.agregarFormulario(formulario);
			out.println("Formulario creado " + formulario);
			
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
