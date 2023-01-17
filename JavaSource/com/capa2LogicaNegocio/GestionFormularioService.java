package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.capa1presentacion.Casilla;
import com.capa1presentacion.Formulario;
import com.capa3Persistencia.entities.CasillaEntity;
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
	
	@Inject
	GestionDepartamentoService departamentoPersistencia;
	
	
	@Inject
	GestionUsuarioService usuarioPersistencia;
	
	public Formulario fromFormularioEntity(FormularioEntity form) {
		Formulario formulario = new Formulario();
		formulario.setIdFormulario(form.getIdFormulario());
		formulario.setNombre(form.getNombre());
		formulario.setDisponible(form.isDisponible());
		formulario.setFechaHora(new Date());
		formulario.setMetodoMedicion(form.getMetodoMedicion());
		formulario.setResumen(form.getResumen());
		formulario.setCasillas(form.getCasillas());
		formulario.setCasillasObligatorias(form.getCasillasObligatorias());
		formulario.setDepartamento(departamentoPersistencia.fromDepartamentoEntity(form.getDepartamento()));
		formulario.setUsuario(usuarioPersistencia.fromUsuarioEntity(form.getUsuario()));
		return formulario;
	}
	
	public FormularioEntity toFormularioEntity(Formulario form) {
		FormularioEntity formulario = new FormularioEntity();
		formulario.setIdFormulario(form.getIdFormulario());
		formulario.setNombre(form.getNombre());
		formulario.setDisponible(true);
		formulario.setFechaHora(new Date());
		formulario.setMetodoMedicion(form.getMetodoMedicion());
		formulario.setResumen(form.getResumen());
		formulario.setCasillas(form.getCasillas());
		formulario.setCasillasObligatorias(form.getCasillasObligatorias());
		formulario.setDepartamento(departamentoPersistencia.forDepartamentoEntity(form.getDepartamento()));
		formulario.setUsuario(usuarioPersistencia.forUsuarioEntity(form.getUsuario()));
		return formulario;
	}
	
	public Formulario agregarFormulario(Formulario formulario) throws Exception {
		FormularioEntity form = formularioBean.crear(toFormularioEntity(formulario));
		return fromFormularioEntity(form);
	}
	
	public List<Formulario> seleccionarFormularios() throws Exception {

		List<FormularioEntity> listaFormulariosEntities = formularioBean.buscarFormulariosDisponibles();

		List<Formulario> listaFormularios = new ArrayList<Formulario>();

		for (FormularioEntity f : listaFormulariosEntities) {
			listaFormularios.add(fromFormularioEntity(f));
		}
		return listaFormularios;
	}
	
	public void eliminarFormulario(Long idFormulario) throws Exception {
		formularioBean.eliminar(idFormulario);
	}
}
