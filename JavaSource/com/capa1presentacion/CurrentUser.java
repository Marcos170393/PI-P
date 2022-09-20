package com.capa1presentacion;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value="currentUser")
@SessionScoped	
public class CurrentUser implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static UsuarioLocal usuario = new UsuarioLocal();
	
	private Long idUsuario = usuario.getIdUsuario();
	
	private boolean habilitado = usuario.isHabilitado();
	
	private CurrentUser() {
		//TODO Auto-generated constructor stub
	}

	public static UsuarioLocal getUsuario() {
		return usuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public static void setUsuario(UsuarioLocal usuario) {
		CurrentUser.usuario = usuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
}
