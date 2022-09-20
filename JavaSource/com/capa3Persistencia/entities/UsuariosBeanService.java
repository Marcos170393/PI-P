package com.capa3Persistencia.entities;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
/**
 * Session Bean implementation class UsuarioEntitysBean
 */
@Stateless
public class UsuariosBeanService  {
	/********************************************************************ACCESO AL ENTITY MANAGER****/
	@PersistenceContext
	private EntityManager em;
	/********************************************************************CONSTRUCTOR****/
    public UsuariosBeanService() {
    }
    /********************************************************************CREAR USER****/
    
	public Usuario crear(Usuario user) {
		try {
			em.persist(user);
			em.flush();
			return user;
		} catch (RuntimeException e) {
			System.out.println("No se pudo crear el usuario" + e.getMessage());
			return null;
		}
	}
	/********************************************************************MODIFICAR USER****/

	public void actualizar(Usuario user) throws Exception {
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
			Usuario user = em.find(Usuario.class, idUser);
			em.remove(user);
			em.flush();
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el UsuarioEntity");
		}
	}
	/********************************************************************BUSCAR USER****/
	public Usuario buscarUsuarioEntity(Long idUser) throws Exception {

		try {
			return em.find(Usuario.class, idUser);
		} catch (Exception e) {
			throw new Exception("Error al realizar la busqueda");
		}
	}
	/********************************************************************BUSCAR USER POR NOMBRE
	 * @throws NamingException ****/

	public Usuario buscarNombreUsuarioEntity(String nombreUser) throws Exception   {

		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE nombre_usuario = :nombre", Usuario.class);
			query.setParameter("nombre", nombreUser);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new Exception("Error al realizar la busqueda");
		}
		
	}
	/********************************************************************BUSCAR TODOS LOS USERS****/
	public List<Usuario> buscarTodos() throws Exception {

		try {
			TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new Exception("Error al realizar la busqueda");
		}
	}
}