package com.sca.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sca.constantes.ExpresionRegular;
import com.sca.validator.ValidarExpresionesRegulares;

@Entity
@Table(name="asociado")
public class Asociados {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="legajo")
    private String legajo;
	
	//@OneToOne
	//@JoinColumn(name="id_firma")
    //private Firma id_firma;
	
	@Column(name="documento")
	private String documento;
	
	@ManyToMany
    @JoinTable(
        name = "asociado_categoria", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "asociado_id"), // Llave foránea de la tabla 'asociado'
        inverseJoinColumns = @JoinColumn(name = "categoria_id") // Llave foránea de la tabla 'categoria'
    )
	@JsonManagedReference
    private Set<Categoria> categorias;
	
	@Column(name="activo")
	private int activo;
	
	@Column(name="telefono")
	private String telefono;

    public Asociados() {
    }

	public Asociados(long id, @NotBlank(message = "El nombre no puede estar en blanco") @NotNull String nombre,
			@NotBlank(message = "El apellido no puede estar en blanco") String apellido,
			@NotBlank(message = "El legajo no puede estar en blanco") String legajo,
			Set<Categoria> categorias, @NotNull int activo , @NotNull String telefono,String docunmento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.legajo = legajo;
		this.categorias = categorias;
		this.activo = activo;
		this.telefono = telefono;
		this.documento = docunmento;
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

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
	    return "Asociados{id=" + id + ", categorias=" + (categorias != null ? categorias.size() : 0) + "}";

	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
}
