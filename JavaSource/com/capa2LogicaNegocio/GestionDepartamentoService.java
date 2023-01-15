package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa1presentacion.Departamento;
import com.capa3Persistencia.entities.DepartamentoEntity;
import com.capa3Persistencia.entities.DepartamentosBean;

@Stateless
@LocalBean

public class GestionDepartamentoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	DepartamentosBean departamentoBean = new DepartamentosBean(); 
	
	public Departamento fromDepartamentoEntity(DepartamentoEntity depto) {
		Departamento d = new Departamento();
		d.setIdDepartamento(depto.getIdDepartamento());
		d.setNombre(depto.getNombre());
		d.setCiudades(depto.getCiudades());
		return d;
		
	}
	
	public DepartamentoEntity toDepartamentoEntity(Departamento depto) {
		DepartamentoEntity d = new DepartamentoEntity();
		d.setIdDepartamento(depto.getIdDepartamento());
		d.setNombre(depto.getNombre());
		d.setCiudades(depto.getCiudades());
		return d;
	}
	
	public Departamento agregarDepartamento(Departamento depto) throws Exception {
		DepartamentoEntity dpto = departamentoBean.crear(toDepartamentoEntity(depto));
		return fromDepartamentoEntity(dpto);
	}

	public List<DepartamentoEntity> buscarDepartamentos() throws Exception{
		List<DepartamentoEntity> listaDptos = departamentoBean.obtenerTodos();
		return listaDptos;
	}
	
	public Departamento buscarDepartamentoEntity(Long id) throws Exception {
		DepartamentoEntity depto = departamentoBean.buscarDepartamento(id);
		return fromDepartamentoEntity(depto);
	}

}
