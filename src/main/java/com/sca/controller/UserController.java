package com.sca.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sca.model.Respuesta;
import com.sca.model.User;
import com.sca.service.impl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "User")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {

    Logger log = LoggerFactory.getLogger(String.class);

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Agrega un user", notes = "Esta operación agrega un user a la base de datos")
    public ResponseEntity<Object> addUser(@RequestBody @Validated User user, BindingResult bindingResult) throws BindException {
        return userServiceImpl.save(user,bindingResult);
    }

    @GetMapping(value = "/getAllUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar user", notes = "Esta operación devuelve todos los users a la base de datos")
    public Respuesta getAllUsers() {
        return userServiceImpl.findAll();
    }

    @GetMapping(value = "/getByIdUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar user por id", notes = "Esta operación consulta un user por su identificador personal")
    public Respuesta getByIdUser(@PathParam("id") @PathVariable Long id) {
        return userServiceImpl.finById(id);
    }

    @DeleteMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminar un user", notes = "Esta operación elimina un user de la base de datos")
    public Respuesta deleteUser(@PathParam("id") @PathVariable Long id) {
        return userServiceImpl.delete(id);
    }

    @PutMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualizar un user", notes = "Esta operación actualiza un user a la base de datos")
    public ResponseEntity<Object> updateUser(@RequestBody @Validated User user, BindingResult bindingResult) throws BindException {
        return userServiceImpl.update(user, bindingResult);
    }
}
