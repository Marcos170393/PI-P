package com.capa1presentacion;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.capa3Persistencia.entities.CasillaEntity;

public class Formulario {

	
	private long idFormulario;

	@NotNull(message = "Debes ingresar el nombre")
	@Size(min = 3, max = 20, message = "Min. 3 caracteres / Max. 20 caracteres")
	private String nombre;

	private Date fechaHora;

	@NotNull(message="Debes seleccionar un tipo de medicion")
	private TipoMedicion metodoMedicion;

	@NotNull(message="Debes ingresar un resumen")
	@Size(min = 3, max=50, message = "Min 3 caracteres / Max. 50 caracteres")
	private String resumen;

	@NotNull(message = "Debes seleccionar al menos una casilla")
	private List<CasillaEntity> casillas;

	private List<CasillaEntity> casillasObligatorias;

	private Usuario usuario;

	private boolean isDisponible;
	
	@NotNull(message="Debes seleccionar un departamento")
	private Departamento departamento;

	public Formulario(String nombre, Date fechaHora, TipoMedicion metodoMedicion, String resumen,
			List<CasillaEntity> casillas, Usuario usuario, Departamento departamento) {
		super();
		this.nombre = nombre;
		this.fechaHora = fechaHora;
		this.metodoMedicion = metodoMedicion;
		this.resumen = resumen;
		this.casillas = casillas;
		this.usuario = usuario;
		this.isDisponible = true;
		this.departamento = departamento;
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
				+ isDisponible + "]";
	}

}
