package com.capa3Persistencia.entities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class FormulariosBean
 */
@Stateless
public class FormulariosBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public FormulariosBean() {
		// TODO Auto-generated constructor stub
	}

	public FormularioEntity crear(FormularioEntity form) throws Exception {
		try {
			em.persist(form);
			em.flush();
			return form;
		} catch (PersistenceException e) {
			throw new Exception("No se pudo crear el Formulario");
		}
	}

	public void modificar(FormularioEntity form) throws Exception {
		try {
			em.merge(form);
			em.flush();
		} catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar el Formulario");
		}
	}

	public void eliminar(Long idForm) throws Exception {
		try {
			FormularioEntity form = em.find(FormularioEntity.class, idForm);
			form.setDisponible(false);
			em.flush();
		} catch (PersistenceException e) {
			throw new Exception("No se pudo eliminar el Formulario");
		}
	}

	public FormularioEntity buscarFormulario(Long idFormulario) throws Exception {
		try {
			FormularioEntity form = em.find(FormularioEntity.class, idFormulario);
			return form;
		} catch (PersistenceException e) {
			throw new Exception("No se pudo realizar la busqueda");
		}
	}

	public FormularioEntity buscarFormularioName(String name) throws Exception {
		try {
			TypedQuery<FormularioEntity> query = em
					.createQuery("Select f FROM FormularioEntity f WHERE nombre = :nombre", FormularioEntity.class)
					.setParameter("nombre", name);
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new Exception("No se pudo realizar la busqueda");
		}
	}

	public List<FormularioEntity> buscarTodos() throws Exception {
		try {
			TypedQuery<FormularioEntity> query = em.createNamedQuery("FormularioEntity.findAll",
					FormularioEntity.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new Exception("No se pudo realizar la busqueda");
		}
	}

	public List<FormularioEntity> buscarFormulariosDisponibles() throws Exception {
		try {
			TypedQuery<FormularioEntity> query = em
					.createQuery("Select f FROM FormularioEntity f WHERE isDisponible=true", FormularioEntity.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new Exception("No se pudo realizar la busqueda");
		}
	}

}
