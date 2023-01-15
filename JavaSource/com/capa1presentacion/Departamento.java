package com.capa1presentacion;

import java.util.List;

import com.capa3Persistencia.entities.CiudadEntity;

public class Departamento {

	private long idDepartamento;

	private String nombre;

	private List<CiudadEntity> ciudades;

	public Departamento() {

	}

	public Departamento(String nombre) {
		super();
		this.nombre = nombre;
	}

	public long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CiudadEntity> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<CiudadEntity> ciudades) {
		this.ciudades = ciudades;
	}

	@Override
	public String toString() {
		return "Departamento [idDepartamento=" + idDepartamento + ", nombre=" + nombre + ", ciudades=" + ciudades + "]";
	}

}
