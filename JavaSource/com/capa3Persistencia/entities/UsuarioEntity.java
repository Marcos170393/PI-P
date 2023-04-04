package com.capa3Persistencia.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.capa1presentacion.Rol;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name = "USUARIOS")
@NamedQuery(name = "UsuarioEntity.findAll", query = "SELECT u FROM UsuarioEntity u")
//@NamedQuery(name="UsuarioEntity.findUser", query="SELECT u FROM UsuariosEntity u WHERE nombre_usuario = :nombreUsuario")
public class UsuarioEntity implements Serializable {
	/********************************************************************
	 * ATRIBUTOS
	 ****/
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIOS_IDUSUARIO_GENERATOR", sequenceName="SEQ_USUARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_IDUSUARIO_GENERATOR")
	@Column(name="ID_USUARIO")
	private Long idUsuario;
	private String apellido;
	private String contrasenia;
	private String mail;
	private String nombre;
	private boolean habilitado;
	private boolean disponible;
	@Column(unique = true, name = "NOMBRE_USUARIO")
	private String nombreUsuario;
	@Enumerated(EnumType.STRING)
	private Rol rol;
	private String cedula;
	private String domicilio;
	
	@ManyToOne()
	private CiudadEntity ciudad;
	
	private Long telefono;
	
	


	/********************************************************************
	 * CONSTRUCTORES
	 ****/
	public UsuarioEntity() {
	}

	public UsuarioEntity(String apellido, String contrasenia, String mail, String nombre, String nombreUsuario,
			Rol rol) {
		super();
		this.apellido = apellido;
		this.contrasenia = contrasenia;
		this.mail = mail;
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.rol = rol;
		this.habilitado = true;
		this.disponible = true;
	}

	/********************************************************************
	 * GETTERS
	 ****/
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

	/********************************************************************
	 * SETTERS
	 ****/
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

	public CiudadEntity getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadEntity ciudad) {
		this.ciudad = ciudad;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}



	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	/********************************************************************
	 * TOSTRING
	 ****/
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", apellido=" + apellido + ", contrasenia=" + contrasenia + ", mail="
				+ mail + ", nombre=" + nombre + ", habilitado=" + habilitado + ", nombreUsuario=" + nombreUsuario
				+ ", rol=" + rol + "]";
	}
}