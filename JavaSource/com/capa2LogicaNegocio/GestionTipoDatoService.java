package com.capa2LogicaNegocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa1presentacion.Parametro;
import com.capa1presentacion.TipoDato;
import com.capa3Persistencia.entities.ParametroEntity;
import com.capa3Persistencia.entities.ParametrosBean;
import com.capa3Persistencia.entities.TipoDatoEntity;
import com.capa3Persistencia.entities.TiposDatoBean;

@Stateless
@LocalBean
public class GestionTipoDatoService {
	
	@EJB
	TiposDatoBean tipoDatoBean;
	
	public TipoDato fromTipodatoEntity(TipoDatoEntity p) {
		TipoDato tdato = new TipoDato();
		tdato.setIdDato(p.getIdDato());
		tdato.setNombre(p.getNombre());
		tdato.setUnidadMedida(p.getUnidadMedida());
		return tdato;
	}
	
	public TipoDatoEntity toTipodatoEntity(TipoDato t) {
		TipoDatoEntity tdato = new TipoDatoEntity();
		tdato.setNombre(t.getNombre());
		tdato.setUnidadMedida(t.getUnidadMedida());
		return tdato;
	}
	
	public TipoDatoEntity forTipodatoEntity(TipoDato t) {
		TipoDatoEntity tdato = new TipoDatoEntity();
		tdato.setIdDato(t.getIdDato());
		tdato.setNombre(t.getNombre());
		tdato.setUnidadMedida(t.getUnidadMedida());
		return tdato;
	}
	public TipoDato agregarTipoDato(TipoDato tdato) throws Exception {
		TipoDatoEntity e = tipoDatoBean.crear(toTipodatoEntity(tdato));
		return fromTipodatoEntity(e);
	}

	public TipoDato buscarTdatoEntity(Long id) throws Exception {
		TipoDatoEntity t = tipoDatoBean.obtenerTipoDato(id);
		return fromTipodatoEntity(t);
	}
	
	public List<TipoDato> seleccionarTipoDato() throws Exception {

		List<TipoDatoEntity> listaTipoDatoEntity = tipoDatoBean.obtenerTodos();

		List<TipoDato> listaTipoDato = new ArrayList<>();

		for (TipoDatoEntity p : listaTipoDatoEntity) {
			listaTipoDato.add(fromTipodatoEntity(p));
		}
		return listaTipoDato;
	}
}
