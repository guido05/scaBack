package com.sca.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import com.sca.model.Asociados;
import com.sca.model.Respuesta;

public interface AsociadosService {
	
	public ResponseEntity<Object> save(Asociados cliente, BindingResult bindingResult) throws BindException;
	
	public ResponseEntity<Object> update(Asociados cliente, BindingResult bindingResult) throws BindException;
	
	public Respuesta delete(Long id);
	
	public Respuesta findAll();
	
	public Respuesta finById(Long id);

	public Respuesta findByUserAndReg();
	
	
}
