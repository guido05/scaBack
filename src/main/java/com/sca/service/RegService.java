package com.sca.service;

import com.sca.model.Reg;
import com.sca.model.Respuesta;
import com.sca.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public interface RegService {
    public ResponseEntity<Object> save(Reg reg, BindingResult bindingResult) throws BindException;

    public ResponseEntity<Object> update(Reg reg, BindingResult bindingResult) throws BindException;

    public Respuesta delete(Long id);

    public Respuesta findAll();

    public Respuesta finById(Long id);
}
