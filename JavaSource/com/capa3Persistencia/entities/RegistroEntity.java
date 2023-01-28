package com.capa3Persistencia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Entity implementation class for Entity: Registro
 *
 */
@Entity
@Table(name="REGISTROS")
@NamedQuery(name="RegistroEntity.findAll", query="SELECT r FROM RegistroEntity r")
public class RegistroEntity implements Serializable {

	private static final long serialVersionUID = 1L;	
	public RegistroEntity() {
		super();
	} 
	
	@Id
	@SequenceGenerator(name="REGISTRO_IDREGISTRO_GENERATOR", sequenceName="SEQ_REG")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTRO_IDREGISTRO_GENERATOR")
	@Column(name="ID_REGISTRO")
	private long idRegistro;
	
	
	@JoinColumn(name="ID_USUARIO")
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	private UsuarioEntity usuario;
	
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="ID_FORMULARIO")
	@OneToOne
	private FormularioEntity formulario;
	

	@JoinColumn(name="ID_CASILLA")
	@OneToOne
	private CasillaEntity casilla;
	
	private Date fecha;
	
	@Column(nullable = true)
	private Long valor;
	
	@Column(name = "uk_registro",nullable = false)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Integer uk_registro; 
	
	public RegistroEntity(UsuarioEntity usuario, FormularioEntity formulario, Date fecha,CasillaEntity casilla, Long valor, Integer uk_registro) {
		super();
		this.usuario = usuario;
		this.formulario = formulario;
		this.casilla = casilla;
		this.fecha = fecha;
		this.valor = valor;
		this.uk_registro = uk_registro;
	}

	
	public long getIdRegistro() {
		return idRegistro;
	}
	

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}


	public FormularioEntity getFormulario() {
		return formulario;
	}


	public Long getValor() {
		return valor;
	}


	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}


	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}


	public void setFormulario(FormularioEntity formulario) {
		this.formulario = formulario;
	}


	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	

	public CasillaEntity getCasilla() {
		return casilla;
	}

	public void setCasilla(CasillaEntity casilla) {
		this.casilla = casilla;
	}
	
	

	public int getUk_registro() {
		return uk_registro;
	}


	public void setUk_registro(int uk_registro) {
		this.uk_registro = uk_registro;
	}


	@Override
	public String toString() {
		return "Registro [idRegistro=" + idRegistro + ", usuario=" + usuario + ", formulario=" + formulario
				+ ", casilla=" + casilla + ", fecha=" + fecha + ", valor=" + valor + "]";
	}

	
	
	
	
}
