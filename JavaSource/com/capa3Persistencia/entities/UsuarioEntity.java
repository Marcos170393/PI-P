package com.capa3Persistencia.entities;
import java.io.Serializable;
import javax.persistence.*;

import com.capa1presentacion.Rol;
/**
* The persistent class for the USUARIOS database table.
* 
*/
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="UsuarioEntity.findAll", query="SELECT u FROM UsuarioEntity u")
//@NamedQuery(name="UsuarioEntity.findUser", query="SELECT u FROM UsuariosEntity u WHERE nombre_usuario = :nombreUsuario")
public class UsuarioEntity implements Serializable {
	/********************************************************************ATRIBUTOS****/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDUSUARIO")
	private long idUsuario;
	
	private String apellido;
	
	private String contrasenia;
	
	@Column(unique = true)
	private String mail;
	
	private String nombre;
	
	private boolean habilitado;
	
	@Column(unique=true, name="NOMBRE_USUARIO")
	private String nombreUsuario;
	
	@Enumerated(EnumType.STRING)
	private Rol rol;
	
	/********************************************************************CONSTRUCTORES****/
	public UsuarioEntity() {
	}
	public UsuarioEntity(String apellido, String contrasenia, String mail, String nombre, String nombreUsuario, Rol rol) {
		super();
		this.apellido = apellido;
		this.contrasenia = contrasenia;
		this.mail = mail;
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.rol = rol;
		this.habilitado = true;
	}
	/********************************************************************GETTERS****/
	public long getIdUsuario() {
		return this.idUsuario;
	}
	public String getApellido() {
		return this.apellido;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public String getContrasenia() {
		return this.contrasenia;
	}
	public String getMail() {
		return this.mail;
	}
	public String getNombre() {
		return this.nombre;
	}
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}
	
		
	public Rol getRol() {
		return rol;
	}
	
	/********************************************************************SETTERS****/
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/********************************************************************TOSTRING****/
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", apellido=" + apellido + ", contrasenia=" + contrasenia + ", mail="
				+ mail + ", nombre=" + nombre + ", habilitado=" + habilitado + ", nombreUsuario=" + nombreUsuario + ", rol=" + rol +"]";
	}
}