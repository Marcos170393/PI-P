package com.capa3Persistencia.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
/**
 * The persistent class for the ADMINISTRADORES database table.
 * 
 */
@Entity
@Table(name="ADMINISTRADORES")
@NamedQuery(name="Administrador.findAll", query="SELECT a FROM Administrador a")
public class Administrador implements Serializable {
	/********************************************************************ATRIBUTOS****/
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ADMINISTRADORES_IDADMINISTRADOR_GENERATOR", sequenceName="SEQ_ADMIN")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADMINISTRADORES_IDADMINISTRADOR_GENERATOR")
	@Column(name="ID_ADMINISTRADOR")
	private long idAdministrador;

	@Column(nullable = false,columnDefinition="varchar2(50)")
	private String domicilio;

	private BigDecimal telefono;
	
	@Column(nullable = false,unique = true)
	private int cedula;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="CIUDAD")
	private Ciudad ciudad;

	//bi-directional one-to-one association to Usuario
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;
	/********************************************************************CONSTRUCTORES****/
	public Administrador() {
	}
	public Administrador(String domicilio, BigDecimal telefono, Ciudad ciudad, Usuario usuario, int ci) {
		super();
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.usuario = usuario;
		this.cedula = ci;
	}
	/********************************************************************GETTERS****/
	public long getIdAdministrador() {
		return this.idAdministrador;
	}
	public String getDomicilio() {
		return this.domicilio;
	}
	public BigDecimal getTelefono() {
		return this.telefono;
	}
	public Ciudad getCiudad() {
		return this.ciudad;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	public int getCedula() {
		return cedula;
	}
	/********************************************************************SETTERS****/
	public void setIdAdministrador(long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}
	public void setCiudade(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	/********************************************************************TOSTRING****/
	@Override
	public String toString() {
		return "Administrador [idAdministrador=" + idAdministrador + ", domicilio=" + domicilio + ", telefono="
				+ telefono + ", ciudad=" + ciudad + ", usuario=" + usuario + "]";
	}
}