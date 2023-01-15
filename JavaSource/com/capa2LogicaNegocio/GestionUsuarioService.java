package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.xml.bind.DatatypeConverter;

import com.capa1presentacion.Rol;
import com.capa1presentacion.Usuario;
import com.capa3Persistencia.entities.UsuarioEntity;
import com.capa3Persistencia.entities.UsuariosBean;

@Stateless
@LocalBean

public class GestionUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	UsuariosBean usuarioBean = new UsuariosBean();

	@Inject
	GestionCiudadService persistenciaBean;

	public Usuario fromUsuarioEntity(UsuarioEntity e) { // Pasando los datos de la base al Frontend
		Usuario usuario = new Usuario();

		if (e.getRol().equals(Rol.ADMINISTRADOR) || e.getRol().equals(Rol.INVESTIGADOR)) {
			usuario.setIdUsuario(e.getIdUsuario());
			usuario.setNombre(e.getNombre());
			usuario.setApellido(e.getApellido());
			usuario.setContrasenia(e.getContrasenia());
			usuario.setMail(e.getMail());
			usuario.setHabilitado(e.isHabilitado());
			usuario.setNombreUsuario(e.getNombreUsuario());
			usuario.setRol(e.getRol());
			usuario.setCedula(e.getCedula());
			usuario.setCiudad(persistenciaBean.fromCiudadEntity(e.getCiudad()));
			usuario.setDomicilio(e.getDomicilio());
			usuario.setTelefono(e.getTelefono());
			return usuario;
		} else {
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
	}

	public UsuarioEntity toUsuarioEntity(Usuario e) { // Pasando los datos del Frontend a la base
		UsuarioEntity usuario = new UsuarioEntity();

		if (e.getRol().equals(Rol.ADMINISTRADOR) || e.getRol().equals(Rol.INVESTIGADOR)) {
			usuario.setNombre(e.getNombre());
			usuario.setApellido(e.getApellido());
			usuario.setContrasenia(this.Encrypt(e.getContrasenia()));
			usuario.setMail(e.getMail());
			usuario.setHabilitado(e.isHabilitado());
			usuario.setNombreUsuario(e.getNombreUsuario());
			usuario.setRol(e.getRol());
			usuario.setCedula(e.getCedula());
			usuario.setCiudad(persistenciaBean.forCiudadEntity(e.getCiudad()));
			usuario.setDomicilio(e.getDomicilio());
			usuario.setTelefono(e.getTelefono());
			return usuario;
		} else {
			usuario.setNombre(e.getNombre());
			usuario.setApellido(e.getApellido());
			usuario.setContrasenia(this.Encrypt(e.getContrasenia()));
			usuario.setMail(e.getMail());
			usuario.setHabilitado(e.isHabilitado());
			usuario.setNombreUsuario(e.getNombreUsuario());
			usuario.setRol(e.getRol());
			return usuario;
		}
	}

	//Metodo que utilizamos en GestionCasilla para agregar el Usuario a la Casilla
	public UsuarioEntity forUsuarioEntity(Usuario e) { // Pasando los datos del Frontend a la base
		UsuarioEntity usuario = new UsuarioEntity();

		if (e.getRol().equals(Rol.ADMINISTRADOR) || e.getRol().equals(Rol.INVESTIGADOR)) {
			usuario.setIdUsuario(e.getIdUsuario());
			usuario.setNombre(e.getNombre());
			usuario.setApellido(e.getApellido());
			usuario.setContrasenia(this.Encrypt(e.getContrasenia()));
			usuario.setMail(e.getMail());
			usuario.setHabilitado(e.isHabilitado());
			usuario.setNombreUsuario(e.getNombreUsuario());
			usuario.setRol(e.getRol());
			usuario.setCedula(e.getCedula());
			usuario.setCiudad(persistenciaBean.toCiudadEntity(e.getCiudad()));
			usuario.setDomicilio(e.getDomicilio());
			usuario.setTelefono(e.getTelefono());
			return usuario;
		} else {
			usuario.setIdUsuario(e.getIdUsuario());
			usuario.setNombre(e.getNombre());
			usuario.setApellido(e.getApellido());
			usuario.setContrasenia(this.Encrypt(e.getContrasenia()));
			usuario.setMail(e.getMail());
			usuario.setHabilitado(e.isHabilitado());
			usuario.setNombreUsuario(e.getNombreUsuario());
			usuario.setRol(e.getRol());
			return usuario;
		}
	}
	// servicios para capa de Presentacion

	public List<Usuario> seleccionarUsuarios() throws Exception {

		List<UsuarioEntity> listaUsuariosEntities = usuarioBean.buscarTodos();

		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		for (UsuarioEntity usuarioEntity : listaUsuariosEntities) {
			listaUsuarios.add(fromUsuarioEntity(usuarioEntity));
		}
		return listaUsuarios;
	}

//	public void elminarUsuario(Long id) throws Exception{
//		usuarioBean.eliminar(id);
//		
//	}
	public void elminarUsuarioEntity(Long id) throws Exception {
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
		try {
			UsuarioEntity user = usuarioBean.buscarNombreUsuarioEntity(name);
			return fromUsuarioEntity(user);
		} catch (PersistenceException | NullPointerException e)

		{
			Usuario usuario = new Usuario();
			return usuario;

		}

	}

	public Usuario agregarUsuario(Usuario usuario) throws Exception {
		UsuarioEntity e = usuarioBean.crear(toUsuarioEntity(usuario));
		return fromUsuarioEntity(e);
	}

	public void actualizarUsuario(Usuario usuario) throws Exception {
		UsuarioEntity usuarioUpdate = usuarioBean.buscarUsuarioEntity(usuario.getIdUsuario());
		usuarioUpdate.setApellido(usuario.getApellido());
		usuarioUpdate.setContrasenia(usuario.getContrasenia());
		usuarioUpdate.setHabilitado(usuario.isHabilitado());
		usuarioUpdate.setMail(usuario.getMail());
		usuarioUpdate.setNombre(usuario.getNombre());
		usuarioUpdate.setNombreUsuario(usuario.getNombreUsuario());
		usuarioUpdate.setRol(usuario.getRol());
		usuarioUpdate.setCedula(usuario.getCedula());
		usuarioUpdate.setCiudad(persistenciaBean.toCiudadEntity(usuario.getCiudad()));
		usuarioUpdate.setDomicilio(usuario.getDomicilio());
		usuarioUpdate.setTelefono(usuario.getTelefono());
		usuarioBean.actualizar(usuarioUpdate);

	}

	public void actualizarUsuarioAficionado(Usuario usuario) throws Exception {
		UsuarioEntity usuarioUpdate = usuarioBean.buscarUsuarioEntity(usuario.getIdUsuario());
		usuarioUpdate.setApellido(usuario.getApellido());
		usuarioUpdate.setContrasenia(usuario.getContrasenia());
		usuarioUpdate.setHabilitado(usuario.isHabilitado());
		usuarioUpdate.setMail(usuario.getMail());
		usuarioUpdate.setNombre(usuario.getNombre());
		usuarioUpdate.setNombreUsuario(usuario.getNombreUsuario());
		usuarioUpdate.setRol(usuario.getRol());
		usuarioUpdate.setCedula(null);
		usuarioUpdate.setCiudad(null);
		usuarioUpdate.setDomicilio(null);
		usuarioUpdate.setTelefono(null);
		usuarioBean.actualizar(usuarioUpdate);
	}

	public void actualizarPerfil(Usuario usuario) throws Exception {
		UsuarioEntity usuarioUpdate = usuarioBean.buscarUsuarioEntity(usuario.getIdUsuario());
		usuarioUpdate.setApellido(usuario.getApellido());
		usuarioUpdate.setContrasenia(this.Encrypt(usuario.getContrasenia()));
		usuarioUpdate.setHabilitado(usuario.isHabilitado());
		usuarioUpdate.setMail(usuario.getMail());
		usuarioUpdate.setNombre(usuario.getNombre());
		usuarioUpdate.setNombreUsuario(usuario.getNombreUsuario());
		usuarioUpdate.setRol(usuario.getRol());
		usuarioBean.actualizar(usuarioUpdate);
	}

	public String Encrypt(String pInput) {
		String encryptedtext = null;
		try {

			String Input = pInput;
			String key = "Bar12345Bar12345Bar12345Bar12345";

			SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(Input.getBytes("UTF-8"));
			// encryptedtext = new String(encrypted);
			encryptedtext = DatatypeConverter.printBase64Binary(encrypted);
			System.err.println("encrypted:" + encryptedtext);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptedtext;
	}

	public String Decrypt(String pInput) {
		String decrypted = null;
		byte[] encrypted;
		try {

			String Input = pInput;

			String key = "Bar12345Bar12345Bar12345Bar12345";

			SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			encrypted = DatatypeConverter.parseBase64Binary(Input);
			decrypted = new String(cipher.doFinal(encrypted), "UTF-8");
			System.err.println("decrypted: " + decrypted);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return decrypted;
	}
}
