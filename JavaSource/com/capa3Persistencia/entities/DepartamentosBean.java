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
 * Session Bean implementation class DepartamentosBean
 */
@Stateless
@Remote
public class DepartamentosBean{
	/********************************************************************ACCESO AL ENTITY MANAGER****/
	@PersistenceContext
	private EntityManager em;
	/********************************************************************CONSTRUCTOR****/
    public DepartamentosBean() {
        // TODO Auto-generated constructor stub
    }
    /********************************************************************CREAR DEPARTAMENTO****/
	public Departamento crear(Departamento depto) throws CustomException {
		try {
			em.persist(depto);
			em.flush();
			return depto;
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo crear el Departamento");
		}		
	}
	/********************************************************************MODIFICAR DEPARTAMENTO****/
	public void actualizar(Departamento depto) throws CustomException {
		try {
			em.merge(depto);
			em.flush();
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo actualizar el Departamento");
		}
		
	}
	/********************************************************************ELIMINAR DEPARTAMENTO****/
	public void eliminar(Long idDpto) throws CustomException {
		try {
			Departamento dpto = em.find(Departamento.class, idDpto);
			em.remove(dpto);
			em.flush();
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo eliminar el Departamento");
		}
	}
	/********************************************************************AGREGAR CIUDAD****/
	public void agregarCiudad(Long idDpto, Long idCiudad) throws CustomException {
		try {
			Departamento dpto = em.find(Departamento.class, idDpto);
			Ciudad ciudad = em.find(Ciudad.class, idCiudad);
			
			dpto.addCiudade(ciudad);
			em.persist(dpto);
			em.flush();
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo agregar la Ciudad al Departamento");
		}		
	}
	/********************************************************************BUSCAR DEPARTAMENTO****/
	public Departamento buscarDepartamento(Long idDpto) throws CustomException {
		try {
			Departamento dpto = em.find(Departamento.class, idDpto);
			return dpto;
		}catch(PersistenceException p) {
			throw new CustomException("Error inesperado en la busqueda");
		}
	}
	/********************************************************************BUSCAR DEPARTAMENTO POR NOMBRE****/
	public Departamento buscarDepartamento(String nombre) throws CustomException {
		try {
			TypedQuery<Departamento> query = em.createQuery("SELECT d FROM Departamento d WHERE d.nombre = :nombre", Departamento.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		}catch(PersistenceException p) {
			throw new CustomException("No se pudo encontrar el departamento");
		}
	}
	/********************************************************************BUSCAR TODOS LOS DEPARTAMENTOS****/
	public List<Departamento> obtenerTodos() throws CustomException {
		try {
			TypedQuery<Departamento> query = em.createNamedQuery("Departamento.findAll", Departamento.class);
			return query.getResultList();
		}catch(PersistenceException p) {
			throw new CustomException("Error inesperado en la busqueda");
		}
	}
}