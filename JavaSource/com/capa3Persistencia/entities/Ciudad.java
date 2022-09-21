package com.capa3Persistencia.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
/**
 * The persistent class for the CIUDADES database table.
 * 
 */
@Entity
@Table(name="CIUDADES")
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	/********************************************************************ATRIBUTOS****/
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CIUDADES_IDCIUDAD_GENERATOR", sequenceName="SEQ_CIUDAD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CIUDADES_IDCIUDAD_GENERATOR")
	@Column(name="ID_CIUDAD")
	private long idCiudad;

	@Column(unique = true, nullable = false, length = 45)
	private String nombre;

	//bi-directional many-to-one association to Estacion
//	@OneToMany(mappedBy="ciudade")
//	private List<Estacion> estaciones;
	/********************************************************************CONSTRUCTORES****/
	public Ciudad() {
	}
	public Ciudad(String nombre) {
		super();
		this.nombre = nombre;
	}
	/********************************************************************GETTERS****/
	public long getIdCiudad() {
		return this.idCiudad;
	}
	public String getNombre() {
		return this.nombre;
	}
//	public List<Estacion> getEstaciones() {
//		return this.estaciones;
//	}
	/********************************************************************SETTERS****/
	public void setIdCiudad(long idCiudad) {
		this.idCiudad = idCiudad;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//	public void setEstaciones(List<Estacion> estaciones) {
//		this.estaciones = estaciones;
//	}
	/********************************************************************METODOS****/
	/*AGREGAR ESTACION*/
//	public Estacion addEstacione(Estacion estacione) {
//		getEstaciones().add(estacione);
//		estacione.setCiudade(this);
//
//		return estacione;
//	}
	/*ELIMINAR ESTACION*/
//	public Estacion removeEstacione(Estacion estacione) {
//		getEstaciones().remove(estacione);
//		estacione.setCiudade(null);
//
//		return estacione;
//	}
	/********************************************************************TOSTRING****/
	@Override
	public String toString() {
		return "Ciudad [idCiudad=" + idCiudad + ", nombre=" + nombre + "]";
	}
}