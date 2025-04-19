package com.sca.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sca.model.Reg;
import com.sca.model.Respuesta;
import com.sca.repository.RegRepository;
import com.sca.service.RegService;

@Service
public class RegServiceImpl extends ResponseEntityExceptionHandler implements RegService {

    Logger log = LoggerFactory.getLogger(String.class);

    @Autowired
    RegRepository regRepository;

    Respuesta respuesta;

    String resp = "";

    //El ExceptionHandler me sirve para recuperar o en viar el status del error del pedido
    @ExceptionHandler(BindException.class)
    @Override
    public ResponseEntity<Object> save(Reg reg, BindingResult bindingResult) throws BindException {
        respuesta = new Respuesta();
        try {
            respuesta.setCodigo("200");
            respuesta.setStatus("Ok");
            respuesta.setDescripcion("Se agrego un Reg");
            respuesta.setData(regRepository.save(reg));
        } catch (Exception e) {
            respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            respuesta.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
            respuesta.setDescripcion("No se pudo agregar el SueldoBasico");
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(r -> {
                    resp = resp + r.getDefaultMessage() + ";";
                });
                respuesta.setData(resp);
                resp = "";
                return handleExceptionInternal(e, respuesta, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);
            } else {
                respuesta.setData(e.getMessage());
                return handleExceptionInternal(e, respuesta, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);
            }
        }

        return new ResponseEntity<Object>(respuesta, null, HttpStatus.CREATED);
    }

    @Override
    public Respuesta delete(Long id) {
        respuesta = new Respuesta();
        try {
            Reg reg = regRepository.findById(id).get();
            regRepository.deleteById(id);
            respuesta.setCodigo("200");
            respuesta.setStatus("Ok");
            respuesta.setDescripcion("Se elimino un reg");
            respuesta.setData(reg);
        } catch (Exception e) {
            respuesta.setCodigo("400");
            respuesta.setStatus("Error");
            respuesta.setDescripcion("No se pudo eliminar el reg");
            respuesta.setData(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Respuesta findAll() {
        respuesta = new Respuesta();
        try {
            respuesta.setCodigo("200");
            respuesta.setStatus("Ok");
            respuesta.setDescripcion("Se muestran todos los regs");
            respuesta.setData(regRepository.findAll());
        } catch (Exception e) {
            respuesta.setCodigo("400");
            respuesta.setStatus("Error");
            respuesta.setDescripcion("No se pudieron mostrar los regs");
            respuesta.setData(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Respuesta finById(Long id) {
        respuesta = new Respuesta();
        try {
            respuesta.setCodigo("200");
            respuesta.setStatus("Ok");
            respuesta.setDescripcion("Datos del reg");
            respuesta.setData(regRepository.findById(id));
        } catch (Exception e) {
            respuesta.setCodigo("400");
            respuesta.setStatus("Error");
            respuesta.setDescripcion("No se pudieron mostrar los datos del reg");
            respuesta.setData(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public ResponseEntity<Object> update(Reg reg, BindingResult bindingResult) throws BindException {
        respuesta = new Respuesta();
        try {
            respuesta.setCodigo("200");
            respuesta.setStatus("Ok");
            respuesta.setDescripcion("Se modificaron los datos del user");
            respuesta.setData(regRepository.save(reg));
        } catch (Exception e) {
            respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            respuesta.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
            respuesta.setDescripcion("No se pudo modificar la SueldoBasico");
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(r -> {
                    resp = resp + r.getDefaultMessage() + ";";
                });
                respuesta.setData(resp);
                resp = "";
                return handleExceptionInternal(e, respuesta, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);
            } else {
                respuesta.setData(e.getMessage());
                return handleExceptionInternal(e, respuesta, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);
            }
        }

        return new ResponseEntity<Object>(respuesta, null, HttpStatus.CREATED);
    }
}
