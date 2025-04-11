package com.sca.controller;

import com.sca.model.Respuesta;
import com.sca.model.Reg;
import com.sca.service.impl.RegServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@Api(tags = "Reg")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class RegController {

    Logger log = LoggerFactory.getLogger(String.class);

    @Autowired
    RegServiceImpl regServiceImpl;

    @PostMapping(value = "/addReg", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Agrega un Reg", notes = "Esta operación agrega un Reg a la base de datos")
    public ResponseEntity<Object> addReg(@RequestBody @Validated Reg reg, BindingResult bindingResult) throws BindException {
        return regServiceImpl.save(reg,bindingResult);
    }

    @GetMapping(value = "/getAllReg", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar Reg", notes = "Esta operación devuelve todos los Regs a la base de datos")
    public Respuesta getAllRegs() {
        return regServiceImpl.findAll();
    }

    @GetMapping(value = "/getByIdReg/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar Reg por id", notes = "Esta operación consulta un Reg por su identificador personal")
    public Respuesta getByIdReg(@PathParam("id") @PathVariable Long id) {
        return regServiceImpl.finById(id);
    }

    @DeleteMapping(value = "/deleteReg/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminar un Reg", notes = "Esta operación elimina un Reg de la base de datos")
    public Respuesta deleteReg(@PathParam("id") @PathVariable Long id) {
        return regServiceImpl.delete(id);
    }

    @PutMapping(value = "/updateReg", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualizar un Reg", notes = "Esta operación actualiza un Reg a la base de datos")
    public ResponseEntity<Object> updateReg(Reg reg, BindingResult bindingResult) throws BindException {
        return regServiceImpl.update(reg, bindingResult);
    }
}
