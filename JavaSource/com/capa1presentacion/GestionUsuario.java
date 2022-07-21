package com.capa1presentacion;

import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Enumerated;

import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa3Persistencia.exception.PersistenciaException;
import com.utils.ExceptionsTools;

//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;


//@ManagedBean(name="usuario")

@Named(value="gestionUsuario")		//JEE8
@SessionScoped			        //JEE8
public class GestionUsuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	GestionUsuarioService persistenciaBean;
	
	private Usuario usuarioSeleccionado;
	
	@Enumerated
	private Rol admin = Rol.ADMINISTRADOR;
	
	@Enumerated
	private Rol inv= Rol.INVESTIGADOR;
	
	@Enumerated
	private Rol afic= Rol.AFICIONADO;
	
	public GestionUsuario() {
		super();
	}
	@PostConstruct
	public void init() {
		usuarioSeleccionado=new Usuario();
		
	}
	
	// MODIFICACION DE USUARIO \\
	public String actualizarUsuario() throws Exception {
		
		try {
			
			persistenciaBean.actualizarUsuario(usuarioSeleccionado);
			//actualizamos id
			Long nuevoId=usuarioSeleccionado.getIdUsuario();
			//vaciamos usuarioSeleccionado como para ingresar uno nuevo
			usuarioSeleccionado=new Usuario();
			
			//mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
			  "Se ha actualizado el usuario con éxito.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return "listado";
			
		} 
		catch (PersistenciaException e) {
			
			Throwable rootException=ExceptionsTools.getCause(e);
			String msg1=e.getMessage();
			String msg2=ExceptionsTools.formatedMsg(rootException);
			//mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		finally {
			
		}
		
		return "";
}
	public void registro() throws Exception {
		Usuario usuarioNuevo;
		try {
			usuarioNuevo = (Usuario) persistenciaBean.agregarUsuario(usuarioSeleccionado);
			//actualizamos id
			Long nuevoId=usuarioNuevo.getIdUsuario();
			//vaciamos usuarioSeleccionado como para ingresar uno nuevo
			usuarioSeleccionado=new Usuario();
			
			//mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
			  "Registro exitoso! Debe aguardar la habilitacion por parte de los administradores.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			
			
		} 
		catch (PersistenciaException e) {
			
			String msg1=e.getMessage();
			//mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,msg1,null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			
		}
		
	}
	// GUARDAR NUEVO USUARIO \\
	public String salvarCambios() throws Exception {
			
			Usuario usuarioNuevo;
			try {
				usuarioNuevo = (Usuario) persistenciaBean.agregarUsuario(usuarioSeleccionado);
				//actualizamos id
				Long nuevoId=usuarioNuevo.getIdUsuario();
				//vaciamos usuarioSeleccionado como para ingresar uno nuevo
				usuarioSeleccionado=new Usuario();
				
				//mensaje de actualizacion correcta
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				  "Nuevo usuario creado con éxito.", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				return "home";
				
			} 
			catch (PersistenciaException e) {
				
				String msg1=e.getMessage();
				//mensaje de actualizacion correcta
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,msg1,null);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				return "";
			}
	}
	
	// LOGIN DE USUARIO \\
	public String validarUsuario() throws Exception {
		try {
			 Usuario usuario = (Usuario) persistenciaBean.buscarUsuarioEntityName(usuarioSeleccionado.getNombreUsuario());
					//SI ESTA HABILITADO Y COINCIDE NOMBRE Y CONTRASEÑA
				if (usuario.getContrasenia().equals(usuarioSeleccionado.getContrasenia()) && usuario.getNombreUsuario().equals(usuarioSeleccionado.getNombreUsuario()) && usuario.isHabilitado()) {
						 usuarioSeleccionado=new Usuario();	
						 CurrentUser.setUsuario(usuario);
						
						return "home";
							
				}else {
					
					if (usuario.getNombreUsuario().equals("")) {
						FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"No se encontro el usuario", "" );
						FacesContext.getCurrentInstance().addMessage(null, facesMsg);
						return "";
					}else if(!usuario.isHabilitado()) {
						FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
								"Usuario inhabilitado", "" );
								FacesContext.getCurrentInstance().addMessage(null, facesMsg);
								return "";
					}
					else {
						FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Contraseña incorrecta", "" );
						FacesContext.getCurrentInstance().addMessage(null, facesMsg);
						return "";
					}
	
				}
		}catch(Exception e) {
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"Nombre de usuario o contraseña incorrectos", "" );
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					return "";
		}
	}
				
	// MOSTRAR LISTADO DE USUARIOS \\
	public List<Usuario> mostrarUsuarios() throws Exception {
		

		List<Usuario> listaUsuarios;
		try {
			listaUsuarios =  persistenciaBean.seleccionarUsuarios();
			return listaUsuarios;
		} 
		catch (PersistenciaException e) {
			
			Throwable rootException=ExceptionsTools.getCause(e);
			String msg2=ExceptionsTools.formatedMsg(rootException);
			//mensaje de actualizacion correcta
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,null, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			
			return  new ArrayList<Usuario>(); 
		}
	}
	
	// METODO PARA MOSTRAR FORMULARIO DE ACTUALIZACION
	public String actualizarVistaUsuario(String id) throws Exception{
		usuarioSeleccionado = persistenciaBean.buscarUsuarioEntity(Long.parseLong(id));
		return "actualizarUsuario";
	}
	
	// BAJA DE USUAIOS
	public void eliminarUsuario(String id) throws Exception{

	try {
		persistenciaBean.elminarUsuarioEntity(Long.parseLong(id));
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario eliminado con �xito","");
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	} 
	catch (PersistenciaException e) {
		
		Throwable rootException=ExceptionsTools.getCause(e);
		String msg1=e.getMessage();
		String msg2=ExceptionsTools.formatedMsg(rootException);
		//mensaje de actualizacion correcta
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,msg1, msg2);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}
	finally {
		
	}
	
	}
	
	public String modPerfil() {
		usuarioSeleccionado = CurrentUser.getUsuario();
		return "profile";
	}

	public String reset() {
		usuarioSeleccionado=new Usuario();
		return "";
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
	
		
	
}
