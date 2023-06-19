package com.utils;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

@Named(value = "exportarExcel")
@ViewScoped
public class ExportarExcel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public void postProcessXLS(Object document) {
		//

		try {

			HSSFWorkbook wb = (HSSFWorkbook) document; 
			HSSFSheet sheet = wb.getSheetAt(0); //obtenemos la hoja de cálculo utilizando el método getSheetAt(0) y se almacena en el objeto sheet de tipo HSSFSheet.
			CellStyle style = wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			for (Row row : sheet) { // Se recorren las filas
				for (Cell cell : row) { // Se recorren las celdas
					switch (cell.getCellTypeEnum()) { // Se obtiene el valor de las celdas
					case STRING:
						cell.setCellValue(cell.getStringCellValue().toUpperCase()); //Los convierte a cadena String mayuscula
						cell.setCellStyle(style);
						break;
					}
				} // Este método recorre todas las celdas de la hoja de cálculo
					// generada, convierte los valores de las celdas de tipo cadena a mayúsculas y
					// aplica un estilo visual específico a esas celdas antes de que el archivo sea
					// descargado por el usuario
			}
		} catch (Exception e) {

		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
