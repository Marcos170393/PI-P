package com.capa1presentacion;

import java.util.Date;
import java.util.List;

import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.entities.DepartamentoEntity;
import com.capa3Persistencia.entities.UsuarioEntity;

public class Formulario {

	private long idFormulario;

	private String nombre;

	private Date fechaHora;

	private TipoMedicion metodoMedicion;

	private String resumen;

	private List<CasillaEntity> casillas;

	private List<CasillaEntity> casillasObligatorias;

	private UsuarioEntity usuario;

	private boolean isDisponible;

	//private DepartamentoEntity departamento;

	private Departamento departamento;
	
	public Formulario() {

	}

	public Formulario(String nombre, Date fechaHora, TipoMedicion metodoMedicion, String resumen,
			List<CasillaEntity> casillas, UsuarioEntity usuario, Departamento departamento) {
		super();
		this.nombre = nombre;
		this.fechaHora = fechaHora;
		this.metodoMedicion = metodoMedicion;
		this.resumen = resumen;
		this.casillas = casillas;
		this.usuario = usuario;
		this.departamento = departamento;
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

	public List<CasillaEntity> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<CasillaEntity> casillas) {
		this.casillas = casillas;
	}

	public List<CasillaEntity> getCasillasObligatorias() {
		return casillasObligatorias;
	}

	public void setCasillasObligatorias(List<CasillaEntity> casillasObligatorias) {
		this.casillasObligatorias = casillasObligatorias;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public boolean isDisponible() {
		return isDisponible;
	}

	public void setDisponible(boolean isDisponible) {
		this.isDisponible = isDisponible;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Formulario [idFormulario=" + idFormulario + ", nombre=" + nombre + ", fechaHora=" + fechaHora
				+ ", metodoMedicion=" + metodoMedicion + ", resumen=" + resumen + ", casillas=" + casillas
				+ ", casillasObligatorias=" + casillasObligatorias + ", usuario=" + usuario + ", isDisponible="
				+ isDisponible + ", departamento=" + departamento + "]";
	}

}
