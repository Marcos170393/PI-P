package com.capa3Persistencia.entities;
import java.util.List;
import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.wildfly.common.annotation.Nullable;
/**
 * Session Bean implementation class UsuarioEntitysBean
 */
@Stateless
public class UsuariosBean  {
	/********************************************************************ACCESO AL ENTITY MANAGER****/
	@PersistenceContext
	private EntityManager em;
	/********************************************************************CONSTRUCTOR****/
    public UsuariosBean() {
    }
    /********************************************************************CREAR USER****/
	public UsuarioEntity crear(UsuarioEntity user) throws Exception {
		try {
			em.persist(user);
			em.flush();
			return user;
		} catch (Exception e) {
			throw new Exception("No se pudo crear el UsuarioEntity" + e.getMessage());
		}
	}
	/********************************************************************MODIFICAR USER****/

	public void actualizar(UsuarioEntity user) throws Exception {
		try {

			em.merge(user);
			em.flush();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("No se pudo actualizar el UsuarioEntity");
		}
	}
	/********************************************************************ELIMINAR USER****/
	public void eliminar(Long idUser) throws Exception {
		try {
			UsuarioEntity user = em.find(UsuarioEntity.class, idUser);
			em.remove(user);
			em.flush();
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el UsuarioEntity");
		}
	}
	/********************************************************************BUSCAR USER****/
	public UsuarioEntity buscarUsuarioEntity(Long idUser) throws Exception {
		try {
			return em.find(UsuarioEntity.class, idUser);
		} catch (Exception e) {
			throw new Exception("Error al realizar la busqueda");
		}
	}
	/********************************************************************BUSCAR USER POR NOMBRE****/

	public UsuarioEntity buscarNombreUsuarioEntity(String nombreUser) throws PersistenceException, NullPointerException {
		try {
			TypedQuery<UsuarioEntity> query = em.createQuery("SELECT u FROM UsuarioEntity u WHERE nombre_usuario = :nombre", UsuarioEntity.class);
			query.setParameter("nombre", nombreUser);
			return query.getSingleResult();
		} catch (PersistenceException|NullPointerException e) 

		{
			UsuarioEntity usuario = new UsuarioEntity();
			return usuario;
			
		}
		
	}
	/********************************************************************BUSCAR TODOS LOS USERS****/
	public List<UsuarioEntity> buscarTodos() throws Exception {
		try {
			TypedQuery<UsuarioEntity> query = em.createNamedQuery("UsuarioEntity.findAll", UsuarioEntity.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new Exception("Error al realizar la busqueda");
		}
	}
}