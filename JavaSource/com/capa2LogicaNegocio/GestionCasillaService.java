package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.capa1presentacion.Casilla;
import com.capa1presentacion.Parametro;
import com.capa1presentacion.TipoDato;
import com.capa1presentacion.Usuario;
import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.entities.CasillasBean;
import com.capa3Persistencia.entities.ParametroEntity;
import com.capa3Persistencia.entities.UsuarioEntity;

@Stateless
@LocalBean
public class GestionCasillaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	CasillasBean casillaBean = new CasillasBean();
	
	@Inject
	GestionUsuarioService persistenciaBean;

	@Inject
	GestionParametroService parametroPersistencia;
	
	@Inject
	GestionTipoDatoService tdatoPersistencia;
	

	public Casilla fromCasillaEntity(CasillaEntity c) {
		Casilla casilla = new Casilla();
		casilla.setIdCasilla(c.getIdCasilla());
		casilla.setNombre(c.getNombre());
		casilla.setDescripcion(c.getDescripcion());
		casilla.setDisponible(c.isDisponible());
		casilla.setParametro(parametroPersistencia.fromParametroEntity(c.getParametro()));
		casilla.setTipoDato(tdatoPersistencia.fromTipodatoEntity(c.getTipoDato()));
		casilla.setUsuario(persistenciaBean.fromUsuarioEntity(c.getUsuario()));
		return casilla;
	}
	public CasillaEntity toCasillaEntity(Casilla c) {
		CasillaEntity casilla = new CasillaEntity();
		casilla.setNombre(c.getNombre());
		casilla.setDescripcion(c.getDescripcion());
		casilla.setDisponible(true);
		casilla.setParametro(parametroPersistencia.forParametroEntity(c.getParametro()));
		casilla.setTipoDato(tdatoPersistencia.forTipodatoEntity(c.getTipoDato()));
		casilla.setUsuario(persistenciaBean.forUsuarioEntity(c.getUsuario()));
		return casilla;
	}
	
	public CasillaEntity forCasillaEntity(Casilla c) {
		CasillaEntity casilla = new CasillaEntity();
		casilla.setIdCasilla(c.getIdCasilla());
		casilla.setNombre(c.getNombre());
		casilla.setDescripcion(c.getDescripcion());
		casilla.setDisponible(c.isDisponible());
		casilla.setParametro(parametroPersistencia.forParametroEntity(c.getParametro()));
		casilla.setTipoDato(tdatoPersistencia.forTipodatoEntity(c.getTipoDato()));
		casilla.setUsuario(persistenciaBean.forUsuarioEntity(c.getUsuario()));
		return casilla;
	}
	
	public void actualizarCasilla(Casilla c) throws Exception {
		CasillaEntity casilla = casillaBean.obtenerCasilla(c.getIdCasilla());
		casilla.setNombre(c.getNombre());
		casilla.setDescripcion(c.getDescripcion());
		casilla.setDisponible(c.isDisponible());
		casilla.setParametro(parametroPersistencia.forParametroEntity(c.getParametro()));
		casilla.setTipoDato(tdatoPersistencia.forTipodatoEntity(c.getTipoDato()));
		casilla.setUsuario(persistenciaBean.forUsuarioEntity(c.getUsuario()));
		casillaBean.actualizar(casilla);
	}
	
	public Casilla agregarCasilla(Casilla casilla) throws Exception {
		CasillaEntity c = casillaBean.crear(toCasillaEntity(casilla));
		return fromCasillaEntity(c);
	}

	public List<Casilla> seleccionarCasillas() throws Exception {

		List<CasillaEntity> listaCasillasEntities = casillaBean.buscarCasillasHabilitadas();

		List<Casilla> listaCasillas = new ArrayList<Casilla>();

		for (CasillaEntity casillaEntity : listaCasillasEntities) {
			listaCasillas.add(fromCasillaEntity(casillaEntity));
		}
		return listaCasillas;
	}
	

	public List<Casilla> seleccionarCasillasFormulario(String nombre) throws Exception {

		List<CasillaEntity> listaCasillasEntities = casillaBean.buscarCasillasFormulario(nombre);

		List<Casilla> listaCasillas = new ArrayList<Casilla>();

		for (CasillaEntity casillaEntity : listaCasillasEntities) {
			listaCasillas.add(fromCasillaEntity(casillaEntity));
		}
		return listaCasillas;
	}

	public Casilla buscarCasillaEntityName(String name) throws Exception {
		try {
			CasillaEntity casilla = casillaBean.buscarCasillaNombre(name);
			return fromCasillaEntity(casilla);
		} catch (PersistenceException | NullPointerException e)
		{
			Casilla c = new Casilla();
			return c;
		}
	}
	public Casilla buscarCasillaEntityId(Long id) throws Exception {
		try {
			CasillaEntity casilla = casillaBean.obtenerCasilla(id);
			return fromCasillaEntity(casilla);
		} catch (PersistenceException | NullPointerException e)
		{
			Casilla c = new Casilla();
			return c;
		}
	}
	public void elminarCasillaEntity(Long id) throws Exception {
		casillaBean.eliminar(id);

	}
}
