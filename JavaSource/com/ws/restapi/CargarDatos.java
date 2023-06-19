package com.ws.restapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
import com.capa1presentacion.Registro;
import com.capa1presentacion.Rol;
import com.capa1presentacion.TipoDato;
import com.capa1presentacion.TipoMedicion;
import com.capa1presentacion.Usuario;
import com.capa2LogicaNegocio.GestionDepartamentoService;
import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.entities.CasillasBean;
import com.capa3Persistencia.entities.CiudadEntity;
import com.capa3Persistencia.entities.UsuarioEntity;
import com.capa3Persistencia.entities.UsuariosBean;
import com.capa3Persistencia.exception.PersistenciaException;


/**
 * Servlet implementation class CargarDatos
 */
@WebServlet("/CargarDatos")
public class CargarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final String CIUDAD_1 = "Araminda";
	final String CIUDAD_2 = "Salinas";
	final String DEPTO_1 = "Durazno";
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
	com.capa2LogicaNegocio.GestionRegistroService gestionRegistroService;

	@EJB
	UsuariosBean usuarioBean = new UsuariosBean();

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
		try {
			try {
				
				this.cargarDepartamentos();
				this.cargarCiudades();
				
			} catch (Exception e) {
				System.out.println("Error al cargar las ciudades y departamentos");
			}
			
			
			out.println("Se han creado los departamentos con exito");
			out.println("Se han creado las ciudades con exito");
			
			
			Ciudad c = gestionCiudadService.buscarCiudadEntity(CIUDAD_1);
			Ciudad c2 = gestionCiudadService.buscarCiudadEntity(CIUDAD_2);
			
			Usuario u = new Usuario("Admin", "Administrador", "administrador", "administrador@gmail.com", true, "administrador", "12345678",
					"Durazno 123", c, Long.valueOf(92723073), Rol.ADMINISTRADOR);

			u = gestionUsuarioService.agregarUsuario(u);
			out.println("Se ha creado el usuario con exito " + u);

			Usuario u2 = new Usuario("Invest", "Investigador", "investigador", "investigador@gmail.com", true,
					"investigador", "12345867", "Canelones 132", c2, Long.valueOf(91885647), Rol.INVESTIGADOR);
			u2 = gestionUsuarioService.agregarUsuario(u2);
			out.println("Usuario agregado" + u2);

			Usuario u3 = new Usuario("Afic", "Aficionado", "aficionado", "aficionadoUser@gmail.com", true,
					"aficionado", Rol.AFICIONADO);
			u3 = gestionUsuarioService.agregarUsuario(u3);
			
			/*
			 * Usuario e = new Usuario("admin", "admin", "admin", "admin@gmail.com", true,
			 * "admin", "31022972", "Artigas 123", dur, Long.valueOf(93934432),
			 * Rol.ADMINISTRADOR); Usuario usuarioCreado =
			 * gestionUsuarioService.agregarUsuario(e); out.println("Se creo el empleado:" +
			 * usuarioCreado);
			 */
			try {
				TipoDato dato1 = new TipoDato("grados", "DECIMAL");
				TipoDato dato2 = new TipoDato("kpa", "DECIMAL");
				TipoDato dato3 = new TipoDato("co2", "DECIMAL");
				TipoDato dato4 = new TipoDato("g/m3", "ENTERO");
				dato1 = gestionTipoDatoService.agregarTipoDato(dato1);
				dato2 = gestionTipoDatoService.agregarTipoDato(dato2);
				dato3 = gestionTipoDatoService.agregarTipoDato(dato3);
				dato4 = gestionTipoDatoService.agregarTipoDato(dato4);
				
				Parametro param1 = new Parametro("Longitud");
				Parametro param2 = new Parametro("Altitud");
				Parametro param3 = new Parametro("Temperatura");
				
				param1 = gestionParametroService.agregarParametro(param1);
				param2 = gestionParametroService.agregarParametro(param2);
				param3 = gestionParametroService.agregarParametro(param3);
				
				out.println("Tipos de datos y parametros creados");
				
				
				Casilla casilla = new Casilla("Creando casilla", "Casilla", true, param1, dato1, u);
				casilla = gestionCasillaService.agregarCasilla(casilla);
				out.println("Casilla creada.. " + casilla);
				
				Casilla casilla2 = new Casilla("Creando casilla dos", "Casilla dos", true, param2, dato2, u);
				casilla2 = gestionCasillaService.agregarCasilla(casilla2);
				out.println("Casilla creada.. " + casilla2);
				
				List<CiudadEntity> ciudades = new ArrayList<>();
				ciudades.add(gestionCiudadService.forCiudadEntity(c2));
				
				
				List<CasillaEntity> casillas = new ArrayList<>();
				casillas.add(gestionCasillaService.forCasillaEntity(casilla));
				casillas.add(gestionCasillaService.forCasillaEntity(casilla2));
				
				Departamento dpto = gestionDptoService.buscarDepartamentoEntity(DEPTO_1);
				
				Formulario form1 = new Formulario("Formulario 1", new Date(), TipoMedicion.MANUAL,
						"Se crea un formulario de prueba", casillas, u, dpto);
				form1 = gestionFormularioService.agregarFormulario(form1);
				
				Formulario form2 = new Formulario("Formulario 2", new Date(), TipoMedicion.AUTOMATICO,
						"Probando formulario 2", casillas, u, dpto);
				form2 = gestionFormularioService.agregarFormulario(form2);
				out.println("Formularios creados");
				
				Registro reg = new Registro(u, form2, casilla2, new Date(), 100l, 1);
				reg = gestionRegistroService.agregarRegistro(reg);
				
				Registro reg2 = new Registro(u, form1, casilla2, new Date(), 400l, 1);
				reg2 = gestionRegistroService.agregarRegistro(reg2);
				out.print("Registros agregados");
			} catch (PersistenciaException p) {
				p.getMessage();
				
			}
			
		} catch (Exception e) {
		UsuariosBean bean = new UsuariosBean();
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
	
	public void cargarDepartamentos() throws Exception {
	    try {
	      File archivo = new File("C:\\GIT\\PI-P\\JavaSource\\META-INF\\departamentos.txt"); // MARCOS
//	      File archivo = new File("C:\\GIT\\PI-P\\JavaSource\\META-INF\\departamentos.txt");    // CAMBIA SEGUN DIRECTORIO PROPIO
	      Scanner depto = new Scanner(archivo);
	      while (depto.hasNextLine()) {
	        String linea = depto.nextLine();
	        Departamento depar = new Departamento(linea);
	        gestionDptoService.agregarDepartamento(depar);
	       }
	      depto.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("No se encontr� el archivo");
	      e.printStackTrace();
	    }
	}
	
	public  void cargarCiudades() throws Exception {
		
		   try {
			      File archivo = new File("C:\\GIT\\PI-P\\JavaSource\\META-INF\\ciudades.txt");  //MARCOS
//			      File archivo = new File("C:\\GIT\\PI-P\\JavaSource\\META-INF\\ciudades.txt");    // CAMBIA SEGUN DIRECTORIO PROPIO
			      Scanner ciudades = new Scanner(archivo);
			      while (ciudades.hasNextLine()) {
				        String linea = ciudades.nextLine();
				        String[] datos = linea.split(";"); //Separa los datos por ";"
				        Long depto = Long.valueOf(datos[0]); //ID departamentos 
				        Departamento d = gestionDptoService.buscarDepartamentoEntity(depto); //Busca los dptos por ID
				        String ciudad = datos[1]; //Ciudades
				        Ciudad c = new Ciudad(ciudad);
				        c = gestionCiudadService.agregarCiudad(c);		//Crea ciudades
				        gestionDptoService.agregarCiudad(d.getIdDepartamento(),c.getIdCiudad()); //Asigna las ciudades a los departamentos
			      }
			      ciudades.close();
		    } catch (FileNotFoundException e) {
			    	e.printStackTrace();
		    }
	}

}
