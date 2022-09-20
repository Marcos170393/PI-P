package com.capa3Persistencia.entities;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import java.math.BigDecimal;
import java.util.List;
/**
* The persistent class for the USUARIOS database table.
* 
*/
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	/********************************************************************ATRIBUTOS****/
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIOS_IDUSUARIO_GENERATOR", sequenceName="SEQ_USUARIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_IDUSUARIO_GENERATOR")
	@Column(name="ID_USUARIO")
	private long idUsuario;

	@Column(nullable = false, columnDefinition="varchar2(20)")
	private String apellido;

	@Column(nullable = false, columnDefinition="varchar2(25)")
	private String contrasenia;
	
	@Column(nullable = true, columnDefinition="varchar2(50)")  //Cambio de nullable false a true
	private String mail;

	@Column(nullable = false, columnDefinition="varchar2(20)")
	private String nombre;

	@Column(nullable = false)
	private boolean habilitado;
	
	@Column(name="NOMBRE_USUARIO",nullable = false, unique = true ,columnDefinition="varchar2(25)")
	private String nombreUsuario;

	//bi-directional many-to-one association to Administrador
//	@OneToOne(mappedBy="usuario")
//	@LazyCollection(LazyCollectionOption.FALSE)
//	private Administrador administrador;

	//bi-directional many-to-one association to Aficionado
//	@OneToOne(mappedBy="usuario")
//	@LazyCollection(LazyCollectionOption.FALSE)
//	private Aficionado aficionado;

	//bi-directional many-to-one association to Casilla
//	@OneToMany(mappedBy="usuario")
//	@LazyCollection(LazyCollectionOption.FALSE)
//	private List<Casilla> casillas;

	//bi-directional many-to-one association to Estacion
//	@OneToMany(mappedBy="usuario")
//	@LazyCollection(LazyCollectionOption.FALSE)
//	private List<Estacion> estaciones;

	//bi-directional many-to-one association to Formulario
//	@OneToMany(mappedBy="usuario")
//	@LazyCollection(LazyCollectionOption.FALSE)
//	private List<Formulario> formularios;

	//bi-directional many-to-one association to Investigador
//	@OneToOne(mappedBy="usuario")
//	@LazyCollection(LazyCollectionOption.FALSE)
//	private Investigador investigador;

//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(mappedBy = "usuario")
//	private List<Registro> registros;
	/********************************************************************CONSTRUCTORES****/
	public Usuario() {
	}
	public Usuario(String apellido, String contrasenia, String mail, String nombre, String nombreUsuario) {
		super();
		this.apellido = apellido;
		this.contrasenia = contrasenia;
		this.mail = mail;
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
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
//	public Administrador getAdministrador() {
//		return this.administrador;
//	}
//	public Aficionado getAficionado() {
//		return this.aficionado;
//	}
//	public List<Casilla> getCasillas() {
//		return this.casillas;
//	}
//	public List<Estacion> getEstaciones() {
//		return this.estaciones;
//	}
//	public List<Formulario> getFormularios() {
//		return this.formularios;
//	}
//	public Investigador getInvestigador() {
//		return this.investigador;
//	}
//	public List<Registro> getRegistros() {
//		return registros;
//	}
//	public void setRegistros(List<Registro> registros) {
//		this.registros = registros;
//	}
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
//	public void setAdministradores(Administrador administradores) {
//		this.administrador = administradores;
//	}
//	public void setAficionados(Aficionado aficionados) {
//		this.aficionado = aficionados;
//	}
//	public void setCasillas(List<Casilla> casillas) {
//		this.casillas = casillas;
//	}
//	public void setEstaciones(List<Estacion> estaciones) {
//		this.estaciones = estaciones;
//	}
//	public void setFormularios(List<Formulario> formularios) {
//		this.formularios = formularios;
//	}
//	public void setInvestigadores(Investigador investigadores) {
//		this.investigador = investigadores;
//	}
//	/********************************************************************METODOS****/
//	/*AGREGAR CASILLA*/
//	public Casilla addCasilla(Casilla casilla) {
//		getCasillas().add(casilla);
//		casilla.setUsuario(this);
//
//		return casilla;
//	}
//	/*ELIMINAR CASILLA*/
//	public Casilla removeCasilla(Casilla casilla) {
//		getCasillas().remove(casilla);
//		casilla.setUsuario(null);
//
//		return casilla;
//	}
//	/*AGREGAR ESTACION*/
//	public Estacion addEstacione(Estacion estacion) {
//		getEstaciones().add(estacion);
//		estacion.setUsuario(this);
//
//		return estacion;
//	}
//	/*ELIMINAR ESTACION*/
//	public Estacion removeEstacione(Estacion estacion) {
//		getEstaciones().remove(estacion);
//		estacion.setUsuario(null);
//
//		return estacion;
//	}
//	/*AGREGAR FORMULARIO*/
//	public Formulario addFormulario(Formulario formulario) {
//		getFormularios().add(formulario);
//		formulario.setUsuario(this);
//
//		return formulario;
//	}
//	/*ELIMINAR FORMULARIO*/
//	public Formulario removeFormulario(Formulario formulario) {
//		getFormularios().remove(formulario);
//		formulario.setUsuario(null);
//
//		return formulario;
//	}
//	/********************************************************************TOSTRING****/
//	@Override
//	public String toString() {
//		return "Usuario [idUsuario=" + idUsuario + ", apellido=" + apellido + ", contrasenia=" + contrasenia + ", mail="
//				+ mail + ", nombre=" + nombre + ", habilitado=" + habilitado + ", nombreUsuario=" + nombreUsuario
//				+ ", administrador=" + administrador + ", aficionado=" + aficionado + ", casillas=" + casillas
//				+ ", estaciones=" + estaciones + ", formularios=" + formularios + ", investigador=" + investigador
//				+ "]";
//	}
}