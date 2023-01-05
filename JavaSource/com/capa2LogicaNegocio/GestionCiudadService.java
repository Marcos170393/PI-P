package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.xml.bind.DatatypeConverter;

import com.capa1presentacion.Ciudad;
import com.capa1presentacion.Usuario;
import com.capa3Persistencia.entities.CiudadEntity;
import com.capa3Persistencia.entities.CiudadesBean;
import com.capa3Persistencia.entities.UsuarioEntity;
import com.capa3Persistencia.entities.UsuariosBean;



@Stateless
@LocalBean

public class GestionCiudadService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    
	@EJB
	CiudadesBean ciudadBean = new CiudadesBean();
	
	
	public Ciudad fromCiudadEntity(CiudadEntity e) {
		Ciudad c = new Ciudad();
		c.setIdCiudad(e.getIdCiudad());
		c.setNombre(e.getNombre());
		return c;
	}
	public CiudadEntity toCiudadEntity(Ciudad e) {
		CiudadEntity ciudad= new CiudadEntity();
		ciudad.setNombre(e.getNombre());
		return ciudad;
	}

	public List<Ciudad> seleccionarCiudades() throws Exception {
		
		List<CiudadEntity> listaCiudadesEntity= ciudadBean.obtenerTodas();
		
		List<Ciudad> listaCiudades=new ArrayList<Ciudad>();
		
		for (CiudadEntity ciudadEntity : listaCiudadesEntity) {
			listaCiudades.add(fromCiudadEntity(ciudadEntity));
		}
		return listaCiudades;
	}

	
	public Ciudad agregarCiudad(Ciudad ciudad) throws Exception   {
		CiudadEntity e = ciudadBean.crear(toCiudadEntity(ciudad));
		return fromCiudadEntity(e);
	}
		
	public Ciudad buscarCiudadEntity(Long id) throws Exception {
		CiudadEntity ciudad = ciudadBean.buscarCiudad(id);
		return fromCiudadEntity(ciudad);
	}
	
	public List<CiudadEntity> buscarCiudades() throws Exception {
		List<CiudadEntity> ciudades = ciudadBean.obtenerTodas();
		return ciudades;
	}
	


}
