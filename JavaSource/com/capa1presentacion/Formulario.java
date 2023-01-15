package com.capa1presentacion;

import java.util.Date;
import java.util.List;

public class Formulario {

	private long idFormulario;

	private String nombre;
	
	private Date fechaHora;
	
	private TipoMedicion metodoMedicion;
	
	private String resumen;

	private List<Casilla> casillas;
	
	private List<Casilla> casillasObligatorias;
	
	private Usuario usuario;
	
	private boolean isDisponible;

	public Formulario(String nombre, Date fechaHora, TipoMedicion metodoMedicion, String resumen,
			List<Casilla> casillas, Usuario usuario) {
		super();
		this.nombre = nombre;
		this.fechaHora = fechaHora;
		this.metodoMedicion = metodoMedicion;
		this.resumen = resumen;
		this.casillas = casillas;
		this.usuario = usuario;
		this.isDisponible = true;
	}
	
	public Formulario() {
		
	}

	public long getIdFormulario() {
		return idFormulario;
	}

	public void setIdFormulario(long idFormulario) {
		this.idFormulario = idFormulario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public TipoMedicion getMetodoMedicion() {
		return metodoMedicion;
	}

	public void setMetodoMedicion(TipoMedicion metodoMedicion) {
		this.metodoMedicion = metodoMedicion;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public List<Casilla> getCasillasObligatorias() {
		return casillasObligatorias;
	}

	public void setCasillasObligatorias(List<Casilla> casillasObligatorias) {
		this.casillasObligatorias = casillasObligatorias;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isDisponible() {
		return isDisponible;
	}

	public void setDisponible(boolean isDisponible) {
		this.isDisponible = isDisponible;
	}

	@Override
	public String toString() {
		return "Formulario [idFormulario=" + idFormulario + ", nombre=" + nombre + ", fechaHora=" + fechaHora
				+ ", metodoMedicion=" + metodoMedicion + ", resumen=" + resumen + ", casillas=" + casillas
				+ ", casillasObligatorias=" + casillasObligatorias + ", usuario=" + usuario + ", isDisponible="
				+ isDisponible + "]";
	}
	
	
	
}
