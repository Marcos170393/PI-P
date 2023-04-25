package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.capa1presentacion.Casilla;
import com.capa1presentacion.CurrentUser;
import com.capa1presentacion.Formulario;
import com.capa1presentacion.Registro;
import com.capa1presentacion.Usuario;
import com.capa2LogicaNegocio.GestionCasillaService;
import com.capa2LogicaNegocio.GestionFormularioService;
import com.capa2LogicaNegocio.GestionRegistroService;
import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.exception.PersistenciaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

import java.io.*;
@Stateless
@Named("importar")
public class ImportarDatos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	GestionRegistroService registroDao;

	@EJB
	GestionCasillaService gestionCasillaService;

	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	Registro registroSeleccionado;
	
	@PostConstruct
	public void init() {
		registroSeleccionado = new Registro();
	}
	
	@EJB
	GestionFormularioService gestionFormularioService;
	
	public boolean importarDatos() throws Exception {
		 File directoryPath = new File("C:\\data");
		    if (!directoryPath.exists()) {
		        directoryPath.mkdirs(); // Crea el directorio si no existe
		    }
		    File filesList[] = directoryPath.listFiles();
		    File archivoFinal = filesList[0];

		FileInputStream fis = new FileInputStream(archivoFinal);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheetAt(0);

		try {
			
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				
				
				Integer ukRegistro = Integer.parseInt(sheet.getRow(i).getCell(0).getStringCellValue());
				registroSeleccionado.setUk_registro(ukRegistro);
				
				Formulario f = gestionFormularioService.buscarFormularioEntityName(sheet.getRow(i).getCell(1).getStringCellValue());
				registroSeleccionado.setFormulario(f);
		
				System.out.println(registroSeleccionado);

				Date fecha = Date.valueOf(sheet.getRow(i).getCell(3).getStringCellValue());
				registroSeleccionado.setFecha(fecha);

				Casilla c = gestionCasillaService.buscarCasillaEntityName(sheet.getRow(i).getCell(4).getStringCellValue());
				
				registroSeleccionado.setCasilla(c);
				
				Long valor = Long.valueOf(sheet.getRow(i).getCell(6).getStringCellValue());
				registroSeleccionado.setValor(valor);

				Usuario u = gestionUsuarioService.buscarUsuarioEntityName(sheet.getRow(i).getCell(7).getStringCellValue());
				registroSeleccionado.setUsuario(u);
				
				registroDao.agregarRegistro(registroSeleccionado); 
			}

			archivoFinal.delete();

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Los datos se cargaron con exito",
					null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			return true;
		} catch (Exception e) {

			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: El archivo no es compatible",
					null);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);

			archivoFinal.delete();

		}

		return false;
	}
	
}


