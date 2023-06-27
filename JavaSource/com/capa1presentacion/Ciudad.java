package com.capa1presentacion;

public class Ciudad {

	private Long idCiudad;

	private String nombre;

	private Departamento departamento;
	
	public Ciudad(String nombre) {
		super();
		this.nombre = nombre;
	}

	
	public Ciudad() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Ciudad [idCiudad=" + idCiudad + ", nombre=" + nombre + "]";
	}


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}
