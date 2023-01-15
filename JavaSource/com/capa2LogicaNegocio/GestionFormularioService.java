package com.capa2LogicaNegocio;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.capa1presentacion.Formulario;
import com.capa3Persistencia.entities.FormularioEntity;
import com.capa3Persistencia.entities.FormulariosBean;

@Stateless
@LocalBean
public class GestionFormularioService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	FormulariosBean formularioBean = new FormulariosBean();
	
	@Inject
	GestionCasillaService casillasPersistencia;
	
	public Formulario fromFormularioEntity(FormularioEntity form) {
		Formulario formulario = new Formulario();
		formulario.setIdFormulario(form.getIdFormulario());
		formulario.setNombre(form.getNombre());
		formulario.setDisponible(form.isDisponible());
		formulario.setFechaHora(form.getFechaHora());
		formulario.setMetodoMedicion(form.getMetodoMedicion());
		formulario.setResumen(form.getResumen());
		//formulario.setCasillas(casillasPersistencia.forCasillaEntity(form.getCasillas()));
		return formulario;
	}

}
