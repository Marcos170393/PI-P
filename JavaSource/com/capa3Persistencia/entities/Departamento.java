package com.capa3Persistencia.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
/**
 * The persistent class for the DEPARTAMENTOS database table.
 * 
 */
@Entity
@Table(name="DEPARTAMENTOS")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	/********************************************************************ATRIBUTOS****/
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTAMENTOS_IDDEPARTAMENTO_GENERATOR", sequenceName="SEQ_DEPTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTAMENTOS_IDDEPARTAMENTO_GENERATOR")
	@Column(name="ID_DEPARTAMENTO")
	private long idDepartamento;

	@Column(unique = true,nullable = false, columnDefinition="varchar2(20)")
	private String nombre;

	//bi-directional many-to-one association to Ciudad
	@OneToMany(fetch = FetchType.EAGER)
	private List<Ciudad> ciudades;
	/********************************************************************CONSTRUCTORES****/
	public Departamento() {
	}
	public Departamento(String nombre) {
		super();
		this.nombre = nombre;
		 
	}
	/********************************************************************GETTERS****/
	public long getIdDepartamento() {
		return this.idDepartamento;
	}
	public String getNombre() {
		return this.nombre;
	}
	public List<Ciudad> getCiudades() {
		return this.ciudades;
	}
	/********************************************************************SETTERS****/
	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	/********************************************************************METODOS****/
	/*CREADOS AUTOMATICAMENTE BORRAR NO SIRVEN/
	/*AGREGAR CIUDAD*/
	public Ciudad addCiudade(Ciudad ciudade) {
		getCiudades().add(ciudade);
		
		return ciudade;
	}
	/*ELIMINAR CIUDAD*/
	public Ciudad removeCiudade(Ciudad ciudade) {
		getCiudades().remove(ciudade);
		
		return ciudade;
	}
	/********************************************************************TOSTRING****/
	@Override
	public String toString() {
		return "Departamento [idDepartamento=" + idDepartamento + ", nombre=" + nombre + ", ciudades=" + ciudades + "]";
	}
}