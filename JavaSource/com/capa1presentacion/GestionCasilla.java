package com.capa1presentacion;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.capa2LogicaNegocio.GestionCasillaService;
@Named(value = "gestionCasilla") // JEE8
@SessionScoped // JEE8
public class GestionCasilla implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	GestionCasillaService casillaPersistencia;
	
}
