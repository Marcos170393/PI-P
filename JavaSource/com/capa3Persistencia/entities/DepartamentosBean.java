package com.capa3Persistencia.entities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


/**
 * Session Bean implementation class DepartamentosBean
 */
@Stateless
public class DepartamentosBean {

	
	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public DepartamentosBean() {
        // TODO Auto-generated constructor stub
    }

	
	public DepartamentoEntity crear(DepartamentoEntity depto) throws Exception {
		try {
			em.persist(depto);
			em.flush();
			return depto;
		}catch(PersistenceException p) {
			throw new Exception("No se pudo crear el Departamento");
		}
		
	}

	
	public void actualizar(DepartamentoEntity depto) throws Exception {
		try {
			em.merge(depto);
			em.flush();
		}catch(PersistenceException p) {
			throw new Exception("No se pudo actualizar el Departamento");
		}
		
	}

	
	public void eliminar(Long idDpto) throws Exception {
		try {
			DepartamentoEntity dpto = em.find(DepartamentoEntity.class, idDpto);
			em.remove(dpto);
			em.flush();
		}catch(PersistenceException p) {
			throw new Exception("No se pudo eliminar el Departamento");
		}
	}

	
	public void agregarCiudad(Long idDpto, Long idCiudad) throws Exception {
		try {
			DepartamentoEntity dpto = em.find(DepartamentoEntity.class, idDpto);
			CiudadEntity ciudad = em.find(CiudadEntity.class, idCiudad);
			
			dpto.addCiudade(ciudad);
			em.persist(dpto);
			em.flush();
		}catch(PersistenceException p) {
			throw new Exception("No se pudo agregar la Ciudad al Departamento");
		}
		
	}

	
	public DepartamentoEntity buscarDepartamento(Long idDpto) throws Exception {
		try {
			DepartamentoEntity dpto = em.find(DepartamentoEntity.class, idDpto);
			return dpto;
		}catch(PersistenceException p) {
			throw new Exception("Error inesperado en la busqueda");
		}
	}
	

	
	public DepartamentoEntity buscarDepartamento(String nombre) throws Exception {
		try {
			TypedQuery<DepartamentoEntity> query = em.createQuery("SELECT d FROM DepartamentoEntity d WHERE d.nombre = :nombre", DepartamentoEntity.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		}catch(PersistenceException p) {
			throw new Exception("No se pudo encontrar el departamento");
		}
	}
//	@Override
//	public Departamento buscarDepartamentoPorCiudad(Long ciudad) throws CustomException {
//		try {
//			TypedQuery<Departamento> query = em.createQuery("SELECT d FROM Departamento d WHERE d.nombre = :nombre", Departamento.class);
//			query.setParameter("nombre", nombre);
//			return query.getSingleResult();
//		}catch(PersistenceException p) {
//			throw new CustomException("No se pudo encontrar el departamento");
//		}
//	}

	
	public List<DepartamentoEntity> obtenerTodos() throws Exception {
		try {
			TypedQuery<DepartamentoEntity> query = em.createNamedQuery("DepartamentoEntity.findAll", DepartamentoEntity.class);
			return query.getResultList();
		}catch(PersistenceException p) {
			throw new Exception("Error inesperado en la busqueda");
		}
	}
    
    

}
