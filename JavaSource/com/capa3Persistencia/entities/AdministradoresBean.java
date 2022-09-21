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
 * Session Bean implementation class AdministradoresBean
 */
@Stateless
@Remote
public class AdministradoresBean {
	/********************************************************************ACCESO AL ENTITY MANAGER****/
	@PersistenceContext
	private EntityManager em;
	/********************************************************************CONSTRUCTOR****/
    public AdministradoresBean() {
        // TODO Auto-generated constructor stub
    }
    /********************************************************************CREAR USER ADMIN****/
	public Administrador crear(Administrador admin) throws CustomException {
		try {
			em.persist(admin);
			em.flush();
			return admin;
		} catch (PersistenceException e) {
			throw new CustomException("No se pudo crear el Usuario");
		}
		
	}
	/********************************************************************MODIFICAR USER ADMIN****/
	public void actualizar(Administrador admin) throws CustomException {
		try {
			em.merge(admin);
			em.flush();
		}catch(PersistenceException e) {
			throw new CustomException("No se pudo actualizar el Administrador");
		}
		
	}
	/********************************************************************ELIMINAR USER ADMIN****/
	public void eliminar(Long idAdmin) throws CustomException {
		try {
			Administrador admin = em.find(Administrador.class, idAdmin);
			em.remove(admin);
		}catch(PersistenceException e) {
			throw new CustomException("No se pudo eliminar el Administrador");
		}
	}
	/********************************************************************BUSCAR USER ADMIN****/
	public Administrador buscarAdmin(Long idAdmin) throws CustomException {
		try {
			Administrador admin = em.find(Administrador.class, idAdmin);
		}catch(PersistenceException e) {
			throw new CustomException("Error al realizar la busqueda");
		};
		return null;
	}
	/********************************************************************BUSCAR TODOS USER ADMIN****/
	public List<Administrador> buscarTodos() throws CustomException {
		try {
			TypedQuery<Administrador> query = em.createNamedQuery("Administrador.findAll", Administrador.class);
			return query.getResultList();
		}catch(PersistenceException e) {
			throw new CustomException("Error al realizar la busqueda");
		}
	}
}