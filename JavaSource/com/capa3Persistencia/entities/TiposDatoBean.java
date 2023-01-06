package com.capa3Persistencia.entities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


/**
 * Session Bean implementation class TiposDatoBean
 */
@Stateless
public class TiposDatoBean{

	
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public TiposDatoBean() {
        // TODO Auto-generated constructor stub
    }
	
	public TipoDatoEntity crear(TipoDatoEntity dato) throws Exception {
		try {
			em.persist(dato);
			em.flush();
			return dato;
		} catch (PersistenceException e) {
			 throw new Exception("No se pudo crear el dato");
		}
	}
	
	public void actualizar(TipoDatoEntity dato) throws Exception {
		 try {
			 em.merge(dato);
			 em.flush();
		 }catch (PersistenceException e) {
			 throw new Exception("No se pudo actualizar el dato");
		}
		
	}
	
	public void eliminar(Long idDato) throws Exception {
		 try {
			 TipoDatoEntity dato = em.find(TipoDatoEntity.class, idDato);
			 em.remove(dato);
			 em.flush();
		 }catch (PersistenceException e) {
			 throw new Exception("No se pudo eliminar el dato");
		}
		
	}
	
	public TipoDatoEntity obtenerTipoDato(Long idDato) throws Exception {
		try {
			TipoDatoEntity dato = em.find(TipoDatoEntity.class, idDato);
			return dato;
		}catch (PersistenceException e) {
			 throw new Exception("No se pudo realizar la busqueda");
		}
	}
	
	public List<TipoDatoEntity> obtenerTodos() throws Exception {
		try {
			TypedQuery<TipoDatoEntity> query = em.createNamedQuery("TipoDato.findAll", TipoDatoEntity.class);
			return query.getResultList();
		} catch(PersistenceException e) {
			throw new Exception("No se pudo realizar la busqueda");
		}
	}
	
	
	public TipoDatoEntity obtenerTipoDato(String nombre) throws Exception {
		try {
			TypedQuery<TipoDatoEntity> query = em.createQuery("SELECT t FROM TipoDato t WHERE nombre = :nombre", TipoDatoEntity.class);
			query.setParameter("nombre",nombre);
			return query.getSingleResult();
		}catch (PersistenceException e) {
			throw new Exception("No se pudo realizar la busqueda");
		}
	}
	
	

    
}
