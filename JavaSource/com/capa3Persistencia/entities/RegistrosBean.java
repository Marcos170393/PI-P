package com.capa3Persistencia.entities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

/**
 * Session Bean implementation class RegistrosBean
 */
@Stateless
public class RegistrosBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public RegistrosBean() {
		// TODO Auto-generated constructor stub
	}

	public RegistroEntity crear(RegistroEntity reg) throws PersistenceException {
		try {
			em.persist(reg);
			em.flush();
			return reg;
		} catch (PersistenceException e) {
			throw new PersistenceException("No se pudo crear el registro");
		}
	}

	public void actualizar(RegistroEntity reg) throws Exception {
		try {

			em.merge(reg);
			em.flush();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("No se pudo actualizar el RegistroEntity");
		}
	}

	public List<RegistroEntity> obtenerTodos() throws PersistenceException {
		try {
			TypedQuery<RegistroEntity> query = em.createNamedQuery("RegistroEntity.findAll", RegistroEntity.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new PersistenceException("No se pudo realizar la busqueda");
		}

	}

	public RegistroEntity buscarRegistroEntity(Long idRegistro) throws Exception {
		try {
			return em.find(RegistroEntity.class, idRegistro);
		} catch (Exception e) {
			throw new Exception("Error al realizar la busqueda");
		}
	}

	public List<RegistroEntity> obtenerRegistroPorFormulario(Long idFormulario) throws PersistenceException {
		try {
			Query query = em.createNativeQuery("SELECT * FROM REGISTROS WHERE id_formulario=" + idFormulario,
					RegistroEntity.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new PersistenceException("No se pudo realizar la busqueda");
		}

	}

	public Integer obtenerUk() throws PersistenceException {
		try {
			TypedQuery<RegistroEntity> query = em.createQuery(
					"SELECT r FROM RegistroEntity r WHERE r.uk_registro = (SELECT MAX(r.uk_registro) FROM RegistroEntity r) AND rownum = 1",
					RegistroEntity.class);
			return query.getSingleResult().getUk_registro();

		} catch (PersistenceException e) {
			return 0;
		}
	}

	public void eliminarRegistro(Long id) throws PersistenceException {
		try {
			RegistroEntity registro = em.find(RegistroEntity.class, id);
			em.remove(registro);
			em.flush();
		} catch (PersistenceException e) {
			throw new PersistenceException("No se pudo eliminar el registro");
		}

	}

}
