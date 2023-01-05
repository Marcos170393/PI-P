package com.capa1presentacion;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Usuario {

	@NotNull
	private Long idUsuario;

	@NotNull(message = "Debes ingresar el nombre")
	@Size(min = 3, max = 20, message = "Min. 3 caracteres / Max. 20 caracteres")
	private String nombre;

	@NotNull(message = "Debes ingresar el apellido")
	@Size(min = 3, max = 20, message = "Min. 3 caracteres / Max. 20 caracteres")
	private String apellido;

	@NotNull(message = "Debes ingresar contraseña")
	@Size(min = 3, max = 20, message = "Min. 3 caracteres / Max. 20 caracteres")
	private String contrasenia;

	@NotEmpty(message = "Debes ingresar un correo electronico")
	private String mail;

	@NotNull()
	private boolean habilitado;

	@NotNull(message = "Debes ingresar un nombre de usuario")
	@Size(min = 3, max = 20, message = "Min. 3 caracteres / Max. 20 caracteres")
	private String nombreUsuario;

	@NotNull(message = "Debes ingresar la cedula")
	@Size(min = 9, max = 10, message = "Debes ingresar la cedula con punto y guion")
	private String cedula;

	@NotNull(message = "Debes ingresar el domicilio")
	@Size(min = 5, max = 30, message = "Min. 5 caracteres / Max. 30 caracteres")
	private String domicilio;

	@NotEmpty(message = "Debes seleccionar una ciudad")
	private Ciudad ciudad;

	@NotNull(message = "Debes ingresar un telefono")
	private Long telefono;

	@NotNull(message = "Debes seleccionar un rol")
	private Rol rol;

	public Usuario() {
		super();
	}

	public Usuario(String nombre, String apellido, String contrasenia, String mail, boolean habilitado,
			String nombreUsuario, Rol rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia;
		this.mail = mail;
		this.habilitado = habilitado;
		this.nombreUsuario = nombreUsuario;
		this.rol = rol;
	}

	public Usuario(String nombre, String apellido, String contrasenia, String mail, @NotNull boolean habilitado,
			String nombreUsuario, String cedula, String domicilio, Ciudad ciudad, Long telefono, Rol rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia;
		this.mail = mail;
		this.habilitado = habilitado;
		this.nombreUsuario = nombreUsuario;
		this.cedula = cedula;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
		this.telefono = telefono;
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasenia="
				+ contrasenia + ", mail=" + mail + ", habilitado=" + habilitado + ", nombreUsuario=" + nombreUsuario
				+ ", cedula=" + cedula + ", domicilio=" + domicilio + ", ciudad=" + ciudad + ", telefono=" + telefono
				+ ", rol=" + rol + "]";
	}

}
