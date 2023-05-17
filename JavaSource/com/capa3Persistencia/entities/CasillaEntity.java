package com.capa3Persistencia.entities;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.persistence.*;
import javax.validation.Constraint;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.capa1presentacion.Usuario;

import java.util.List;

/**
 * The persistent class for the CASILLAS database table.
 * 
 */
@Entity
@Table(name = "CASILLAS")
@NamedQuery(name = "Casilla.findAll", query = "SELECT c FROM CasillaEntity c")
public class CasillaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CASILLAS_IDCASILLA_GENERATOR", sequenceName = "SEQ_CASILLA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CASILLAS_IDCASILLA_GENERATOR")
	@Column(name = "ID_CASILLA")
	private long idCasilla;

	@Column(nullable = true, columnDefinition = "varchar2(100)")
	private String descripcion;

	@Column(nullable = false, columnDefinition = "varchar2(30)")
	private String nombre;

	private boolean disponible;

	// bi-directional many-to-one association to Parametro
	@ManyToOne
	@JoinColumn(name = "ID_PARAMETRO")
	private ParametroEntity parametro;

	// bi-directional many-to-one association to TipoDato
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_DATO")
	private TipoDatoEntity tipoDato;

	// bi-directional many-to-one association to Usuario

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private UsuarioEntity usuario;

	// bi-directional many-to-many association to Formulario
	/*
	 * @ManyToMany(mappedBy="casillas")
	 * 
	 * @LazyCollection(LazyCollectionOption.FALSE) private List<Formulario>
	 * formularios;
	 */
	// Utilizamos esta variable solamente para guardar el valor del registro, ya que
	// si no es tipo Casilla no funciona.
	@Transient
	private Long valorRegistroCA;

	// Utilizamos esta variable solamente porque cuando se cargan las casillas para
	// crear el registro, si el dato no es te dipo casilla no lo toma, esta variable
	// no se guarda en la base de datos, por eso el @Transient, al igual que el valorRegistroCA
	@Transient
	private boolean obligatoria;

	public CasillaEntity() {
	}

	public CasillaEntity(String descripcion, String nombre, ParametroEntity parametro, TipoDatoEntity tipoDato,
			UsuarioEntity usuario) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.parametro = parametro;
		this.tipoDato = tipoDato;
		this.usuario = usuario;
		this.disponible = true;
	}

	public long getIdCasilla() {
		return this.idCasilla;
	}

	public void setIdCasilla(long idCasilla) {
		this.idCasilla = idCasilla;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ParametroEntity getParametro() {
		return this.parametro;
	}

	public void setParametro(ParametroEntity parametro) {
		this.parametro = parametro;
	}

	public TipoDatoEntity getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(TipoDatoEntity tipoDato) {
		this.tipoDato = tipoDato;
	}

	public UsuarioEntity getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public Long getValorRegistroCA() {
		return valorRegistroCA;
	}

	public void setValorRegistroCA(Long valorRegistroCA) {
		this.valorRegistroCA = valorRegistroCA;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	/*
	 * public List<FormularioEntity> getFormularios() { return this.formularios; }
	 * 
	 * public void setFormularios(List<FormularioEntity> formularios) {
	 * this.formularios = formularios; }
	 * 
	 */

	public boolean isObligatoria() {
		return obligatoria;
	}

	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	@Override
	public String toString() {
		return "Casilla [idCasilla=" + idCasilla + ", descripcion=" + descripcion + ", nombre=" + nombre
				+ ", disponible=" + disponible + ", parametro" + parametro + ", tipoDato=" + tipoDato + ", usuario="
				+ usuario + ", formularios=" + "formularios" + "]";
	}

}