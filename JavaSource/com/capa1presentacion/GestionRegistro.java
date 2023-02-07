package com.capa1presentacion;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.primefaces.component.datatable.DataTable;

import com.capa2LogicaNegocio.GestionCasillaService;
import com.capa2LogicaNegocio.GestionFormularioService;
import com.capa2LogicaNegocio.GestionRegistroService;
import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.entities.RegistrosBean;
import com.capa3Persistencia.exception.PersistenciaException;
import com.utils.ExceptionsTools;

@Named(value = "gestionRegistro") // JEE8
@SessionScoped // JEE8
public class GestionRegistro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	GestionFormularioService formularioPersistencia;

	@Inject
	GestionRegistroService registroPersistencia;

	@Inject
	GestionCasillaService casillaPersistencia;

	@EJB
	RegistrosBean registroBean;

	private static Formulario form;

	private Registro registroSeleccionado;

	private static List<CasillaEntity> casillasRegistro = new ArrayList<>();

	private static List<CasillaEntity> casillasRegistroObligatorias = new ArrayList<>();

	private List<Registro> listaRegistrosTipoDato = new ArrayList<>();

	private List<Registro> listaRegistrosSeleccionFormulario = new ArrayList<>();

	private String nombreTipoDato;

	private String nombreCasilla;

	private Long valorRegistro;

	private boolean obligatoria = false;

	@PostConstruct
	public void init() {
		registroSeleccionado = new Registro();
		casillasRegistro = new ArrayList<>();

	}

	// GUARDAR NUEVO REGISTRO DE CALIDAD DEL AIRE \\
	public String salvarCambios() throws Exception {

		Registro registroNuevo;
		Casilla c = new Casilla();
		try {

			for (CasillaEntity casilla : casillasRegistro) {
				if (casilla.getNombre().equals(nombreCasilla)) {
					c = casillaPersistencia.buscarCasillaEntityId(casilla.getIdCasilla());
				}

			}

			registroSeleccionado.setFormulario(form);
			registroSeleccionado.setUsuario(CurrentUser.getUsuario());
			registroSeleccionado.setUk_registro(1);
			registroSeleccionado.setValor(valorRegistro);
			registroSeleccionado.setCasilla(c);

			boolean incompleto = false;
			for (CasillaEntity casillas : form.getCasillasObligatorias()) {
				if (casillas.getIdCasilla() == c.getIdCasilla()) {
					if (valorRegistro == null) {
						incompleto = true;
						FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Tienes casillas obligatorias sin valor", "");
						FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					}
				}
			}
			if (!incompleto) {
				registroPersistencia.agregarRegistro(registroSeleccionado);
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro agregado", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}

			registroSeleccionado = new Registro();

			return "";

		} catch (Exception e) {

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

	// DIRIGIMOS AL MENU\\
	public String confirmarCambios() throws Exception {

		try {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambios guardados", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "home";

		} catch (Exception e) {

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

	// Guardamos el ID del formulario seleccionado para luego cargarlo en
	// "crearRegistro"
	public String seleccionarRegistro(Long idFormulario) throws Exception {

		Formulario f = formularioPersistencia.buscarFormularioEntityId(idFormulario);
		casillasRegistro = f.getCasillas();
		form = f;
		casillasRegistroObligatorias = f.getCasillasObligatorias();
		return "crearRegistro";
	}

	// Cuando seleccionamos un tipo de dato traemos los registros coincidentes con
	// el comboBox y lo agregamos al listado
	public void buscar() throws Exception {
		List<Registro> listaRegistrosEntity = registroPersistencia.seleccionarRegistros();
		boolean coincide = false;
		for (Registro r : listaRegistrosEntity) {
			if (r.getCasilla().getTipoDato().getNombre().equals(nombreTipoDato)) {
				coincide = true;
				if(coincide) {
					listaRegistrosTipoDato.add(r);
				}
			}
		}
		if(!coincide) {
			listaRegistrosTipoDato.clear();
		}
	}

	// Cargamos el listado de formularios por tipo de dato
	public List<Registro> cargarListado() {
		return listaRegistrosTipoDato;
	}

	public List<CasillaEntity> cargarDatos() {
		return casillasRegistro;
	}

	// Buscamos los registros que tiene el formulario seleccionado en
	// "listarRegistroSeleccionarFormulario" (traemos el id para buscar el
	// formulario seleccionado)
	public String listarRegistrosSeleccionFormulario(Long idFormulario) {
		List<Registro> listado = registroPersistencia.seleccionarRegistrosIdFormulario(idFormulario);// Metodo que trae
																										// la lista de
		listaRegistrosSeleccionFormulario.clear(); // limpiamos listado antes de agregar registros // registros
		for (Registro registro : listado) {
			listaRegistrosSeleccionFormulario.add(registro);
		}
		return "listadoRegistrosFormularioSeleccionado";
	}

	// Cargamos el listado de registros en "listadoRegistrosFormulario" obtenidos
	// del metodo de arriba
	public List<Registro> cargarListadoRegistrosFormulario() {
		return listaRegistrosSeleccionFormulario;
	}
	
	public List<Registro> getListaRegistros() {
		return listaRegistrosTipoDato;
	}

	public void setListaRegistros(List<Registro> listaRegistros) {
		this.listaRegistrosTipoDato = listaRegistros;
	}

	public Registro getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(Registro registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public String getNombreTipoDato() {
		return nombreTipoDato;
	}

	public void setNombreTipoDato(String nombreTipoDato) {
		this.nombreTipoDato = nombreTipoDato;
	}

	public String getNombreCasilla() {
		return nombreCasilla;
	}

	public void setNombreCasilla(String nombreCasilla) {
		this.nombreCasilla = nombreCasilla;
	}

	public Long getValorRegistro() {
		return valorRegistro;
	}

	public void setValorRegistro(Long valorRegistro) {
		this.valorRegistro = valorRegistro;
	}

	public boolean isObligatoria() {
		return obligatoria;
	}

	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public static List<CasillaEntity> getCasillasRegistroObligatorias() {
		return casillasRegistroObligatorias;
	}

	public static void setCasillasRegistroObligatorias(List<CasillaEntity> casillasRegistroObligatorias) {
		GestionRegistro.casillasRegistroObligatorias = casillasRegistroObligatorias;
	}

}
