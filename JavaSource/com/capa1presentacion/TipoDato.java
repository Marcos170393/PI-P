package com.capa1presentacion;

public class TipoDato {

	private long idDato;

	private String nombre;

	private String unidadMedida;

	public TipoDato(String nombre, String unidadMedida) {
		super();
		this.nombre = nombre;
		this.unidadMedida = unidadMedida;
	}

	public TipoDato() {
		// TODO Auto-generated constructor stub
	}

	public long getIdDato() {
		return idDato;
	}

	public void setIdDato(long idDato) {
		this.idDato = idDato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	@Override
	public String toString() {
		return "TipoDato [idDato=" + idDato + ", nombre=" + nombre + ", unidadMedida=" + unidadMedida + "]";
	}

}
