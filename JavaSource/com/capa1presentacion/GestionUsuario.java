package com.capa1presentacion;

import javax.faces.event.ActionEvent;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Enumerated;
import javax.servlet.http.HttpSession;

import com.capa2LogicaNegocio.GestionCiudadService;
import com.capa2LogicaNegocio.GestionDepartamentoService;
import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa3Persistencia.entities.CiudadEntity;
import com.capa3Persistencia.exception.PersistenciaException;
import com.filter.CedulaValidatorFrontend;
import com.utils.ExceptionsTools;
import com.utils.Util;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;

//@ManagedBean(name="usuario")

@Named(value = "gestionUsuario") // JEE8
@SessionScoped // JEE8
public class GestionUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	GestionUsuarioService persistenciaBean;

	@Inject
	GestionCiudadService ciudadPersistencia;

	@Inject
	GestionDepartamentoService departamentoPersistencia;

	private Usuario usuarioSeleccionado;

	private boolean verDatosExtra = false;

	private String ciudadUsuario;
	
	private String departamentoUsuario;
		
	private boolean isFilterActive = false;

	// variable para indicar si se quieren mostrar los filtros o no
	private boolean checkfilter = false;

	@Enumerated
	private Rol admin = Rol.ADMINISTRADOR;

	@Enumerated
	private Rol inv = Rol.INVESTIGADOR;

	@Enumerated
	private Rol afic = Rol.AFICIONADO;

	CedulaValidatorFrontend verificacion = new CedulaValidatorFrontend();

	private List<Usuario> filtroUsuarios;

	private List<Ciudad> ciudades = new ArrayList<Ciudad>();
	
	public List<Usuario> getFiltroUsuarios() {
		return filtroUsuarios;
	}

	public void setFiltroUsuarios(List<Usuario> filtroUsuarios) {
		this.filtroUsuarios = filtroUsuarios;
	}

	public GestionUsuario() {
		super();
	}

	@PostConstruct
	public void init() {
		usuarioSeleccionado = new Usuario();
	}

	public String getCiudadUsuario() {
		return ciudadUsuario;
	}

	public void setCiudadUsuario(String ciudadUsuario) {
		this.ciudadUsuario = ciudadUsuario;
	}

	public boolean isVerDatosExtras() {
		return verDatosExtra;
	}

	public void verDatosExtra() {
		if (usuarioSeleccionado.getRol().equals(admin) || usuarioSeleccionado.getRol().equals(inv)) {
			this.verDatosExtra = true;
		} else {
			this.verDatosExtra = false;
		}
	}

	// Actualizar perfil
	public String actualizarPerfil() throws Exception {

		try {

			persistenciaBean.actualizarPerfil(usuarioSeleccionado);
			// actualizamos id
			Long nuevoId = usuarioSeleccionado.getIdUsuario();
			
			limpiarVariables();

			CurrentUser.setUsuario(persistenciaBean.buscarUsuarioEntity(nuevoId));
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se ha actualizado el usuario con id:" + nuevoId.toString(), "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listado";

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

	// MODIFICACION DE USUARIO \\
	public String actualizarUsuario() throws Exception {

		try {
			if (usuarioSeleccionado.getRol().equals(Rol.ADMINISTRADOR)
					|| usuarioSeleccionado.getRol().equals(Rol.INVESTIGADOR)) {
				List<Ciudad> ciudades = ciudadPersistencia.seleccionarCiudades();
				for (Ciudad c : ciudades) {
					if (c.getNombre().equals(ciudadUsuario)) {
						usuarioSeleccionado.setCiudad(c);
						break;
					}
				}
				verificacion.setMno(usuarioSeleccionado.getCedula());
				persistenciaBean.actualizarUsuario(usuarioSeleccionado);
			} else {
				persistenciaBean.actualizarUsuarioAficionado(usuarioSeleccionado);
			}

			limpiarVariables();
	     	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				    "Usuario actualizado con exito", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				// Guardar el mensaje en el flash scope
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				// Redirigir a listadoCasillas.xhtml usando ExternalContext
				FacesContext.getCurrentInstance().getExternalContext().redirect("listado.xhtml");
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

	public void registro() throws Exception {
		Usuario usuarioNuevo;
		try {

			List<Ciudad> ciudades = ciudadPersistencia.seleccionarCiudades();
			for (Ciudad c : ciudades) {
				if (c.getNombre().equals(ciudadUsuario)) {
					usuarioSeleccionado.setCiudad(c);
					break;
				}
			}
			verificacion.setMno(usuarioSeleccionado.getCedula());
			usuarioNuevo = (Usuario) persistenciaBean.agregarUsuario(usuarioSeleccionado);
			// actualizamos id
			Long nuevoId = usuarioNuevo.getIdUsuario();
			
			limpiarVariables();

			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Registro exitoso! Debe aguardar la habilitacion por parte de los administradores.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

		} catch (PersistenciaException e) {

//			Throwable rootException=ExceptionsTools.getCause(e);
//			String msg1=e.getMessage();
//			String msg2=rootException.getClass().getSimpleName();
			System.out.println(e.getMessage());
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "mal", null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
		} finally {

		}

	}

	// GUARDAR NUEVO USUARIO \\
	public String salvarCambios() throws Exception {

		Usuario usuarioNuevo;
		try {

			List<Ciudad> ciudades = ciudadPersistencia.seleccionarCiudades();
			for (Ciudad c : ciudades) {
				if (c.getNombre().equals(ciudadUsuario)) {
					usuarioSeleccionado.setCiudad(c);
					break;
				}
			}
			verificacion.setMno(usuarioSeleccionado.getCedula());
			usuarioNuevo = (Usuario) persistenciaBean.agregarUsuario(usuarioSeleccionado);
			// actualizamos id
			Long nuevoId = usuarioNuevo.getIdUsuario();
			
			//  Se limpian variables para proximas instancias
			limpiarVariables();
			
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se ha agregado un nuevo usuario con exito", "");
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

	public String validarUsuario() throws Exception {
		try {
			Usuario usuario = (Usuario) persistenciaBean
					.buscarUsuarioEntityName(usuarioSeleccionado.getNombreUsuario());
			// SI ESTA HABILITADO Y COINCIDE NOMBRE Y CONTRASEÑA
			System.out.println(persistenciaBean.Decrypt(usuario.getContrasenia()) + " " + usuario.getContrasenia()
					+ "  " + usuarioSeleccionado.getContrasenia());

			if (persistenciaBean.Decrypt(usuario.getContrasenia()).equals(usuarioSeleccionado.getContrasenia())
					&& usuario.getNombreUsuario().equals(usuarioSeleccionado.getNombreUsuario())
					&& usuario.isHabilitado()) {
				usuarioSeleccionado = new Usuario();
				CurrentUser.setUsuario(usuario);
				HttpSession hs = Util.getSession();
				hs.setAttribute("gestionUsuario", usuario);

				// Redirigir a home.xhtml usando ExternalContext
				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext externalContext = context.getExternalContext();
				externalContext.redirect("home.xhtml");

				return null; // Retornar null para indicar que no hay que procesar la respuesta

			} else {

				if (usuario.getNombreUsuario().equals("")) {
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontro el usuario",
							"");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					return "";
				} else if (!usuario.isHabilitado()) {
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario inhabilitado", "");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					return "";
				} else {
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta", "");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					return "";
				}

			}
		} catch (Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Nombre de usuario o contraseña incorrectos, vuelva a intentarlo", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "";
		}
	}

	// MOSTRAR LISTADO DE USUARIOS \\
	public List<Usuario> mostrarUsuarios() throws Exception {

		List<Usuario> listaUsuarios;
		try {
			listaUsuarios = persistenciaBean.buscarUsuariosDisponibles();
			for (int i = 0; i < listaUsuarios.size(); i++) {
				if (listaUsuarios.get(i).getIdUsuario() == CurrentUser.getUsuario().getIdUsuario()) {
					listaUsuarios.remove(i);
				}
			}
			return listaUsuarios;
		} catch (PersistenciaException e) {

			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			// mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			e.printStackTrace();
			return new ArrayList<Usuario>();
		}
	}

	public List<Ciudad> mostrarCiudades() throws Exception {

		List<Ciudad> listaCiudades;

		try {
			listaCiudades = ciudadPersistencia.seleccionarCiudades();
			return listaCiudades;

		} catch (PersistenciaException e) {
			e.getCause();
			return new ArrayList<Ciudad>();
		}

	}
	
	public List<Departamento> mostrarDepartamentos() throws Exception{
		List<Departamento> departamentos;
		
		try {
			departamentos = departamentoPersistencia.buscarDepartamentos();
			return departamentos;
		} catch (Exception e) {
			e.getCause();
			return new ArrayList<Departamento>();
		}
	}

	// METODO PARA MOSTRAR FORMULARIO DE ACTUALIZACION
	public String actualizarVistaUsuario(Long id) throws Exception {
		usuarioSeleccionado = persistenciaBean.buscarUsuarioEntity(id);
		if (usuarioSeleccionado.getRol().equals(Rol.ADMINISTRADOR)
				|| usuarioSeleccionado.getRol().equals(Rol.INVESTIGADOR)) {
			this.verDatosExtra = true;
		} else {
			this.verDatosExtra = false;
		}
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.redirect("actualizarUsuario.xhtml");
		return null;

	}

	// BAJA DE USUAIOS
	public void eliminarUsuario(String id) throws Exception {

		try {

			if (filtroUsuarios == null) { //Si no estoy filtrando
				persistenciaBean.elminarUsuarioEntity(Long.parseLong(id)); //Elimino el usuario del listado
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado con �xito", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			} else {
				for (Usuario user : filtroUsuarios) { //Si estoy filtrando (Se guardan los usuarios filtrados en filtroUsuarios)
					if (Long.parseLong(id) == user.getIdUsuario()) {
						filtroUsuarios.remove(user); //Lo eliminamos del filtro
						persistenciaBean.elminarUsuarioEntity(Long.parseLong(id)); //Borrado logico
						FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Usuario eliminado con �xito", "");
						FacesContext.getCurrentInstance().addMessage(null, facesMsg);
						break;
					}
				}
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

	public void cerrarSesion() throws IOException {
		HttpSession hs = Util.getSession();
		hs.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}

	public String modPerfil() {
		usuarioSeleccionado = CurrentUser.getUsuario();
		System.out.println(usuarioSeleccionado.getContrasenia());
		usuarioSeleccionado.setContrasenia(persistenciaBean.Decrypt(usuarioSeleccionado.getContrasenia()));
		return "profile";
	}

	public String reset() {
		usuarioSeleccionado = new Usuario();
		return "";
	}

	public void mostrarFiltrosInput() throws Exception {
		this.isFilterActive = this.checkfilter;
		if (this.isFilterActive == false) {
			this.filtroUsuarios = null;
			FacesContext.getCurrentInstance().getExternalContext().redirect("listado.xhtml");
		}
	}

	
	public void buscarCiudadesDepartamento() {
		try {
			System.out.println("buscando ciudades");
			Departamento depto = departamentoPersistencia.buscarDepartamentoEntity(departamentoUsuario);
			List<CiudadEntity> deptoCiudades = depto.getCiudades();

			for(CiudadEntity ciudad : deptoCiudades) {
				ciudades.add(ciudadPersistencia.fromCiudadEntity(ciudad));
			}
			System.out.println("cantidad de ciudades encontradas"+ciudades.size());
		} catch (Exception e) {
			System.out.println(e.getCause());
			
		}
	}
	
	public void limpiarVariables() {
		usuarioSeleccionado = new Usuario();
		departamentoUsuario = null;
		ciudadUsuario = null;
		ciudades = new ArrayList<Ciudad>();
		verDatosExtra = false;
		System.out.println("Se limpiaron las variables");
	}
	public GestionUsuarioService getPersistenciaBean() {
		return persistenciaBean;
	}

	public void setPersistenciaBean(GestionUsuarioService persistenciaBean) {
		this.persistenciaBean = persistenciaBean;
	}

	public Usuario getusuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setusuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public Rol getAdmin() {
		return admin;
	}

	public Rol getInv() {
		return inv;
	}

	public Rol getAfic() {
		return afic;
	}

	public void setAdmin(Rol admin) {
		this.admin = admin;
	}

	public void setInv(Rol inv) {
		this.inv = inv;
	}

	public void setAfic(Rol afic) {
		this.afic = afic;
	}

	public boolean isFilterActive() {
		return isFilterActive;
	}

	public boolean isCheckfilter() {
		return checkfilter;
	}

	public void setFilterActive(boolean isFilterActive) {
		this.isFilterActive = isFilterActive;
	}

	public void setCheckfilter(boolean checkfilter) {
		this.checkfilter = checkfilter;
	}

	public String getDepartamentoUsuario() {
		return departamentoUsuario;
	}

	public void setDepartamentoUsuario(String departamentoUsuario) {
		this.departamentoUsuario = departamentoUsuario;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
	
	

}
