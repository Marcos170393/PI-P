package com.capa1presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.capa2LogicaNegocio.GestionCasillaService;
import com.capa2LogicaNegocio.GestionParametroService;
import com.capa2LogicaNegocio.GestionTipoDatoService;
import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.entities.CasillasBean;
import com.capa3Persistencia.entities.CiudadEntity;
import com.capa3Persistencia.entities.ParametroEntity;
import com.capa3Persistencia.entities.ParametrosBean;
import com.capa3Persistencia.entities.TipoDatoEntity;
import com.capa3Persistencia.entities.TiposDatoBean;
import com.capa3Persistencia.entities.UsuarioEntity;
import com.capa3Persistencia.entities.UsuariosBean;
import com.capa3Persistencia.exception.PersistenciaException;
import com.utils.ExceptionsTools;

@Named(value = "gestionCasilla") // JEE8
@SessionScoped // JEE8
public class GestionCasilla implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	GestionCasillaService casillaPersistencia;

	@EJB
	CasillasBean casillaBean;

	@EJB
	UsuariosBean usuarioBean;

	@EJB
	ParametrosBean parametroBean;

	@EJB
	TiposDatoBean tipoDatoBean;

	private String parametro;

	private String tipoDato;

	private String usuario;

	ParametroEntity parametroEntity;

	TipoDatoEntity tipoDatoEntity;

	UsuarioEntity usuarioEntity;

	private Casilla casillaSeleccionada;

	@PostConstruct
	public void init() {
		casillaSeleccionada = new Casilla();
	}

	// GUARDAR NUEVO USUARIO \\
	public String salvarCambios() throws Exception {

		try {
			List<TipoDatoEntity> tDato = tipoDatoBean.obtenerTodos();
			for (TipoDatoEntity t : tDato) {
				if (t.getNombre().equals(tipoDato)) {
					casillaSeleccionada.setTipoDato(t);
					break;
				}
			}

			List<ParametroEntity> parametros = parametroBean.buscarTodos();
			for (ParametroEntity p : parametros) {
				if (p.getNombre().equals(parametro)) {
					casillaSeleccionada.setParametro(p);
					break;
				}
			}
			List<UsuarioEntity> listUsuario = usuarioBean.buscarTodos();
			for (UsuarioEntity u : listUsuario) {
				if (CurrentUser.getUsuario().getNombreUsuario().equals(u.getNombreUsuario())) {
					casillaSeleccionada.setUsuario(u);
					break;
				}
			}

			casillaPersistencia.agregarCasilla(casillaSeleccionada);
			// actualizamos id
			Long nuevoId = casillaSeleccionada.getIdCasilla();
			// vaciamos casillaSeleccionada como para ingresar una nueva
			casillaSeleccionada = new Casilla();

			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se ha agregado una nueva casilla con éxito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "home";

		} catch (PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();

		} finally {

		}
		return "";
	}

	// MOSTRAR LISTADO DE CASILLAS \\
	public List<Casilla> mostrarCasillas() throws Exception {

		try {

			List<Casilla> listaCasillas = casillaPersistencia.seleccionarCasillas();

			return listaCasillas;

		} catch (PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			e.printStackTrace();
			return new ArrayList<Casilla>();
		}

	}

	// METODO PARA MOSTRAR FORMULARIO DE ACTUALIZACION
	public String actualizarVistaCasilla(String id) throws Exception {
		casillaSeleccionada = casillaPersistencia.buscarCasillaEntityId(Long.parseLong(id));
		return "actualizarCasilla";
	}

	// MODIFICACION DE USUARIO \\
	public String actualizarCasilla() throws Exception {

		try {
			List<TipoDatoEntity> tDato = tipoDatoBean.obtenerTodos();
			for (TipoDatoEntity t : tDato) {
				if (t.getNombre().equals(tipoDato)) {
					casillaSeleccionada.setTipoDato(t);
					break;
				}
			}

			List<ParametroEntity> parametros = parametroBean.buscarTodos();
			for (ParametroEntity p : parametros) {
				if (p.getNombre().equals(parametro)) {
					casillaSeleccionada.setParametro(p);
					break;
				}
			}
			List<UsuarioEntity> listUsuario = usuarioBean.buscarTodos();
			for (UsuarioEntity u : listUsuario) {
				if (CurrentUser.getUsuario().getNombreUsuario().equals(u.getNombreUsuario())) {
					casillaSeleccionada.setUsuario(u);
					break;
				}
			}

			casillaPersistencia.actualizarCasilla(casillaSeleccionada);
			casillaSeleccionada = null;
			casillaSeleccionada = new Casilla();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Casilla actualizada con éxito", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listadoCasillas";

		} catch (PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		} finally {

		}
		return "";
	}

	public void eliminarCasilla(Long id) throws Exception {

		try {
			Casilla c = casillaPersistencia.buscarCasillaEntityId(id);
			casillaPersistencia.eliminarCasilla(c.getIdCasilla());
			
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Casilla eliminada con éxito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		} catch (

		PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		} finally {

		}

	}

	public List<ParametroEntity> listParametros() throws Exception {
		List<ParametroEntity> lista = parametroBean.buscarTodos();
		return lista;
	}

	public List<TipoDatoEntity> listTipodatos() throws Exception {
		List<TipoDatoEntity> lista = tipoDatoBean.obtenerTodos();
		return lista;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Casilla getCasillaSeleccionada() {
		return casillaSeleccionada;
	}

	public void setCasillaSeleccionada(Casilla casillaSeleccionada) {
		this.casillaSeleccionada = casillaSeleccionada;
	}

}
