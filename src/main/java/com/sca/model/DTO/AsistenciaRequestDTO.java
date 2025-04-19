package com.sca.model.DTO;

public class AsistenciaRequestDTO {

	private String id;
	private String fecha;
	private String horaEntrada;
	private String horaSalida;
	private int id_asociado;
	private int id_condicion;
	private String observacion;
	
	public AsistenciaRequestDTO() {}
	
	public AsistenciaRequestDTO(String id, String fecha, String horaEntrada, String horaSalida, int id_asociado,
			int id_condicion, String observacion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.id_asociado = id_asociado;
		this.id_condicion = id_condicion;
		this.observacion = observacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
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

	public int getId_asociado() {
		return id_asociado;
	}

	public void setId_asociado(int id_asociado) {
		this.id_asociado = id_asociado;
	}

	public int getId_condicion() {
		return id_condicion;
	}

	public void setId_condicion(int id_condicion) {
		this.id_condicion = id_condicion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}	
}
