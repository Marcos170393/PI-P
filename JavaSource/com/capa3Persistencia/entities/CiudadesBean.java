package com.capa3Persistencia.entities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


/**
 * Session Bean implementation class CiudadesBean
 */
@Stateless
public class CiudadesBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public CiudadesBean() {
		// TODO Auto-generated constructor stub
	}

	public CiudadEntity crear(CiudadEntity ciudad) throws Exception {
		try {
			em.persist(ciudad);
			em.flush();
			return ciudad;
		} catch (PersistenceException p) {
			throw new Exception("No se pudo crear la Ciudad");
		}
	}

	public void actualizar(CiudadEntity ciudad) throws Exception {
		try {
			em.merge(ciudad);
			em.flush();
		} catch (PersistenceException p) {
			throw new Exception("No se pudo actualizar la Ciudad");
		}

	}

	public void eliminar(Long idCiudad) throws Exception {
		try {
			CiudadEntity ciudad = em.find(CiudadEntity.class, idCiudad);
			em.remove(ciudad);
			em.flush();
		} catch (PersistenceException p) {
			throw new Exception("No se pudo crear el Ciudad");
		}
	}

	public List<CiudadEntity> obtenerTodas() throws Exception {
		try {
			TypedQuery<CiudadEntity> query = em.createNamedQuery("Ciudad.findAll", CiudadEntity.class);
			return query.getResultList();
		} catch (PersistenceException p) {
			throw new Exception("Error al realizar la busqueda");
		}
	}

	public CiudadEntity buscarCiudad(Long idCiudad) throws Exception {
		try {
			CiudadEntity ciudad = em.find(CiudadEntity.class, idCiudad);
			return ciudad;
		} catch (PersistenceException p) {
			throw new Exception("No se pudo encontrar crear el Ciudad");
		}
	}

	public CiudadEntity buscarCiudadName(String nombre) throws Exception {
		try {
			TypedQuery<CiudadEntity> query = em.createQuery("SELECT c FROM CiudadEntity c WHERE NOMBRE = :nombre",
					CiudadEntity.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		} catch (PersistenceException p) {
			throw new Exception("No se pudo encontrar la Ciudad");
		}
	}

}
