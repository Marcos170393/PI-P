package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.capa1presentacion.Rol;
import com.capa1presentacion.TipoMedicion;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the FORMULARIOS database table.
 * 
 */
@Entity
@Table(name = "FORMULARIOS")
@NamedQuery(name = "FormularioEntity.findAll", query = "SELECT f FROM FormularioEntity f")
public class FormularioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FORMULARIOS_IDFORMULARIO_GENERATOR", sequenceName = "SEQ_FORM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMULARIOS_IDFORMULARIO_GENERATOR")
	@Column(name = "ID_FORMULARIO")
	private long idFormulario;

	@Column(nullable = false, columnDefinition = "varchar2(20)")
	private String nombre;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_HORA")
	private Date fechaHora;

	@Enumerated(EnumType.STRING)
	private TipoMedicion metodoMedicion;

	@Column(nullable = true, columnDefinition = "varchar2(100)")
	private String resumen;

	// bi-directional many-to-many association to Casilla
	@ManyToMany
	@JoinTable(name = "CONTIENEN", joinColumns = { @JoinColumn(name = "ID_FORMULARIO") }, inverseJoinColumns = {
			@JoinColumn(name = "ID_CASILLA") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CasillaEntity> casillas;

	@ManyToMany
	@JoinTable(name = "CONTIENEN_OBLIGATORIAS", joinColumns = {
			@JoinColumn(name = "ID_FORMULARIO") }, inverseJoinColumns = { @JoinColumn(name = "ID_CASILLA") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CasillaEntity> casillasObligatorias;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private UsuarioEntity usuario;

	private boolean isDisponible;

	public FormularioEntity() {
	}

	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO")
	private DepartamentoEntity departamento;

	public FormularioEntity(String nombre, Date fechaHora, TipoMedicion metodoMedicion, String resumen,
			List<CasillaEntity> casillas, UsuarioEntity usuario, DepartamentoEntity depar) {
		super();
		this.nombre = nombre;
		this.fechaHora = fechaHora;
		this.metodoMedicion = metodoMedicion;
		this.resumen = resumen;
		this.casillas = casillas;
		this.usuario = usuario;
		this.isDisponible = true;
		this.departamento = depar;
	}

	public long getIdFormulario() {
		return this.idFormulario;
	}

	public void setIdFormulario(long idFormulario) {
		this.idFormulario = idFormulario;
	}

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public TipoMedicion getMetodoMedicion() {
		return this.metodoMedicion;
	}

	public void setMetodoMedicion(TipoMedicion metodoMedicion) {
		this.metodoMedicion = metodoMedicion;
	}

	public String getResumen() {
		return this.resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public List<CasillaEntity> getCasillas() {
		return this.casillas;
	}

	public void setCasillas(List<CasillaEntity> casillas) {
		this.casillas = casillas;
	}

	public UsuarioEntity getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isDisponible() {
		return isDisponible;
	}

	public void setDisponible(boolean isDisponible) {
		this.isDisponible = isDisponible;
	}

	public DepartamentoEntity getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoEntity departamento) {
		this.departamento = departamento;
	}

	public List<CasillaEntity> getCasillasObligatorias() {
		return casillasObligatorias;
	}

	public void setCasillasObligatorias(List<CasillaEntity> casillasObligatorias) {
		this.casillasObligatorias = casillasObligatorias;
	}

	@Override
	public String toString() {
		return "Formulario [idFormulario=" + idFormulario + ", nombre=" + nombre + ", fechaHora=" + fechaHora
				+ ", metodoMedicion=" + metodoMedicion + ", resumen=" + resumen + ", casillas=" + casillas
				+ ", usuario=" + usuario + ", isDisponible=" + isDisponible + ", departamento=" + departamento + "]";
	}

}