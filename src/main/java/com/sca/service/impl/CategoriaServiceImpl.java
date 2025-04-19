package com.sca.service.impl;

import java.util.Optional;

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

import com.sca.model.Categoria;
import com.sca.model.Respuesta;
import com.sca.model.DTO.CategoriaRequestDTO;
import com.sca.repository.CategoriaRepository;
import com.sca.service.CategoriaService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoriaServiceImpl extends ResponseEntityExceptionHandler implements CategoriaService {

	Logger log = LoggerFactory.getLogger(String.class);
	
	@Autowired
	CategoriaRepository categoriaRepository;

	Respuesta respuesta;

	String resp = "";

	//El ExceptionHandler me sirve para recuperar o en viar el status del error del pedido
	@ExceptionHandler(BindException.class)
	@Override
	public ResponseEntity<Object> save(Categoria categoria, BindingResult bindingResult) throws BindException {
		respuesta = new Respuesta();
		try {
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se agrego un Categoria");
			respuesta.setData(categoriaRepository.save(categoria));
		} catch (Exception e) {
			respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			respuesta.setDescripcion("No se pudo agregar el Categoria");
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
			Categoria categoria = categoriaRepository.findById(id).get();
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se elimino una Categoria");
			respuesta.setData(categoria);
			categoriaRepository.deleteById(id);
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudo eliminar la Categoria");
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
			respuesta.setDescripcion("Se muestran todos los Categorias");
			respuesta.setData(categoriaRepository.findAll());
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudieron mostrar los Categorias");
			respuesta.setData(e.getMessage());
		}
		return respuesta;
	}

	@Override
	public Respuesta findById(Long id) {
		respuesta = new Respuesta();
		try {
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Datos de la Categoria");
			respuesta.setData(categoriaRepository.findById(id));
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudieron mostrar los datos del Categoria");
			respuesta.setData(e.getMessage());
		}
		return respuesta;
	}
	
	

	@Override
	public ResponseEntity<Object> update(Categoria categoria, BindingResult bindingResult) throws BindException {
		respuesta = new Respuesta();
		try {
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se modificaron los datos del Categoria");
			respuesta.setData(categoriaRepository.save(categoria));
		} catch (Exception e) {
			respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			respuesta.setDescripcion("No se pudo modificar la Categoria");
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
	public Optional<Categoria> findByIdCategoria(Long id) {
		System.out.println(categoriaRepository.findById(id));
		return categoriaRepository.findById(id);
	}
	
	public Categoria converToCategoria(CategoriaRequestDTO categoriaRequestDto) {
		Categoria categoria = new Categoria();
		categoria.setId(categoriaRequestDto.getId());
		categoria.setNombre(categoriaRequestDto.getNombre());
		return categoria;
	}
}
