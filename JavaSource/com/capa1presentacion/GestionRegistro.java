package com.capa1presentacion;

import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.capa2LogicaNegocio.GestionCasillaService;
import com.capa2LogicaNegocio.GestionFormularioService;
import com.capa2LogicaNegocio.GestionRegistroService;
import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.entities.RegistrosBean;
import com.capa3Persistencia.exception.PersistenciaException;
import com.utils.ExceptionsTools;
import com.utils.ImportarDatos;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.model.UploadedFile;

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

	private static Formulario formModificar;

	private Registro registroSeleccionado;

	private static List<CasillaEntity> casillasRegistro = new ArrayList<>();

	private static List<CasillaEntity> casillasRegistroObligatorias = new ArrayList<>();

	private static List<CasillaEntity> casillasRegistroModificar = new ArrayList<>();

	private static List<CasillaEntity> casillasRegistroObligatoriasModificar = new ArrayList<>();

	private static List<Registro> registrosAgregados = new ArrayList<>();

	private List<Registro> listaRegistrosTipoDato = new ArrayList<>();

	private static List<Registro> listaRegistrosSeleccionFormulario = new ArrayList<>();

	private static List<Registro> listadoRegistrosTodos = new ArrayList<>();

	private String nombreTipoDato;

	private String nombreCasilla;

	private Long valorRegistro;

	private boolean obligatoria;

	private String nombreDepartamento;

	private List<Registro> listaRegistrosDepartamento = new ArrayList<>();

	private static List<Registro> listaRegistrosFecha = new ArrayList<>();

	private Date fechaInicial;

	private Date fechaFinal;

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	@EJB
	ImportarDatos importarDatos;

	@PostConstruct
	public void init() {
		registroSeleccionado = new Registro();
		casillasRegistro = new ArrayList<>();

	}

	// GUARDAR NUEVO REGISTRO DE CALIDAD DEL AIRE \\
	public String agregarRegistroCA() throws Exception {

		try {
			boolean incompleto = false;
			for (CasillaEntity casillasOb : casillasRegistroObligatorias) {
				for (CasillaEntity casillas : casillasRegistro) {
					if (casillasOb.getIdCasilla() == casillas.getIdCasilla()) {
						if (casillas.getValorRegistroCA() == null) {
							incompleto = true;
							// Si la casilla es obligatoria y el campo valor se encuentra vacio, salta el
							// siguiente mensaje.
							FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Debes ingresar valor en las casillas obligatorias", "");
							FacesContext.getCurrentInstance().addMessage(null, facesMsg);
						}
					}
				}
			}
			if (!incompleto) {
				int uk = registroBean.obtenerUk() + 1;

				for (CasillaEntity ce : casillasRegistro) {
					registroSeleccionado.setCasilla(casillaPersistencia.fromCasillaEntity(ce));
					registroSeleccionado.setFormulario(form);
					registroSeleccionado.setUsuario(CurrentUser.getUsuario());
					registroSeleccionado.setUk_registro(uk);
					registroSeleccionado.setValor(ce.getValorRegistroCA());

					registroPersistencia.agregarRegistro(registroSeleccionado);
				}
				casillasRegistro.clear();
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros creados con exito", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			}
			registroSeleccionado = new Registro();
			return null;

		} catch (Exception e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		} finally {
		}

		return "";
	}

	public String descartarCambios() throws IOException {

		casillasRegistro.clear();
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambios descartados", "");
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		return null; // Retornar null para indicar que no hay que procesar la respuesta
	}

	// MODIFICACION DE REGISTRO \\
	public String actualizarRegistro() throws Exception {
		try {
			boolean incompleto = false;
			for (CasillaEntity casillasOb : formModificar.getCasillasObligatorias()) {
				for (Registro registro : listaRegistrosSeleccionFormulario)
					if (registro.getCasilla().getIdCasilla() == casillasOb.getIdCasilla()) {
						if (registro.getValor() == null) {
							incompleto = true;
							obligatoria = true;
							FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Tienes casillas obligatorias sin valor", "");
							FacesContext.getCurrentInstance().addMessage(null, facesMsg);
						}
					}
			}

			if (!incompleto) {
				for (Registro r : listaRegistrosSeleccionFormulario) {
					r.setUsuario(CurrentUser.getUsuario());
					registroPersistencia.actualizarRegistro(r);

				}

				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros actualizados con exito",
						"");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			}
			registroSeleccionado = new Registro();
			return null;

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

	// Guardamos el ID del formulario seleccionado para luego cargarlo en
	// "crearRegistro"
	public String seleccionarRegistro(Long idFormulario) throws Exception {

		Formulario f = formularioPersistencia.buscarFormularioEntityId(idFormulario);
		casillasRegistro = f.getCasillas();
		form = f;
		casillasRegistroObligatorias = f.getCasillasObligatorias();

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.redirect("crearRegistro.xhtml");
		return null;

	}

	// Cuando seleccionamos un tipo de dato traemos los registros coincidentes con
	// el comboBox y lo agregamos al listado
	public void buscar() throws Exception {
		List<Registro> listaRegistrosEntity = registroPersistencia.seleccionarRegistros();
		boolean coincide = false;
		listaRegistrosTipoDato.clear();
		for (Registro r : listaRegistrosEntity) {
			if (r.getCasilla().getTipoDato().getNombre().equals(nombreTipoDato)) {
				coincide = true;
				if (coincide) {
					listaRegistrosTipoDato.add(r);
				}

			}
		}
		if (!coincide) {
			listaRegistrosTipoDato.clear();
		}
	}

	public void buscarRegistrosDepartamento() throws Exception {
		List<Registro> listaRegistros = registroPersistencia.seleccionarRegistros();
		boolean coincide = false;
		listaRegistrosDepartamento.clear();
		for (Registro registros : listaRegistros) {
			if (registros.getFormulario().getDepartamento().getNombre().equals(nombreDepartamento)) {
				coincide = true;
			}
			if (coincide) {
				listaRegistrosDepartamento.add(registros);
			} else {
				listaRegistrosDepartamento.clear();
			}
		}

	}

	// Cargamos el listado de registros por departamento
	public List<Registro> cargarListadoRegistrosDepto() {
		return listaRegistrosDepartamento;
	}

	public void buscarRegistrosFecha() throws Exception {
	    List<Registro> listado = registroPersistencia.seleccionarRegistros();

	    listaRegistrosFecha.clear();
	    for (Registro reg : listado) {
	        if ((reg.getFecha().equals(fechaInicial) || reg.getFecha().after(fechaInicial))
	                && (fechaFinal == null || reg.getFecha().equals(fechaFinal) || reg.getFecha().before(fechaFinal))) {
	            listaRegistrosFecha.add(reg);
	        } else {
	            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error, inténtelo nuevamente", "");
	            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	            listaRegistrosFecha.clear();
	            break;
	        }
	    }
	}

	public List<Registro> cargarListadoFecha() {
		return listaRegistrosFecha;
	}

	// Cargamos el listado de formularios por tipo de dato
	public List<Registro> cargarListado() {
		return listaRegistrosTipoDato;
	}

	public List<CasillaEntity> cargarDatos() {

		for (CasillaEntity casilla : casillasRegistro) { // Si la casilla es obligatoria, en el listado la ponemos como
															// true
			for (CasillaEntity casillaOb : casillasRegistroObligatorias) {
				if (casilla.getIdCasilla() == casillaOb.getIdCasilla()) {
					casilla.setObligatoria(true);
				}
			}
		}

		return casillasRegistro;
	}

	public String seleccionarFormularioModificarRegistro(Long idFormulario) throws Exception {

//Esta busqueda de form es simplemente para asignar las casillas a los listados para luego poder hacer el control de no dejar una casilla obligatoria vacia
		Formulario f = formularioPersistencia.buscarFormularioEntityId(idFormulario);
		casillasRegistroModificar = f.getCasillas();
		casillasRegistroObligatoriasModificar = f.getCasillasObligatorias();
		formModificar = f;

		List<Registro> listado = registroPersistencia.seleccionarRegistrosIdFormulario(idFormulario);
		listaRegistrosSeleccionFormulario.clear();
		for (Registro registro : listado) {
			for (CasillaEntity casillasOb : casillasRegistroObligatoriasModificar) {
				if (registro.getCasilla().getIdCasilla() == casillasOb.getIdCasilla()) {
					registro.setEsObligatoria(true);
				}
			}

			listaRegistrosSeleccionFormulario.add(registro);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.redirect("actualizarRegistro.xhtml");
		return null;

	}

	// Buscamos los registros que tiene el formulario seleccionado en
	// "listarRegistroSeleccionarFormulario" (traemos el id para buscar el
	// formulario seleccionado)
	public String listarRegistrosSeleccionFormulario(Long idFormulario) throws IOException {
		List<Registro> listado = registroPersistencia.seleccionarRegistrosIdFormulario(idFormulario);// Metodo que trae
																										// la lista de
																										// registros
		listaRegistrosSeleccionFormulario.clear(); // limpiamos listado antes de agregar registros
		for (Registro registro : listado) {
			listaRegistrosSeleccionFormulario.add(registro);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.redirect("listadoRegistrosFormularioSeleccionado.xhtml");

		return null;
	}

	protected String getFileName(Part p) {
		String submittedFileName = p.getSubmittedFileName();
		return Paths.get(submittedFileName).getFileName().toString();
	}

	private Part archivoSubido;

	public Part getArchivoSubido() { // Aca se guarda el excel cuando lo cargamos
		return archivoSubido;
	}

	public void setArchivoSubido(Part archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

	public void importarExcel() {
		try {
			String nombre = getFileName(archivoSubido); // Obtiene el nombre del archivo enviado en la parte Part y lo
														// devuelve como una cadena.
			System.out.println(nombre);
			archivoSubido.write("C:\\data\\" + getFileName(archivoSubido)); // El write() guarda el archivo en la
																			// ubicación "C:\data\" utilizando el nombre
																			// del archivo original.
			String nombreNuevo = getFileName(archivoSubido);
			importarDatos.importarDatos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Registro> listarRegistrosTodos() throws Exception {

		listadoRegistrosTodos = registroPersistencia.seleccionarRegistros();

		for (ListIterator<Registro> iter = listadoRegistrosTodos.listIterator(listadoRegistrosTodos.size()); iter
				.hasPrevious();) {
			Registro registro = iter.previous();
			if (CurrentUser.getUsuario().getRol() == Rol.AFICIONADO
					&& CurrentUser.getUsuario().getIdUsuario() != registro.getUsuario()
							.getIdUsuario()) { /*
												 * Si el id del usuario aficionado es distinto al id del usuario que
												 * creo el registro, borra el registro del listado para solo ver los
												 * suyos
												 */
				iter.remove();
			}
		}
		return listadoRegistrosTodos;
	}

	// Cargamos el listado de registros en "listadoRegistrosFormulario" obtenidos
	// del metodo de arriba
	public List<Registro> cargarListadoRegistrosFormulario() {

		for (ListIterator<Registro> iter = listaRegistrosSeleccionFormulario
				.listIterator(listaRegistrosSeleccionFormulario.size()); iter.hasPrevious();) {
			Registro registro = iter.previous();

			if (CurrentUser.getUsuario().getRol() == Rol.AFICIONADO
					&& CurrentUser.getUsuario().getIdUsuario() != registro.getUsuario().getIdUsuario()) {
				iter.remove();
			}
		}
		return listaRegistrosSeleccionFormulario;
	}

	public void eliminarRegistro(Long idRegistro) {

		for (Registro r : listaRegistrosSeleccionFormulario) {
			if (r.getIdRegistro() == idRegistro) {
				listaRegistrosSeleccionFormulario.remove(r); // Sacamos el registro del listado
				break;
			}
		}
		registroPersistencia.eliminarRegistro(idRegistro);

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado con éxito", "");
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
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

	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public static List<CasillaEntity> getCasillasRegistroObligatorias() {
		return casillasRegistroObligatorias;
	}

	public static void setCasillasRegistroObligatorias(List<CasillaEntity> casillasRegistroObligatorias) {
		GestionRegistro.casillasRegistroObligatorias = casillasRegistroObligatorias;
	}

	public static List<CasillaEntity> getCasillasRegistroObligatoriasModificar() {
		return casillasRegistroObligatoriasModificar;
	}

	public static void setCasillasRegistroObligatoriasModificar(
			List<CasillaEntity> casillasRegistroObligatoriasModificar) {
		GestionRegistro.casillasRegistroObligatoriasModificar = casillasRegistroObligatoriasModificar;
	}

	public boolean isObligatoria() {
		return obligatoria;
	}

	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

}
