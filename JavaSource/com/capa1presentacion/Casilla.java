package com.capa1presentacion;

import com.capa3Persistencia.entities.ParametroEntity;
import com.capa3Persistencia.entities.TipoDatoEntity;
import com.capa3Persistencia.entities.UsuarioEntity;

public class Casilla {

	private long idCasilla;
	
	private String descripcion;
	
	private String nombre;
	
	private boolean disponible;
	
	private ParametroEntity parametro;
	
	private TipoDatoEntity tipoDato;
	
	private UsuarioEntity usuario;

	public Casilla(String descripcion, String nombre, boolean disponible, ParametroEntity parametro, TipoDatoEntity tipoDato,
			UsuarioEntity usuario) {
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

	public ParametroEntity getParametro() {
		return parametro;
	}

	public void setParametro(ParametroEntity parametro) {
		this.parametro = parametro;
	}

	public TipoDatoEntity getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDatoEntity tipoDato) {
		this.tipoDato = tipoDato;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Casilla [idCasilla=" + idCasilla + ", descripcion=" + descripcion + ", nombre=" + nombre
				+ ", disponible=" + disponible + ", parametro=" + parametro + ", tipoDato=" + tipoDato + ", usuario="
				+ usuario + "]";
	}
	
	
	
}
