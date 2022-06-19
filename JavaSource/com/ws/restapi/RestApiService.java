package com.ws.restapi;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.capa1presentacion.Usuario;
import com.capa2LogicaNegocio.GestionUsuarioService;

@Path("usuarios")
public class RestApiService {
	
	

	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	@GET
	@Path("buscarUsuario/{nombre_usuario}")
	@Produces("application/json")
	public Usuario obtenerEmpleado(@PathParam("nombre_usuario") String nombreUsuario){
		try {
			 Usuario usuario = gestionUsuarioService.buscarUsuarioEntityName(nombreUsuario);
			 if (usuario==null) {
				 return new Usuario();
			 }
			 return usuario;
		}catch(Exception e) {
			e.printStackTrace();
			return new Usuario(); 
		}
	
		
	}
	
	@GET
	@Path("obtenerUsuario/{id}")
	@Produces("application/json")
	public Usuario obtenerUsuario(@PathParam("id") Long id){
		try {
			 Usuario usuario = gestionUsuarioService.buscarUsuarioEntity(id);
			 if (usuario==null) {
				 return new Usuario();
			 }
			 return usuario;
		}catch(Exception e) {
			e.printStackTrace();
			return new Usuario(); 
		}
	
		
	}
	
	@GET
	@Path("listarUsuarios")
	@Produces("application/json")
	public List<Usuario> listarUsuarios(){
		

		try {
			 List<Usuario> listaUsuarios = gestionUsuarioService.seleccionarUsuarios();
			 return listaUsuarios;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return  new ArrayList<Usuario>(); 
		}
		
	}
}
