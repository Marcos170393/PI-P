package com.capa1presentacion;

public class Casilla {

	private long idCasilla;
	
	private String descripcion;
	
	private String nombre;
	
	private boolean disponible;
	
	private Parametro parametro;
	
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
