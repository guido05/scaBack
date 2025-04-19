package com.sca.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import com.sca.model.Respuesta;
import com.sca.model.User;

public interface UserService {
    public ResponseEntity<Object> save(User user, BindingResult bindingResult) throws BindException;

    public ResponseEntity<Object> update(User user, BindingResult bindingResult) throws BindException;

    public Respuesta delete(Long id);

    public Respuesta findAll();

    public Respuesta finById(Long id);
    
    public Optional<User> findByDni(String dni);
}
