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
	GestionDepartamentoService gestionDptoService;
	
	public Formulario fromFormularioEntity(FormularioEntity formulario) {
		Formulario f = new Formulario();
		f.setIdFormulario(formulario.getIdFormulario());
		f.setNombre(formulario.getNombre());
		f.setDisponible(formulario.isDisponible());
		f.setFechaHora(formulario.getFechaHora());
		f.setResumen(formulario.getResumen());
		f.setUsuario(formulario.getUsuario());
		f.setMetodoMedicion(formulario.getMetodoMedicion());
		f.setDepartamento(gestionDptoService.fromDepartamentoEntity(formulario.getDepartamento()));
		f.setCasillas(formulario.getCasillas());
		return f;
	}
	
	public FormularioEntity toFormularioEntity(Formulario formulario) {
		FormularioEntity f = new FormularioEntity();
		f.setIdFormulario(formulario.getIdFormulario());
		f.setNombre(formulario.getNombre());
		f.setDisponible(formulario.isDisponible());
		f.setFechaHora(formulario.getFechaHora());
		f.setResumen(formulario.getResumen());
		f.setUsuario(formulario.getUsuario());
		f.setMetodoMedicion(formulario.getMetodoMedicion());
		f.setDepartamento(gestionDptoService.toDepartamentoEntity(formulario.getDepartamento()));
		f.setCasillas(formulario.getCasillas());
		return f;
	}
	
	public Formulario agregarFormulario(Formulario formulario) throws Exception {
		FormularioEntity form = formularioBean.crear(toFormularioEntity(formulario));
		return fromFormularioEntity(form);
	}
}
