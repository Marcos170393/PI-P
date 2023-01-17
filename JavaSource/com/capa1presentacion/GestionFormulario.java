package com.capa1presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Enumerated;

import com.capa2LogicaNegocio.GestionCasillaService;
import com.capa2LogicaNegocio.GestionDepartamentoService;
import com.capa2LogicaNegocio.GestionFormularioService;
import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.entities.CasillasBean;
import com.capa3Persistencia.exception.PersistenciaException;

import com.utils.ExceptionsTools;

@Named(value = "gestionFormulario") // JEE8
@SessionScoped // JEE8
public class GestionFormulario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	GestionFormularioService formularioPersistencia;

	@Inject
	GestionCasillaService casillaPersistencia;
	
	@Inject
	GestionDepartamentoService departamentoPersistencia;

	private Formulario formularioSeleccionado;
	
	private String departamentoFormulario;

	@Enumerated
	private TipoMedicion manual = TipoMedicion.MANUAL;

	@Enumerated
	private TipoMedicion automatica = TipoMedicion.AUTOMATICO;

	private static List<CasillaEntity> casillasFormulario = new ArrayList<>();
	
	@EJB
	CasillasBean casillasBean = new CasillasBean(); 
	
	@PostConstruct
	public void init() {
		formularioSeleccionado = new Formulario();
		casillasFormulario = new ArrayList<>();
	}


	// GUARDAR NUEVA CASILLA \\
	public String salvarCambios() throws Exception {

		Formulario formularioNuevo;
		try {

			List<Departamento> dptos = departamentoPersistencia.buscarDepartamentos();
			for (Departamento departamento : dptos) {
				if(departamento.getNombre().equals(departamentoFormulario)) {
					formularioSeleccionado.setDepartamento(departamento);
				}
			}

			formularioSeleccionado.setUsuario(CurrentUser.getUsuario());
			formularioSeleccionado.setCasillas(casillasFormulario);

			formularioNuevo = (Formulario) formularioPersistencia.agregarFormulario(formularioSeleccionado);
			// actualizamos id
			Long nuevoId = formularioNuevo.getIdFormulario();
			// vaciamos usuarioSeleccionado como para ingresar uno nuevo
			formularioSeleccionado = new Formulario();
			casillasFormulario = new ArrayList<>();
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se ha agregado un nuevo formulario con éxito", "");
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
	
	
	// MOSTRAR LISTADO DE FORMULARIOS \\
	public List<Formulario> mostrarFormularios() throws Exception {

		try {
			List<Formulario> listaFormulario = formularioPersistencia.seleccionarFormularios();

			return listaFormulario;
		} catch (PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			e.printStackTrace();
			return new ArrayList<Formulario>();
		}
	}

	public void eliminarFormulario(String idCasilla) throws Exception {

		try {
			formularioPersistencia.eliminarFormulario(Long.parseLong(idCasilla));
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Formulario eliminado con éxito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
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
	
	public String agregarCasillasFormulario(Long idCasilla) throws Exception {

		try {
			
			//Buscamos la casilla en la que presionamos agregar y la agregamos al listado																																					
			CasillaEntity casilla = casillasBean.obtenerCasilla(idCasilla);
				casillasFormulario.add(casilla);
			
	
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Casilla agregada con exito", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "";
		} catch (Exception e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
	}

		public List<CasillaEntity> mostrarCasillasFormulario() throws Exception {

			try {
				//Cargamos la lista estatica de casillas del formulario
				List<CasillaEntity> list = casillasFormulario;
				return list;
				
			} catch (Exception e) {

				Throwable rootException = ExceptionsTools.getCause(e);
				String msg1 = e.getMessage();
				String msg2 = ExceptionsTools.formatedMsg(rootException);
				// mensaje de actualizacion correcta
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				e.printStackTrace();
				return new ArrayList<CasillaEntity>();
			}
		}

		public void eliminarCasillaFormulario(Long idCasilla) throws Exception {

			try {
				for(CasillaEntity c : casillasFormulario ) {
					if(c.getIdCasilla() == idCasilla) {
						casillasFormulario.remove(c);
					}
				}				
	
			} catch (Exception e) {

				Throwable rootException = ExceptionsTools.getCause(e);
				String msg1 = e.getMessage();
				String msg2 = ExceptionsTools.formatedMsg(rootException);
				// mensaje de actualizacion correcta
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				e.printStackTrace();
			}
		}

		public List<Departamento> listaDepartamentos() throws Exception{
			List<Departamento> listaDptos = departamentoPersistencia.buscarDepartamentos();
			return listaDptos;
		}
		
		
		
	public String actualizarVista() throws Exception {
		return "listadoCasillasFormulario";
		}
		
	public String getDepartamentoFormulario() {
		return departamentoFormulario;
	}

	public void setDepartamentoFormulario(String metodoMedicion) {
		this.departamentoFormulario = metodoMedicion;
	}

	public Formulario getFormularioSeleccionado() {
		return formularioSeleccionado;
	}

	public void setFormularioSeleccionado(Formulario formularioSeleccionado) {
		this.formularioSeleccionado = formularioSeleccionado;
	}

	public TipoMedicion getManual() {
		return manual;
	}

	public void setManual(TipoMedicion manual) {
		this.manual = manual;
	}

	public TipoMedicion getAutomatica() {
		return automatica;
	}

	public void setAutomatica(TipoMedicion automatica) {
		this.automatica = automatica;
	}

	public static List<CasillaEntity> getCasillasFormulario() {
		return casillasFormulario;
	}

	public static void setCasillasFormulario(List<CasillaEntity> casillasFormulario) {
		GestionFormulario.casillasFormulario = casillasFormulario;
	}

}
