package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPO_DATO database table.
 * 
 */
@Entity
@Table(name="TIPO_DATO")
@NamedQuery(name="TipoDato.findAll", query="SELECT t FROM TipoDatoEntity t")
public class TipoDatoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_DATO_IDDATO_GENERATOR", sequenceName="SEQ_TIPO_DATO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_DATO_IDDATO_GENERATOR")
	@Column(name="ID_DATO")
	private long idDato;

	@Column(nullable = false,columnDefinition="varchar2(20)", unique = true)
	private String nombre;

	@Column(name="UNIDAD_MEDIDA",nullable = false,columnDefinition="varchar2(20)")
	private String unidadMedida;

//	//bi-directional many-to-one association to Casilla
//	@OneToMany(mappedBy="tipoDato")
//	private List<Casilla> casillas;
//
//	//bi-directional many-to-one association to Estacion
//	@OneToMany(mappedBy="tipoDato")
//	private List<Estacion> estaciones;

	public TipoDatoEntity() {
	}

	
	
	public TipoDatoEntity(String nombre, String unidadMedida) {
	super();
	this.nombre = nombre;
	this.unidadMedida = unidadMedida;
}



	public long getIdDato() {
		return this.idDato;
	}

	public void setIdDato(long idDato) {
		this.idDato = idDato;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}



	@Override
	public String toString() {
		return "TipoDato [idDato=" + idDato + ", nombre=" + nombre + ", unidadMedida=" + unidadMedida + "]";
	}

//	public List<Casilla> getCasillas() {
//		return this.casillas;
//	}
//
//	public void setCasillas(List<Casilla> casillas) {
//		this.casillas = casillas;
//	}

//	public Casilla addCasilla(Casilla casilla) {
//		getCasillas().add(casilla);
//		casilla.setTipoDato(this);
//
//		return casilla;
//	}
//
//	public Casilla removeCasilla(Casilla casilla) {
//		getCasillas().remove(casilla);
//		casilla.setTipoDato(null);
//
//		return casilla;
//	}
//
//	public List<Estacion> getEstaciones() {
//		return this.estaciones;
//	}
//
//	public void setEstaciones(List<Estacion> estaciones) {
//		this.estaciones = estaciones;
//	}

//	public Estacion addEstacione(Estacion estacione) {
//		getEstaciones().add(estacione);
//		estacione.setTipoDato(this);
//
//		return estacione;
//	}
//
//	public Estacion removeEstacione(Estacion estacione) {
//		getEstaciones().remove(estacione);
//		estacione.setTipoDato(null);
//
//		return estacione;
//	}

}