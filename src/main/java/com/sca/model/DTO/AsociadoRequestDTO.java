package com.sca.model.DTO;

import java.util.List;
import java.util.Set;

import com.sca.constantes.ExpresionRegular;
import com.sca.validator.ValidarExpresionesRegulares;

public class AsociadoRequestDTO {

    private long id;
	
	@ValidarExpresionesRegulares(customMessage = "El nombre no es válido", expresionRegular = ExpresionRegular.NOMBREAPELLIDO)
	private String nombre;
	
	@ValidarExpresionesRegulares(customMessage = "El apellido no es válido", expresionRegular = ExpresionRegular.NOMBREAPELLIDO)
	private String apellido;
	
	@ValidarExpresionesRegulares(customMessage="El legajo debe tener 3 digitos", expresionRegular = ExpresionRegular.LEGAJO)
    private String legajo;
	
	private String documento;

    private List<Long> categorias;
	
	private int activo;

	private String telefono;
	
	public AsociadoRequestDTO() {
		super();
	}

	public AsociadoRequestDTO(long id, String nombre, String apellido, String legajo, String documento,
			List<Long> categorias, int activo, String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.legajo = legajo;
		this.documento = documento;
		this.categorias = categorias;
		this.activo = activo;
		this.telefono = telefono;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public List<Long> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Long> categorias) {
		this.categorias = categorias;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
