package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;


/**
 * The persistent class for the CASILLAS database table.
 * 
 */
@Entity
@Table(name="CASILLAS")
@NamedQuery(name="Casilla.findAll", query="SELECT c FROM CasillaEntity c")
public class CasillaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CASILLAS_IDCASILLA_GENERATOR", sequenceName="SEQ_CASILLA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CASILLAS_IDCASILLA_GENERATOR")
	@Column(name="ID_CASILLA")
	private long idCasilla;

	@Column(nullable = true, columnDefinition="varchar2(100)")
	private String descripcion;

	@Column(nullable = false, columnDefinition="varchar2(30)")
	private String nombre;

	private boolean disponible;
	
	
	//bi-directional many-to-one association to Parametro
	@ManyToOne
	@JoinColumn(name="PARAMETRO")
	@LazyCollection(LazyCollectionOption.FALSE)
	private ParametroEntity parametroBean;

	//bi-directional many-to-one association to TipoDato
	@ManyToOne
	@JoinColumn(name="ID_TIPO_DATO")
	@LazyCollection(LazyCollectionOption.FALSE)
	private TipoDatoEntity tipoDato;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	@LazyCollection(LazyCollectionOption.FALSE)
	private UsuarioEntity usuario;

	//bi-directional many-to-many association to Formulario
/*	@ManyToMany(mappedBy="casillas")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Formulario> formularios;*/

	public CasillaEntity() {
	}

	
	
	public CasillaEntity(String descripcion, String nombre, ParametroEntity parametro, TipoDatoEntity tipoDato, UsuarioEntity usuario) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.parametroBean = parametro;
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

	public ParametroEntity getParametroBean() {
		return this.parametroBean;
	}

	public void setParametro(ParametroEntity parametro) {
		this.parametroBean = parametro;
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



	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


/*
	public List<FormularioEntity> getFormularios() {
		return this.formularios;
	}

	public void setFormularios(List<FormularioEntity> formularios) {
		this.formularios = formularios;
	}

*/

	@Override
	public String toString() {
		return "Casilla [idCasilla=" + idCasilla + ", descripcion=" + descripcion + ", nombre=" + nombre
				+ ", disponible=" + disponible + ", parametroBean=" + parametroBean + ", tipoDato=" + tipoDato
				+ ", usuario=" + usuario + ", formularios=" + "formularios" + "]";
	}



	
	

	

}