package com.capa1presentacion;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.capa2LogicaNegocio.GestionCasillaService;
import com.capa2LogicaNegocio.GestionFormularioService;
import com.capa2LogicaNegocio.GestionParametroService;
import com.capa2LogicaNegocio.GestionTipoDatoService;
import com.capa3Persistencia.entities.CasillaEntity;
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

	@Inject
	GestionFormularioService formularioPersistencia;

	private Casilla casillaSeleccionada;

	private String parametroUsuario;

	private String tipoDatoUsuario;

	private String unidadMedidaValue;

	private boolean checkFilter = false;

	private boolean filterActive = false;

	private List<Casilla> filtroCasillas;

	public List<Casilla> getFiltroCasillas() {
		return filtroCasillas;
	}

	public void setFiltroCasillas(List<Casilla> filtroCasillas) {
		this.filtroCasillas = filtroCasillas;
	}

	@PostConstruct
	public void init() {
		casillaSeleccionada = new Casilla();
	}

	// GUARDAR NUEVA CASILLA \\
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
				if (t.getNombre().equals(tipoDatoUsuario)) {
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
					"Se ha agregado una nueva casilla con exito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			// Guardar el mensaje en el flash scope
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			// Redirigir a listadoCasillas.xhtml usando ExternalContext
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			return null; // Retornar null para indicar que no hay que procesar la respuesta

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

	public void eliminarCasilla(String idCasilla) throws Exception {

		try {
			boolean casillaFormulario = false;
			List<Formulario> formularios = formularioPersistencia.seleccionarFormularios(); // Traemos formularios de la
																							// BD
			for (Formulario f : formularios) {
				List<CasillaEntity> casillasForm = f.getCasillas();// Traemos todas las casillas asignadas a formularios
				for (CasillaEntity c : casillasForm) {
					System.out.println(c);
					if (Long.parseLong(idCasilla) == c.getIdCasilla()) { // Si la casilla que queremos eliminar esta
																			// asignada a un formulario
						FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
								"La casilla esta asignada a un formulario, no se puede eliminar", "");
						FacesContext.getCurrentInstance().addMessage(null, facesMsg);
						casillaFormulario = true;
						break;
					}
				}
			}

			if (!casillaFormulario) {
				casillaPersistencia.elminarCasillaEntity(Long.parseLong(idCasilla));
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Casilla eliminada con éxito", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
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

	}

	// ACTUALIZACION DE CASILLAS\\
	public String actualizarCasilla() throws Exception {

		Casilla casillaNueva;
		try {

			List<Parametro> parametros = parametroPersistencia.seleccionarParametros();
			for (Parametro p : parametros) {
				if (p.getNombre().equals(casillaSeleccionada.getParametro().getNombre())) {
					casillaSeleccionada.setParametro(p);
					break;
				}
			}

			List<TipoDato> tipoDatos = tipoDatoPersistencia.seleccionarTipoDato();
			for (TipoDato t : tipoDatos) {
				if (t.getNombre().equals(casillaSeleccionada.getTipoDato().getNombre())) {
					casillaSeleccionada.setTipoDato(t);
				}
			}

			casillaSeleccionada.setUsuario(CurrentUser.getUsuario());
			casillaPersistencia.actualizarCasilla(casillaSeleccionada);
			// actualizamos id
			Long nuevoId = casillaSeleccionada.getIdCasilla();
			// vaciamos usuarioSeleccionado como para ingresar uno nuevo
			casillaSeleccionada = new Casilla();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Casilla actualizada con exito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().redirect("listadoCasillas.xhtml");

			return null; // Retornar null para indicar que no hay que procesar la respuesta

		} catch (PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		}

		return "";
	}

	public void mostrarFiltrosInput() throws IOException {
		this.filterActive = this.checkFilter;
		if (filterActive == false) {
			filtroCasillas = null;
			FacesContext.getCurrentInstance().getExternalContext().redirect("listadoCasillas.xhtml");
		}

	}

	public void renderUnidadMedida() {
			if(tipoDatoUsuario.toString().equals("g/m3")) {
				unidadMedidaValue = "ENTERO";
			}else {
				unidadMedidaValue = "DECIMAL";
			}
	}

	public String actualizarVistaCasilla(Long id) throws Exception {
		casillaSeleccionada = casillaPersistencia.buscarCasillaEntityId(id);

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.redirect("actualizarCasilla.xhtml");
		return null;
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

	public boolean isCheckFilter() {
		return checkFilter;
	}

	public void setCheckFilter(boolean checkFilter) {
		this.checkFilter = checkFilter;
	}

	public boolean isFilterActive() {
		return filterActive;
	}

	public void setFilterActive(boolean filterActive) {
		this.filterActive = filterActive;
	}

	public String getUnidadMedidaValue() {
		return unidadMedidaValue;
	}

	public void setUnidadMedidaValue(String unidadMedidaValue) {
		this.unidadMedidaValue = unidadMedidaValue;
	}

}
