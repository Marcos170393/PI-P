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
import com.capa1presentacion.Registro;
import com.capa1presentacion.Rol;
import com.capa1presentacion.TipoDato;
import com.capa1presentacion.TipoMedicion;
import com.capa1presentacion.Usuario;
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
		UsuariosBean bean = new UsuariosBean();
		try {
			Ciudad c = new Ciudad("Durazno");
			Ciudad c1 = new Ciudad("Artigas");
			Ciudad c2 = new Ciudad("Canelones");
			Ciudad c3 = new Ciudad("Colonia");
			Ciudad c4 = new Ciudad("Flores");
			Ciudad c5 = new Ciudad("Florida");
			Ciudad c6 = new Ciudad("Lavalleja");
			Ciudad c7 = new Ciudad("Maldonado");
			Ciudad c8 = new Ciudad("Montevideo");
			Ciudad c9 = new Ciudad("Paysandu");
			Ciudad c10 = new Ciudad("Rio Negro");
			Ciudad c11 = new Ciudad("Rivera");
			Ciudad c12 = new Ciudad("Rocha");
			Ciudad c13 = new Ciudad("Salto");
			Ciudad c14 = new Ciudad("San Jose");
			Ciudad c15 = new Ciudad("Soriano");
			Ciudad c16 = new Ciudad("Tacuarembo");
			Ciudad c17 = new Ciudad("Treinta y Tres");
			Ciudad c18 = new Ciudad("Fray Bentos");
			
			c = gestionCiudadService.agregarCiudad(c);
			c1 = gestionCiudadService.agregarCiudad(c1);
			c2 = gestionCiudadService.agregarCiudad(c2);
			c3 = gestionCiudadService.agregarCiudad(c3);
			c4 = gestionCiudadService.agregarCiudad(c4);
			c5 = gestionCiudadService.agregarCiudad(c5);
			c6 = gestionCiudadService.agregarCiudad(c6);
			c7 = gestionCiudadService.agregarCiudad(c7);
			c8 = gestionCiudadService.agregarCiudad(c8);
			c9 = gestionCiudadService.agregarCiudad(c9);
			c10 = gestionCiudadService.agregarCiudad(c10);
			c11 = gestionCiudadService.agregarCiudad(c11);
			c12 = gestionCiudadService.agregarCiudad(c12);
			c13 = gestionCiudadService.agregarCiudad(c13);
			c14 = gestionCiudadService.agregarCiudad(c14);
			c15 = gestionCiudadService.agregarCiudad(c15);
			c16 = gestionCiudadService.agregarCiudad(c16);
			c17 = gestionCiudadService.agregarCiudad(c17);
			c18 = gestionCiudadService.agregarCiudad(c18);
			out.println("Se han creado las ciudades con exito");

			Usuario u = new Usuario("Admin", "Administrador", "PasswordAD1", "administrador@gmail.com", true, "administrador", "12345678",
					"Durazno 123", c, Long.valueOf(92723073), Rol.ADMINISTRADOR);

			u = gestionUsuarioService.agregarUsuario(u);
			out.println("Se ha creado el usuario con exito " + u);

			Usuario u2 = new Usuario("Invest", "Investigador", "PasswordINV1", "investigador@gmail.com", true,
					"investigador", "12345867", "Canelones 132", c1, Long.valueOf(91885647), Rol.INVESTIGADOR);
			u2 = gestionUsuarioService.agregarUsuario(u2);
			out.println("Usuario agregado" + u2);

			Usuario u3 = new Usuario("Afic", "Aficionado", "PasswordAFIC1", "aficionadoUser@gmail.com", true,
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

				/*
				 * Parametro p1 = gestionParametroService.buscarParametroEntity(1L); TipoDato t1
				 * = gestionTipoDatoService.buscarTdatoEntity(1L); Usuario use =
				 * gestionUsuarioService.buscarUsuarioEntity(user.getIdUsuario());
				 */

				Casilla casilla = new Casilla("Creando casilla", "Casilla", true, param1, dato1, u);
				casilla = gestionCasillaService.agregarCasilla(casilla);
				out.println("Casilla creada.. " + casilla);

				Casilla casilla2 = new Casilla("Creando casilla dos", "Casilla dos", true, param2, dato2, u);
				casilla2 = gestionCasillaService.agregarCasilla(casilla2);
				out.println("Casilla creada.. " + casilla2);

				List<CiudadEntity> ciudades = new ArrayList<>();
				ciudades.add(gestionCiudadService.forCiudadEntity(c2));

				Departamento dpto = new Departamento("Durazno");
				Departamento dpto2 = new Departamento("Artigas");
				Departamento dpto3 = new Departamento("Canelones");
				Departamento dpto4 = new Departamento("Colonia");
				Departamento dpto5 = new Departamento("Flores");
				Departamento dpto6 = new Departamento("Florida");
				Departamento dpto7 = new Departamento("Lavalleja");
				Departamento dpto8 = new Departamento("Maldonado");
				Departamento dpto9 = new Departamento("Montevideo");
				Departamento dpto10 = new Departamento("Paysandu");
				Departamento dpto11 = new Departamento("Rio Negro");
				Departamento dpto12 = new Departamento("Rivera");
				Departamento dpto13 = new Departamento("Rocha");
				Departamento dpto14 = new Departamento("Salto");
				Departamento dpto15 = new Departamento("San Jose");
				Departamento dpto16 = new Departamento("Soriano");
				Departamento dpto17 = new Departamento("Tacuarembo");
				Departamento dpto18 = new Departamento("Treinta y Tres");
				Departamento dpto19 = new Departamento("Fray Bentos");
				dpto.setCiudades(ciudades);
				
				dpto = gestionDptoService.agregarDepartamento(dpto);
				dpto2 = gestionDptoService.agregarDepartamento(dpto2);
				dpto3 = gestionDptoService.agregarDepartamento(dpto3);
				dpto4 = gestionDptoService.agregarDepartamento(dpto4);
				dpto5 = gestionDptoService.agregarDepartamento(dpto5);
				dpto6 = gestionDptoService.agregarDepartamento(dpto6);
				dpto7 = gestionDptoService.agregarDepartamento(dpto7);
				dpto8 = gestionDptoService.agregarDepartamento(dpto8);
				dpto9 = gestionDptoService.agregarDepartamento(dpto9);
				dpto10 = gestionDptoService.agregarDepartamento(dpto10);
				dpto11 = gestionDptoService.agregarDepartamento(dpto11);
				dpto12 = gestionDptoService.agregarDepartamento(dpto12);
				dpto13 = gestionDptoService.agregarDepartamento(dpto13);
				dpto14 = gestionDptoService.agregarDepartamento(dpto14);
				dpto15 = gestionDptoService.agregarDepartamento(dpto15);
				dpto16 = gestionDptoService.agregarDepartamento(dpto16);
				dpto17 = gestionDptoService.agregarDepartamento(dpto17);
				dpto18 = gestionDptoService.agregarDepartamento(dpto18);
				dpto19 = gestionDptoService.agregarDepartamento(dpto19);
				out.println("Departamento creados");

				List<CasillaEntity> casillas = new ArrayList<>();
				casillas.add(gestionCasillaService.forCasillaEntity(casilla));
				casillas.add(gestionCasillaService.forCasillaEntity(casilla2));

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
