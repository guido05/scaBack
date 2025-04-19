package com.sca.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sca.model.DTO.AsistenciaDTO;

@Entity
@Table(name="asistencia")
public class Asistencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	

	@OneToOne(optional = true)
	@JoinColumn(name = "id_condicion", nullable = true)
    private Condicion id_condicion;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "id_asociado",nullable = true)
	private Asociados id_asociado;
	
	/*@OneToOne(optional = true)
	@JoinColumn(name = "id_dia",nullable = true)
	private Dia id_dia;*/
	
	@Column(name="horaEntrada")
    private String horaEntrada;
	
	@Column(name="horaSalida")
    private String horaSalida;
	
	@Column(name="fecha")
    private LocalDate fecha;
	
	@Column(name="observacion")
    private String observacion;
	
	@Column(name="subTotal")
    private String subtotal;

    public Asistencia() {
    }

	public Asistencia(long id, Condicion id_condicion, Asociados id_asociado, String horaEntrada,
			String horaSalida, LocalDate fecha, String observacion, String subtotal) {
		super();
		this.id = id;
		this.id_condicion = id_condicion;
		this.id_asociado = id_asociado;
		//this.id_dia = id_dia;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.fecha = fecha;
		this.observacion = observacion;
		this.subtotal = subtotal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Condicion getId_condicion() {
		return id_condicion;
	}

	public void setId_condicion(Condicion id_condicion) {
		this.id_condicion = id_condicion;
	}

	public Asociados getId_asociado() {
		return id_asociado;
	}

	public void setId_asociado(Asociados id_asociado) {
		this.id_asociado = id_asociado;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	
	public static Asistencia convertToAasistencia(AsistenciaDTO asistenciaDTO,Asociados asociado,Condicion condicion) {
		Asistencia asistencia = new Asistencia();
		asistencia.setFecha(asistenciaDTO.getFecha_hora());
		asistencia.setHoraEntrada(asistenciaDTO.getHora_entrada());
		asistencia.setHoraSalida(asistenciaDTO.getHora_salida());
		if(asociado != null)
			asistencia.setId_asociado(asociado);
		if(condicion != null)
			asistencia.setId_condicion(condicion);
		return asistencia;
	}
}
