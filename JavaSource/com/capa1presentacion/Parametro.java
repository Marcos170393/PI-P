package com.capa1presentacion;

public class Parametro {

	private long idParametro;

	private String nombre;

	public Parametro(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Parametro() {
		// TODO Auto-generated constructor stub
	}

	public long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Parametro [idParametro=" + idParametro + ", nombre=" + nombre + "]";
	}

}
