package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.capa1presentacion.Ciudad;
import com.capa1presentacion.Departamento;
import com.capa3Persistencia.entities.CiudadEntity;
import com.capa3Persistencia.entities.DepartamentoEntity;
import com.capa3Persistencia.entities.DepartamentosBean;

@Stateless
@LocalBean
public class GestionDepartamentoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	DepartamentosBean departamentoBean = new DepartamentosBean();

	@Inject
	GestionCiudadService ciudadesPersistencia;

	public Departamento fromDepartamentoEntity(DepartamentoEntity depto) {
		Departamento d = new Departamento();
		d.setIdDepartamento(depto.getIdDepartamento());
		d.setNombre(depto.getNombre());
		d.setCiudades(depto.getCiudades());
		return d;

	}

	public DepartamentoEntity toDepartamentoEntity(Departamento depto) {
		DepartamentoEntity d = new DepartamentoEntity();
		d.setNombre(depto.getNombre());
		d.setCiudades(depto.getCiudades());
		return d;
	}

	public DepartamentoEntity forDepartamentoEntity(Departamento depto) {
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

	public List<Departamento> buscarDepartamentos() throws Exception {
		List<DepartamentoEntity> listaDptosEntity = departamentoBean.obtenerTodos();
		List<Departamento> listaDptos = new ArrayList<>();
	
		for (DepartamentoEntity departamento : listaDptosEntity) {
			listaDptos.add(fromDepartamentoEntity(departamento));
		}
		
		return listaDptos;
	}

	public Departamento buscarDepartamentoEntity(Long id) throws Exception {
		DepartamentoEntity depto = departamentoBean.buscarDepartamento(id);
		return fromDepartamentoEntity(depto);
	}
}
