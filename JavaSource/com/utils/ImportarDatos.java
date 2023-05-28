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
import com.capa1presentacion.Departamento;
import com.capa1presentacion.Formulario;
import com.capa1presentacion.Registro;
import com.capa1presentacion.Usuario;
import com.capa2LogicaNegocio.GestionCasillaService;
import com.capa2LogicaNegocio.GestionDepartamentoService;
import com.capa2LogicaNegocio.GestionFormularioService;
import com.capa2LogicaNegocio.GestionRegistroService;
import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa3Persistencia.entities.CasillaEntity;
import com.capa3Persistencia.exception.PersistenciaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

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
	
	@EJB
	GestionFormularioService gestionFormularioService;
	
	@EJB
	GestionDepartamentoService gestionDepartamentoService;
	
	public boolean importarDatos() throws Exception {
		File directoryPath = new File("C:\\data"); //Ruta donde se encuentra guardado el archivo
		File filesList[] = directoryPath.listFiles(); //Obtiene la lista de archivos en ese directorio y se almacena en el arreglo filesList[]
		File archivoFinal = filesList[0]; //Se selecciona el archivo (el primero) y se guarda en la variabla archivoFinal
		
		FileInputStream fis = new FileInputStream(archivoFinal);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheetAt(0); //Se obtiene la hoja de cálculo en la posición 0 del libro de Excel utilizando el método getSheetAt(0)
		
		List<Formulario> listaFormularios = gestionFormularioService.seleccionarFormularios();
		List<Casilla> listaCasillas = gestionCasillaService.seleccionarCasillas();
		List<Usuario> listaUsuarios = gestionUsuarioService.buscarUsuariosDisponibles();
	    System.out.println(listaFormularios);
        System.out.println(listaUsuarios);
        System.out.println(listaCasillas);
		try {
			
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { //Se recorre el archivo
				Registro registro = new Registro();
				
					
				registro.setUk_registro(Integer.parseInt(sheet.getRow(i).getCell(0).getStringCellValue())); //Se recorre la celda 0
			
				String nombreFormularioExcel = sheet.getRow(i).getCell(1).getStringCellValue().toLowerCase(); //Celda 1
				for (Formulario f : listaFormularios) {
				    if (f.getNombre().toLowerCase().equals(nombreFormularioExcel)) {
				        registro.setFormulario(f);
				    }
				}
	        
				//2 = departamento, no se setea ya que el formulario lo trae
	            
	            String fechaHoraStr = sheet.getRow(i).getCell(3).getStringCellValue();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	            Date fechaHora = sdf.parse(fechaHoraStr);
	            registro.setFecha(fechaHora);
				
				String nombreCasillaExcel = sheet.getRow(i).getCell(4).getStringCellValue().toLowerCase();

				for (Casilla c : listaCasillas) {
				    if (c.getNombre().toLowerCase().equals(nombreCasillaExcel)) {
				        registro.setCasilla(c);
				    }
				}
				
				registro.setValor(Long.parseLong(sheet.getRow(i).getCell(6).getStringCellValue()));
				
				String nombreUsuarioExcel = sheet.getRow(i).getCell(7).getStringCellValue().toLowerCase();

				for (Usuario u : listaUsuarios) {
				    if (u.getNombreUsuario().toLowerCase().equals(nombreUsuarioExcel)) {
				        registro.setUsuario(u);
				    }
				}			
				registroDao.agregarRegistro(registro); 
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


