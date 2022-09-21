package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.capa1presentacion.Rol;
import com.capa1presentacion.UsuarioLocal;
import com.capa3Persistencia.entities.Usuario;
import com.capa3Persistencia.entities.UsuariosBean;


@Stateless
@LocalBean
public class GestionUsuarioService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsuariosBean usuarioBean = new UsuariosBean();
	
	
	//TODO ajustar asignacion de roles
	public UsuarioLocal fromUsuarioEntity(Usuario e) {
		UsuarioLocal usuario=new UsuarioLocal();
		usuario.setIdUsuario(e.getIdUsuario());
		usuario.setNombre(e.getNombre());
		usuario.setApellido(e.getApellido());
		usuario.setContrasenia(e.getContrasenia());
		usuario.setMail(e.getMail());
		usuario.setHabilitado(e.isHabilitado());
		usuario.setNombreUsuario(e.getNombreUsuario());
		usuario.setRol(Rol.ADMINISTRADOR);
		return usuario;
	}
	//TODO ajustar asignacion de roles
	public Usuario toUsuarioEntity(UsuarioLocal e) {
		Usuario usuario= new Usuario();
		usuario.setNombre(e.getNombre());
		usuario.setApellido(e.getApellido());
		usuario.setContrasenia(e.getContrasenia());
		usuario.setMail(e.getMail());
		usuario.setHabilitado(e.isHabilitado());
		usuario.setNombreUsuario(e.getNombreUsuario());
		return usuario;
	}


	
	// servicios para capa de Presentacion

	

	

	public List<UsuarioLocal> seleccionarUsuarios() throws Exception {
		
		List<Usuario> listaUsuariosEntities= usuarioBean.buscarTodos();
		
		List<UsuarioLocal> listaUsuarios=new ArrayList<UsuarioLocal>();
		
		for (Usuario usuarioEntity : listaUsuariosEntities) {
			listaUsuarios.add(fromUsuarioEntity(usuarioEntity));
		}
		return listaUsuarios;
	}

	public void elminarUsuario(Long id) throws Exception{
		usuarioBean.eliminar(id);
		
	}
	public void elminarUsuarioEntity(Long id) throws Exception{
		usuarioBean.eliminar(id);
		
	}
	public UsuarioLocal buscarUsuarioEntity(Long id) throws Exception {
		Usuario user = usuarioBean.buscarUsuarioEntity(id);
		return fromUsuarioEntity(user);
	}
	
	public List<Usuario> buscarUsuarios() throws Exception {
		List<Usuario> usuarios = usuarioBean.buscarTodos();
		return usuarios;
	}
	
	public UsuarioLocal buscarUsuarioEntityName(String name) throws Exception {
		try{
			Usuario user = usuarioBean.buscarNombreUsuarioEntity(name);
			return fromUsuarioEntity(user);
		}catch (PersistenceException|NullPointerException e) 

		{
			UsuarioLocal usuario = new UsuarioLocal();
			return usuario;
			
		}

	}
	
	public UsuarioLocal agregarUsuario(UsuarioLocal usuario) throws Exception   {
		Usuario e = usuarioBean.crear(toUsuarioEntity(usuario));
		return fromUsuarioEntity(e);
	}

	public void actualizarUsuario(UsuarioLocal usuario) throws Exception   {
		Usuario usuarioUpdate = usuarioBean.buscarUsuarioEntity(usuario.getIdUsuario());
		usuarioUpdate.setApellido(usuario.getApellido());
		usuarioUpdate.setContrasenia(usuario.getContrasenia());
		usuarioUpdate.setHabilitado(usuario.isHabilitado());
		usuarioUpdate.setMail(usuario.getMail());
		usuarioUpdate.setNombre(usuario.getNombre());
		usuarioUpdate.setNombreUsuario(usuario.getNombreUsuario());
//		usuarioUpdate.setRol(usuario.getRol());
		usuarioBean.actualizar(usuarioUpdate);
	}	
	
}
