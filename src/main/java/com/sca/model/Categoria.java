package com.sca.model;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="categoria")
public class Categoria {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name="nombre")
    private String nombre;
    
    @ManyToMany(mappedBy = "categorias")
    @JsonBackReference
    private Set<Asociados> asociados;

    public Categoria() {
    }

    public Categoria(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

	public Set<Asociados> getAsociados() {
		return asociados;
	}

	public void setAsociados(Set<Asociados> asociados) {
		this.asociados = asociados;
	}

	@Override
	public String toString() {
	    return "Categoria{id=" + id + ", asociados=" + (asociados != null ? asociados.size() : 0) + "}";
	}
	
	

  
}
