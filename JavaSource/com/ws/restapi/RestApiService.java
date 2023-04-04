package com.ws.restapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.capa1presentacion.Casilla;
import com.capa1presentacion.CurrentUser;
import com.capa1presentacion.Formulario;
import com.capa1presentacion.Registro;
import com.capa1presentacion.Usuario;
import com.capa2LogicaNegocio.GestionCasillaService;
import com.capa2LogicaNegocio.GestionFormularioService;
import com.capa2LogicaNegocio.GestionRegistroService;
import com.capa2LogicaNegocio.GestionUsuarioService;

@Path("usuarios")
public class RestApiService {

	@EJB
	GestionUsuarioService gestionUsuarioService;

	@EJB
	GestionFormularioService gestionFormularioService;
	
	@EJB
	GestionCasillaService gestionCasillaService;

	@EJB
	GestionRegistroService gestionRegistroService;

	@GET
	@Path("buscarUsuario/{nombre_usuario}")
	@Produces("application/json")
	public Usuario obtenerEmpleado(@PathParam("nombre_usuario") String nombreUsuario) {
		try {
			Usuario usuario = gestionUsuarioService.buscarUsuarioEntityName(nombreUsuario);
			if (usuario == null) {
				return new Usuario();
			}
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return new Usuario();
		}

	}

	@GET
	@Path("/login{nombre_usuario}-{password}")
	@Produces("application/json")
	public Usuario login(@PathParam("nombre_usuario") String nombre_usuario, @PathParam("password") String password) {
		try {
			Usuario usuario = (Usuario) gestionUsuarioService.buscarUsuarioEntityName(nombre_usuario);
			if (usuario.getNombreUsuario().equals(nombre_usuario)
					&& gestionUsuarioService.Decrypt(usuario.getContrasenia()).equals(password)) {

				return usuario;

			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@GET
	@Path("/listarFormularios")
	@Produces("application/json")
	public List<Formulario> listarFormularios() {

		try {
			List<Formulario> listaFormularios = gestionFormularioService.seleccionarFormularios();
			return listaFormularios;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Formulario>();
		}

	}

	@GET
	@Path("obtenerUsuario/{id}")
	@Produces("application/json")
	public Usuario obtenerUsuario(@PathParam("id") Long id) {
		try {
			Usuario usuario = gestionUsuarioService.buscarUsuarioEntity(id);
			if (usuario == null) {
				return new Usuario();
			}
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return new Usuario();
		}

	}

	@GET
	@Path("listarUsuarios")
	@Produces("application/json")
	public List<Usuario> listarUsuarios() {

		try {
			List<Usuario> listaUsuarios = gestionUsuarioService.seleccionarUsuarios();
			return listaUsuarios;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Usuario>();
		}

	}

	@GET
	@Path("buscarFormulario/{nombre}")
	@Produces("application/json")
	public Formulario obtenerFormulario(@PathParam("nombre") String nombre) {
		try {
			Formulario form = gestionFormularioService.buscarFormularioEntityName(nombre);
			if (form == null) {
				return new Formulario();
			}
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			return new Formulario();
		}

	}
	
	@GET
	@Path("buscarFormularioId/{id}")
	@Produces("application/json")
	public Formulario obtenerFormularioId(@PathParam("id") Long id) {
		try {
			Formulario form = gestionFormularioService.buscarFormularioEntityId(id);
			if (form == null) {
				return new Formulario();
			}
			return form;
		} catch (Exception e) {
			e.printStackTrace();
			return new Formulario();
		}

	}
	
	@GET
	@Path("buscarCasilla/{nombre}")
	@Produces("application/json")
	public Casilla obtenerCasilla(@PathParam("nombre") String nombre) {
		try {
			Casilla c = gestionCasillaService.buscarCasillaEntityName(nombre);
			if (c == null) {
				return new Casilla();
			}
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return new Casilla();
		}

	}


		@POST
		@Path("crear")
		@Produces("application/json")
		public Registro crear(Registro registro) {
			try {
				
				registro.setFecha(new Date());
				gestionRegistroService.agregarRegistro(registro);
				return registro;
			} catch (Exception e) {
				e.printStackTrace();
				return new Registro();
			}
		
	
	}
		
		@GET	
		@Path("buscarRegistros/{id_formulario}")
		@Produces("application/json")
		public List<Registro> buscarRegistros(@PathParam("id_formulario") Long id_formulario) {
			try {
				List<Registro> r = new ArrayList<>();
				r = gestionRegistroService.seleccionarRegistrosIdFormulario(id_formulario);
				return r;
			} catch (Exception e) {
				return new ArrayList<Registro>();
				
			}
		
	
	}

}
