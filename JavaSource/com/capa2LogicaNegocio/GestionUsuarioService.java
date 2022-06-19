package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.capa1presentacion.Usuario;
import com.capa1presentacion.UsuarioEntity;
import com.capa3Persistencia.entities.UsuariosBean;



@Stateless
@LocalBean

public class GestionUsuarioService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsuariosBean usuarioBean = new UsuariosBean();
	
	

	public Usuario fromUsuarioEntity(UsuarioEntity e) {
		Usuario usuario=new Usuario();
		usuario.setIdUsuario(e.getIdUsuario());
		usuario.setNombre(e.getNombre());
		usuario.setApellido(e.getApellido());
		usuario.setContrasenia(e.getContrasenia());
		usuario.setMail(e.getMail());
		usuario.setHabilitado(e.isHabilitado());
		usuario.setNombreUsuario(e.getNombreUsuario());
		usuario.setRol(e.getRol());
		return usuario;
	}
	public UsuarioEntity toUsuarioEntity(Usuario e) {
		UsuarioEntity usuario= new UsuarioEntity();
		usuario.setNombre(e.getNombre());
		usuario.setApellido(e.getApellido());
		usuario.setContrasenia(e.getContrasenia());
		usuario.setMail(e.getMail());
		usuario.setHabilitado(e.isHabilitado());
		usuario.setNombreUsuario(e.getNombreUsuario());
		usuario.setRol(e.getRol());
		return usuario;
	}


	
	// servicios para capa de Presentacion

	

	

	public List<Usuario> seleccionarUsuarios() throws Exception {
		
		List<UsuarioEntity> listaUsuariosEntities= usuarioBean.buscarTodos();
		
		List<Usuario> listaUsuarios=new ArrayList<Usuario>();
		
		for (UsuarioEntity usuarioEntity : listaUsuariosEntities) {
			listaUsuarios.add(fromUsuarioEntity(usuarioEntity));
		}
		return listaUsuarios;
	}

//	public void elminarUsuario(Long id) throws Exception{
//		usuarioBean.eliminar(id);
//		
//	}
	public void elminarUsuarioEntity(Long id) throws Exception{
		usuarioBean.eliminar(id);
		
	}
	public Usuario buscarUsuarioEntity(Long id) throws Exception {
		UsuarioEntity user = usuarioBean.buscarUsuarioEntity(id);
		return fromUsuarioEntity(user);
	}
	
	public List<UsuarioEntity> buscarUsuarios() throws Exception {
		List<UsuarioEntity> usuarios = usuarioBean.buscarTodos();
		return usuarios;
	}
	
	public Usuario buscarUsuarioEntityName(String name) throws Exception {
		try{
			UsuarioEntity user = usuarioBean.buscarNombreUsuarioEntity(name);
			return fromUsuarioEntity(user);
		}catch (PersistenceException|NullPointerException e) 

		{
			Usuario usuario = new Usuario();
			return usuario;
			
		}

	}
	
	public Usuario agregarUsuario(Usuario usuario) throws Exception   {
		UsuarioEntity e = usuarioBean.crear(toUsuarioEntity(usuario));
		return fromUsuarioEntity(e);
	}

	public void actualizarUsuario(Usuario usuario) throws Exception   {
		UsuarioEntity usuarioUpdate = usuarioBean.buscarUsuarioEntity(usuario.getIdUsuario());
		usuarioUpdate.setApellido(usuario.getApellido());
		usuarioUpdate.setContrasenia(usuario.getContrasenia());
		usuarioUpdate.setHabilitado(usuario.isHabilitado());
		usuarioUpdate.setMail(usuario.getMail());
		usuarioUpdate.setNombre(usuario.getNombre());
		usuarioUpdate.setNombreUsuario(usuario.getNombreUsuario());
		usuarioUpdate.setRol(usuario.getRol());
		usuarioBean.actualizar(usuarioUpdate);
	}	
	
}
