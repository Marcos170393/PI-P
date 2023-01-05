package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CIUDADES database table.
 * 
 */
@Entity
@Table(name="CIUDADES")
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM CiudadEntity c")
public class CiudadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CIUDADES_IDCIUDAD_GENERATOR", sequenceName="SEQ_CIUDAD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CIUDADES_IDCIUDAD_GENERATOR")
	@Column(name="ID_CIUDAD")
	private long idCiudad;

	@Column(unique = true, nullable = false, length = 45)
	private String nombre;

	
	public CiudadEntity() {
	}
	
	
	public CiudadEntity(String nombre) {
		super();
		this.nombre = nombre;
	}

	public long getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Ciudad [idCiudad=" + idCiudad + ", nombre=" + nombre + "]";
	}

	


}