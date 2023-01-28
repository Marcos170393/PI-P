package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.capa1presentacion.Formulario;
import com.capa1presentacion.Registro;
import com.capa3Persistencia.entities.FormularioEntity;
import com.capa3Persistencia.entities.RegistroEntity;
import com.capa3Persistencia.entities.RegistrosBean;

@Stateless
@LocalBean
public class GestionRegistroService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	RegistrosBean registroBean = new RegistrosBean();

	@Inject
	GestionCasillaService casillaPersistencia;

	@Inject
	GestionFormularioService formularioPersistencia;

	@Inject
	GestionUsuarioService usuarioPersistencia;

	public Registro fromRegistroEntity(RegistroEntity reg) {
		Registro registro = new Registro();
		registro.setIdRegistro(reg.getIdRegistro());
		registro.setFecha(reg.getFecha());
		registro.setValor(reg.getValor());
		registro.setUk_registro(reg.getUk_registro());
		registro.setCasilla(casillaPersistencia.fromCasillaEntity(reg.getCasilla()));
		registro.setFormulario(formularioPersistencia.fromFormularioEntity(reg.getFormulario()));
		registro.setUsuario(usuarioPersistencia.fromUsuarioEntity(reg.getUsuario()));
		return registro;
	}

	public RegistroEntity toRegistroEntity(Registro reg) {
		RegistroEntity registro = new RegistroEntity();
		registro.setFecha(new Date());
		registro.setValor(reg.getValor());
		registro.setUk_registro(reg.getUk_registro());
		registro.setCasilla(casillaPersistencia.forCasillaEntity(reg.getCasilla()));
		registro.setFormulario(formularioPersistencia.forFormularioEntity(reg.getFormulario()));
		registro.setUsuario(usuarioPersistencia.forUsuarioEntity(reg.getUsuario()));
		return registro;

	}

	public Registro agregarRegistro(Registro reg) {
		RegistroEntity registro = registroBean.crear(toRegistroEntity(reg));
		return fromRegistroEntity(registro);
	}

	public List<Registro> seleccionarRegistros() throws Exception {

		List<RegistroEntity> listaRegistrosEntity = registroBean.obtenerTodos();

		List<Registro> listaRegistros = new ArrayList<Registro>();

		for (RegistroEntity r : listaRegistrosEntity) {
			listaRegistros.add(fromRegistroEntity(r));
		}
		return listaRegistros;
	}

	public List<Registro> seleccionarRegistrosIdFormulario(Long idFormulario) {
		List<RegistroEntity> listaRegistrosEntity = registroBean.obtenerRegistroPorFormulario(idFormulario);

		List<Registro> listaRegistros = new ArrayList<Registro>();

		for (RegistroEntity r : listaRegistrosEntity) {
			listaRegistros.add(fromRegistroEntity(r));
		}
		return listaRegistros;
	}

}
