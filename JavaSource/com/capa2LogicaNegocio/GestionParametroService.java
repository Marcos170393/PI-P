package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa1presentacion.Ciudad;
import com.capa1presentacion.Parametro;
import com.capa1presentacion.Usuario;
import com.capa3Persistencia.entities.CiudadEntity;
import com.capa3Persistencia.entities.ParametroEntity;
import com.capa3Persistencia.entities.ParametrosBean;
import com.capa3Persistencia.entities.UsuarioEntity;

@Stateless
@LocalBean
public class GestionParametroService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ParametrosBean parametroBean = new ParametrosBean();

	public Parametro fromParametroEntity(ParametroEntity p) {
		Parametro parametro = new Parametro();
		parametro.setIdParametro(p.getIdParametro());
		parametro.setNombre(p.getNombre());
		return parametro;
	}

	public ParametroEntity toParametroEntity(Parametro p) {
		ParametroEntity parametro = new ParametroEntity();
		parametro.setNombre(p.getNombre());
		return parametro;
	}

	public ParametroEntity forParametroEntity(Parametro p) {
		ParametroEntity parametro = new ParametroEntity();
		parametro.setIdParametro(p.getIdParametro());
		parametro.setNombre(p.getNombre());
		return parametro;
	}

	public Parametro agregarParametro(Parametro parametro) throws Exception {
		ParametroEntity e = parametroBean.crear(toParametroEntity(parametro));
		return fromParametroEntity(e);
	}

	public Parametro buscarParametroEntity(Long id) throws Exception {
		ParametroEntity p = parametroBean.buscarParametro(id);
		return fromParametroEntity(p);
	}

	public List<Parametro> seleccionarParametros() throws Exception {

		List<ParametroEntity> listaParametrosEntity = parametroBean.buscarTodos();

		List<Parametro> listaParametros = new ArrayList<>();

		for (ParametroEntity p : listaParametrosEntity) {
			listaParametros.add(fromParametroEntity(p));
		}
		return listaParametros;
	}



}
