package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PARAMETROS database table.
 * 
 */
@Entity
@Table(name="PARAMETROS")
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM ParametroEntity p")
public class ParametroEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PARAMETROS_IDPARAMETRO_GENERATOR", sequenceName="SEQ_PARAM")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PARAMETROS_IDPARAMETRO_GENERATOR")
	@Column(name="ID_PARAMETRO")
	private long idParametro;

	@Column(unique = true, nullable = false,columnDefinition="varchar2(20)")
	private String nombre;

//	//bi-directional many-to-one association to Casilla
//	@OneToMany(mappedBy="parametroBean")
//	private List<Casilla> casillas;
//
//	//bi-directional many-to-one association to Estacion
//	@OneToMany(mappedBy="parametroBean")
//	private List<Estacion> estaciones;

	public ParametroEntity() {
	}

	
	
	public ParametroEntity(String nombre) {
		super();
		this.nombre = nombre;
	}



	public long getIdParametro() {
		return this.idParametro;
	}

	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public String toString() {
		return "Parametro [idParametro=" + idParametro + ", nombre=" + nombre + "]";
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
//		casilla.setParametro(this);
//
//		return casilla;
//	}
//
//	public Casilla removeCasilla(Casilla casilla) {
//		getCasillas().remove(casilla);
//		casilla.setParametro(null);
//
//		return casilla;
//	}

//	public List<Estacion> getEstaciones() {
//		return this.estaciones;
//	}
//
//	public void setEstaciones(List<Estacion> estaciones) {
//		this.estaciones = estaciones;
//	}

//	public Estacion addEstacione(Estacion estacione) {
//		getEstaciones().add(estacione);
//		estacione.setParametroBean(this);
//
//		return estacione;
//	}
//
//	public Estacion removeEstacione(Estacion estacione) {
//		getEstaciones().remove(estacione);
//		estacione.setParametroBean(null);
//
//		return estacione;
//	}
	
	

}