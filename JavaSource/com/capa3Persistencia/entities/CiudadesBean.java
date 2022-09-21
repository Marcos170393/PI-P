package com.capa3Persistencia.entities;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.exception.CustomException;
/**
 * Session Bean implementation class CiudadesBean
 */
@Stateless
@Remote
public class CiudadesBean{
	/********************************************************************ACCESO AL ENTITY MANAGER****/
	@PersistenceContext
	private EntityManager em;
	/********************************************************************CONSTRUCTOR****/
    public CiudadesBean() {
        // TODO Auto-generated constructor stub
    }
    /********************************************************************CREAR CIUDAD****/
	public Ciudad crear(Ciudad ciudad) throws CustomException {
		try {
			em.persist(ciudad);
			em.flush();
			return ciudad;
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo crear la Ciudad");
		}
		
	}
	/********************************************************************MODIFICAR CIUDAD****/
	public void actualizar(Ciudad ciudad) throws CustomException {
		try {
			em.merge(ciudad);
			em.flush();
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo actualizar la Ciudad");
		}
		
	}
	/********************************************************************ELIMINAR CIUDAD****/
	public void eliminar(Long idCiudad) throws CustomException {
		try {
			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
			em.remove(ciudad);
			em.flush();
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo crear el Ciudad");
		}
	}
	/********************************************************************BUSCAR CIUDAD****/
	public Ciudad buscarCiudad(Long idCiudad) throws CustomException {
		try {
			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
			return ciudad;
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo encontrar crear el Ciudad");
		}
	}
	/********************************************************************BUSCAR CIUDAD POR NOMBRE****/
	public Ciudad buscarCiudad(String nombre) throws CustomException {
		try {
			TypedQuery<Ciudad> query = em.createQuery("SELECT c FROM Ciudad c WHERE NOMBRE = :nombre", Ciudad.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo encontrar la Ciudad");
		}
	}
	/********************************************************************BUSCAR TODAS LAS CIUDADES****/
	public List<Ciudad> obtenerTodas() throws CustomException {
		try {
			TypedQuery<Ciudad> query = em.createNamedQuery("Ciudad.findAll", Ciudad.class);
			return query.getResultList();
		}catch(PersistenceException p) {
			throw new CustomException("Error al realizar la busqueda");
		}
	}
	/********************************************************************AGREGAR ESTACION****/
//	public void agregarEstacion(Long idCiudad, Long idEstacion) throws CustomException {
//		try {
//			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
//			ciudad.addEstacione(em.find(Estacion.class, idEstacion));
//		}catch(PersistenceException p) {
//			throw new CustomException("Error al agregar la Estacion a la ciudad");
//		}
//	}
	/********************************************************************ELIMINAR ESTACION****/	
//	@Override
//	public boolean removerEstacion(Long idCiudad, Estacion estacion) throws CustomException {
//		try {
//			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
//			ciudad.getEstaciones().remove(estacion);
//			return true;
//		}catch(PersistenceException p) {
//			throw new CustomException("No se pudo eliminar la estacion de la ciudad");
//		}
//	}
}