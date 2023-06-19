package com.capa1presentacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Casilla {

	private long idCasilla;
	
	@NotNull(message="Debes ingresar una descripcion")
	@Size(min = 0, max = 100, message= "Maximo 100 caracteres.")
	private String descripcion;
	
	@NotNull(message = "Debes ingresar un nombre")
	@Size(min = 8, max = 30, message = "Min. 8 caracteres / Max. 30 caracteres")
	private String nombre;
	
	private boolean disponible;
	
	@NotNull(message="Debes seleccionar un parametro")
	private Parametro parametro;
	
	@NotNull(message="Debes seleccionar un tipo de dato")
	private TipoDato tipoDato;
	
	private Usuario usuario;

	public Casilla(String descripcion, String nombre, boolean disponible, Parametro parametro, TipoDato tipoDato,
			Usuario usuario) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.disponible = disponible;
		this.parametro = parametro;
		this.tipoDato = tipoDato;
		this.usuario = usuario;
	}

	public Casilla() {
		// TODO Auto-generated constructor stub
	}

	public long getIdCasilla() {
		return idCasilla;
	}

	public void setIdCasilla(long idCasilla) {
		this.idCasilla = idCasilla;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public TipoDato getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Casilla [idCasilla=" + idCasilla + ", descripcion=" + descripcion + ", nombre=" + nombre
				+ ", disponible=" + disponible + ", parametro=" + parametro + ", tipoDato=" + tipoDato + ", usuario="
				+ usuario + "]";
	}
	
	
	
}
