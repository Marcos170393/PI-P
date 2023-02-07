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

@Path("registros")
public class RestApiServiceRegistroCA {

	@EJB
	GestionRegistroService gestionRegistroService;

	@EJB
	GestionFormularioService gestionFormularioService;

	@EJB
	GestionCasillaService gestionCasillaService;

	@EJB
	GestionUsuarioService gestionUsuarioService;

	@POST
	@Path("crearRegistro/{idFormulario}/{idCasilla}/{nombre}/{valor}")
	@Produces("application/json")
	public Registro crearRegistro(@PathParam("idFormulario")Long idFormulario, @PathParam("idCasilla") Long idCasilla, @PathParam("nombre") String nombre, 
			@PathParam("valor") Long valor){
		try {
		Registro registro = new Registro();
		Formulario form = gestionFormularioService.buscarFormularioEntityId(idFormulario);
		Casilla c = gestionCasillaService.buscarCasillaEntityId(idCasilla);
		Usuario user =	gestionUsuarioService.buscarUsuarioEntityName(nombre);
			
			registro.setFecha(new Date());	
			registro.setUsuario(user);
			registro.setFormulario(form);
			registro.setCasilla(c);
			registro.setUk_registro(1);
			registro.setValor(valor);
			registro = gestionRegistroService.agregarRegistro(registro);
			 if (registro==null) {
				 return new Registro();
			 }
			 return registro;
		}catch(Exception e) {
			e.printStackTrace();
			return new Registro(); 
		}
	
		
	}

	@GET
	@Path("obtenerRegistro/{id}")
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
	@Path("listarRegistros")
	@Produces("application/json")
	public List<Registro> listarRegistros() {

		try {
			List<Registro> listaRegistros = gestionRegistroService.seleccionarRegistros();
			return listaRegistros;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Registro>();
		}

	}
}
