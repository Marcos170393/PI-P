package com.capa1presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.capa2LogicaNegocio.GestionCasillaService;
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
		
}
