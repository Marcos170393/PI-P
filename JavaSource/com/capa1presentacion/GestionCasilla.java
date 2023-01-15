package com.capa1presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.capa2LogicaNegocio.GestionCasillaService;
import com.capa2LogicaNegocio.GestionParametroService;
import com.capa2LogicaNegocio.GestionTipoDatoService;
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

	@Inject
	GestionParametroService parametroPersistencia;

	@Inject
	GestionTipoDatoService tipoDatoPersistencia;
	
	private Casilla casillaSeleccionada;

	private String parametroUsuario;

	private String tipoDatoUsuario;

	@PostConstruct
	public void init() {
		casillaSeleccionada = new Casilla();
	}

	// GUARDAR NUEVO USUARIO \\
	public String salvarCambios() throws Exception {

		Casilla casillaNueva;
		try {

			List<Parametro> parametros = parametroPersistencia.seleccionarParametros();
			for (Parametro p : parametros) {
				if (p.getNombre().equals(parametroUsuario)) {
					casillaSeleccionada.setParametro(p);
					break;
				}
			}
			
			List<TipoDato> tipoDatos = tipoDatoPersistencia.seleccionarTipoDato();
			for (TipoDato t : tipoDatos) {
				if(t.getNombre().equals(tipoDatoUsuario)) {
					casillaSeleccionada.setTipoDato(t);
				}
			}
			
			casillaSeleccionada.setUsuario(CurrentUser.getUsuario());
			
			casillaNueva = (Casilla) casillaPersistencia.agregarCasilla(casillaSeleccionada);
			// actualizamos id
			Long nuevoId = casillaNueva.getIdCasilla();
			// vaciamos usuarioSeleccionado como para ingresar uno nuevo
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

	public List<Parametro> listaParametros() throws Exception {
		List<Parametro> listaParametros = parametroPersistencia.seleccionarParametros();
		return listaParametros;
	}
	

	public List<TipoDato> listaTipoDato() throws Exception {
		List<TipoDato> listaTipoDato = tipoDatoPersistencia.seleccionarTipoDato();
		return listaTipoDato;
	}

	public Casilla getCasillaSeleccionada() {
		return casillaSeleccionada;
	}

	public void setCasillaSeleccionada(Casilla casillaSeleccionada) {
		this.casillaSeleccionada = casillaSeleccionada;
	}

	public String getParametroUsuario() {
		return parametroUsuario;
	}

	public void setParametroUsuario(String parametroUsuario) {
		this.parametroUsuario = parametroUsuario;
	}

	public String getTipoDatoUsuario() {
		return tipoDatoUsuario;
	}

	public void setTipoDatoUsuario(String tipoDatoUsuario) {
		this.tipoDatoUsuario = tipoDatoUsuario;
	}

}
