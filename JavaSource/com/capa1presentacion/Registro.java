package com.capa1presentacion;

import java.util.Date;



public class Registro {
	
	private long idRegistro;
	
	private Usuario usuario;
	
	private Formulario formulario;
	
	private Casilla casilla;
	
	private Date fecha;
	
	private Long valor;
	
	private Integer uk_registro;

	public Registro(Usuario usuario, Formulario formulario, Casilla casilla, Date fecha, Long valor,
			Integer uk_registro) {
		super();
		this.usuario = usuario;
		this.formulario = formulario;
		this.casilla = casilla;
		this.fecha = fecha;
		this.valor = valor;
		this.uk_registro = uk_registro;
	}

	public Registro() {
		// TODO Auto-generated constructor stub
	}

	public long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public Casilla getCasilla() {
		return casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Integer getUk_registro() {
		return uk_registro;
	}

	public void setUk_registro(Integer uk_registro) {
		this.uk_registro = uk_registro;
	}

	@Override
	public String toString() {
		return "Registro [idRegistro=" + idRegistro + ", usuario=" + usuario + ", formulario=" + formulario
				+ ", casilla=" + casilla + ", fecha=" + fecha + ", valor=" + valor + ", uk_registro=" + uk_registro
				+ "]";
	} 
	
	
	
}
