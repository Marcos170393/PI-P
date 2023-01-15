package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DEPARTAMENTOS database table.
 * 
 */
@Entity
@Table(name="DEPARTAMENTOS")
@NamedQuery(name="DepartamentoEntity.findAll", query="SELECT d FROM DepartamentoEntity d")
public class DepartamentoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTAMENTOS_IDDEPARTAMENTO_GENERATOR", sequenceName="SEQ_DEPTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTAMENTOS_IDDEPARTAMENTO_GENERATOR")
	@Column(name="ID_DEPARTAMENTO")
	private long idDepartamento;

	@Column(unique = true,nullable = false, columnDefinition="varchar2(20)")
	private String nombre;

	//bi-directional many-to-one association to Ciudad
	@OneToMany(fetch = FetchType.EAGER)
	private List<CiudadEntity> ciudades;

	public DepartamentoEntity() {
	}

	public DepartamentoEntity(String nombre) {
		super();
		this.nombre = nombre;
		 
	}

	public long getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CiudadEntity> getCiudades() {
		return this.ciudades;
	}

	public void setCiudades(List<CiudadEntity> ciudades) {
		this.ciudades = ciudades;
	}

	public CiudadEntity addCiudade(CiudadEntity ciudade) {
		getCiudades().add(ciudade);
		
		return ciudade;
	}

	public CiudadEntity removeCiudade(CiudadEntity ciudade) {
		getCiudades().remove(ciudade);
		
		return ciudade;
	}

	@Override
	public String toString() {
		return "Departamento [idDepartamento=" + idDepartamento + ", nombre=" + nombre + ", ciudades=" + ciudades + "]";
	}


	

}