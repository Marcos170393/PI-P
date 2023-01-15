package com.capa1presentacion;

import java.util.List;

public class Departamento {
	private long idDepartamento;

	private String nombre;

	private List<Ciudad> ciudades;

	public Departamento(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Departamento() {
		super();
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

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	@Override
	public String toString() {
		return "Departamento [idDepartamento=" + idDepartamento + ", nombre=" + nombre + ", ciudades=" + ciudades + "]";
	}

}
