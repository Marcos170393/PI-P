package com.ws.restapi;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.capa1presentacion.UsuarioLocal;
import com.capa2LogicaNegocio.GestionUsuarioService;

@Path("usuarios")
public class RestApiService {
	
	

	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	@GET
	@Path("buscarUsuario/{nombre_usuario}")
	@Produces("application/json")
	public UsuarioLocal obtenerEmpleado(@PathParam("nombre_usuario") String nombreUsuario){
		try {
			 UsuarioLocal usuario = gestionUsuarioService.buscarUsuarioEntityName(nombreUsuario);
			 if (usuario==null) {
				 return new UsuarioLocal();
			 }
			 return usuario;
		}catch(Exception e) {
			e.printStackTrace();
			return new UsuarioLocal(); 
		}
	
		
	}
	
	@GET
	@Path("obtenerUsuario/{id}")
	@Produces("application/json")
	public UsuarioLocal obtenerUsuario(@PathParam("id") Long id){
		try {
			 UsuarioLocal usuario = gestionUsuarioService.buscarUsuarioEntity(id);
			 if (usuario==null) {
				 return new UsuarioLocal();
			 }
			 return usuario;
		}catch(Exception e) {
			e.printStackTrace();
			return new UsuarioLocal(); 
		}
	
		
	}
	
	@GET
	@Path("listarUsuarios")
	@Produces("application/json")
	public List<UsuarioLocal> listarUsuarios(){
		

		try {
			 List<UsuarioLocal> listaUsuarios = gestionUsuarioService.seleccionarUsuarios();
			 return listaUsuarios;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return  new ArrayList<UsuarioLocal>(); 
		}
		
	}
}
