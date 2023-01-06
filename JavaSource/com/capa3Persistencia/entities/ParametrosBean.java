package com.capa3Persistencia.entities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


/**
 * Session Bean implementation class ParametrosBean
 */
@Stateless
public class ParametrosBean{

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ParametrosBean() {
        // TODO Auto-generated constructor stub
    }
	
	public ParametroEntity crear(ParametroEntity param) throws Exception {
		try {
			em.persist(param);
			em.flush();
			return param;
		} catch (PersistenceException e) {
			throw new Exception("No se pudo crear el Parametro");
		}
	}
	
	public void modificar(ParametroEntity param) throws Exception {
		try {
			em.merge(param);
			em.flush();
		}catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar el Parametro");
		}
		
	}
	
	public void eliminar(Long idParam) throws Exception {
		try {
			ParametroEntity param = em.find(ParametroEntity.class, idParam);
			em.remove(param);
			em.flush();
		}catch (PersistenceException e) {
			throw new Exception("No se pudo eliminar el Parametro");
		}
		
	}
	
	public ParametroEntity buscarParametro(Long idParam) throws Exception {
		 try {
			 ParametroEntity param = em.find(ParametroEntity.class, idParam);
			 return param;
		 }catch (PersistenceException e) {
				throw new Exception("No se pudo realizar la busqueda");
			}
	}
	
	public List<ParametroEntity> buscarTodos() throws Exception {
		try {
			TypedQuery<ParametroEntity> query = em.createNamedQuery("Parametro.findAll", ParametroEntity.class);
			return query.getResultList();
		}catch (PersistenceException e) {
			throw new Exception("No se pudo crear el Parametro");
		}
	}
	
	public ParametroEntity buscarParametro(String nombre) throws Exception {
		try {
			TypedQuery<ParametroEntity> query = em.createQuery("SELECT p FROM Parametro p WHERE nombre = :nombre", ParametroEntity.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		}catch (PersistenceException e) {
			throw new Exception("No se pudo realizar la busqueda");
		}
	}
    
    

}
