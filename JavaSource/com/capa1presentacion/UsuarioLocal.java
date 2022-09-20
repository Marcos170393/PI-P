package com.capa1presentacion;


import javax.validation.constraints.NotNull;

public class UsuarioLocal {

	@NotNull
	private Long idUsuario;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String apellido;
	
	@NotNull
	private String contrasenia;
	
	@NotNull
	private String mail;
	
	@NotNull
	private boolean habilitado;
	
	@NotNull
	private String nombreUsuario;
	
	@NotNull
	private Rol rol;

	public UsuarioLocal() {
		super();
	}

	public UsuarioLocal( String nombre, String apellido, String contrasenia,String mail,boolean habilitado, String nombreUsuario,Rol rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia;
		this.mail = mail;
		this.habilitado = habilitado;
		this.nombreUsuario = nombreUsuario;
		this.rol = rol;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public String getMail() {
		return mail;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasenia="
				+ contrasenia + ", mail=" + mail + ", habilitado=" + habilitado + ", nombreUsuario=" + nombreUsuario
				+ ", Rol =" + rol + "]";
	}
	
	




}
