package com.capa3Persistencia.entities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class CasillasBean
 */
@Stateless
public class CasillasBean {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public CasillasBean() {
        // TODO Auto-generated constructor stub
    }

	
	public CasillaEntity crear(CasillaEntity casilla) throws Exception {
		try {
			em.persist(casilla);
			em.flush();
			return casilla;
		} catch (PersistenceException e) {
			throw new Exception("No se pudo crear la casilla");
		}
	}

	
	public void actualizar(CasillaEntity casilla) throws Exception {
		try {
			em.merge(casilla);
			em.flush();
		} catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar la casilla");
		}
	}

	
	public void eliminar(Long idCasilla) throws Exception {
		 try {
			CasillaEntity casilla = em.find(CasillaEntity.class, idCasilla);
			em.remove(casilla);
			em.flush();
		} catch (PersistenceException e) {
			 throw new Exception("No se pudo eliminar la casilla");
		}
		
	}

	
	public CasillaEntity obtenerCasilla(Long idCasilla) throws Exception {
		try {
			CasillaEntity casilla = em.find(CasillaEntity.class, idCasilla);
			return casilla;
		} catch (Exception e) {
			throw new Exception("No se pudo obtener la casilla");
		}
	}

	
	public List<CasillaEntity> obtenerTodas() throws Exception {
		 try {
			 TypedQuery<CasillaEntity> query = em.createNamedQuery("Casilla.findAll",CasillaEntity.class);
			 return query.getResultList();
		} catch (PersistenceException e) {
			 throw new Exception("No se pudo realizar la busqueda");		}
		
	}
    
	public CasillaEntity buscarCasillaNombre(String nombre) throws Exception {
		try {
			TypedQuery<CasillaEntity> casilla = em.createQuery("Select c FROM CasillaEntity c WHERE nombre=:nombre",CasillaEntity.class).setParameter("nombre", nombre);
			return casilla.getSingleResult();
		} catch (Exception e) {
			throw new Exception("No se pudo obtener la casilla" + e.getCause());
		}
	}
    

}
