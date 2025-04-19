package com.sca.model.DTO;

import javax.persistence.Column;

public class CategoriaRequestDTO {

	 	private long id;
	    private String nombre;
		
	    public CategoriaRequestDTO(long id, String nombre) {
			super();
			this.id = id;
			this.nombre = nombre;
		}
		
		public CategoriaRequestDTO() {
			super();
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
	    
	    
		
}
