package com.sca.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.sca.model.Asociados;
import com.sca.model.Categoria;
import com.sca.model.Respuesta;
import com.sca.model.User;
import com.sca.model.DTO.AsociadoDTO;
import com.sca.model.DTO.AsociadoRequestDTO;
import com.sca.repository.AsociadosRepository;
import com.sca.repository.CategoriaRepository;
import com.sca.repository.UserRepository;
import com.sca.service.AsociadosService;

@Service
public class AsociadosServiceImpl extends ResponseEntityExceptionHandler implements AsociadosService {

	Logger log = LoggerFactory.getLogger(String.class);
	
	@Autowired
	AsociadosRepository asociadosRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	UserRepository userRepository;

	Respuesta respuesta;

	String resp = "";

	//El ExceptionHandler me sirve para recuperar o en viar el status del error del pedido
	@ExceptionHandler(BindException.class)
	@Override
	public ResponseEntity<Object> save(Asociados asociados, BindingResult bindingResult) throws BindException {
		respuesta = new Respuesta();
		try {
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se agrego un asociado");
			crearUser(asociados);
			respuesta.setData(asociadosRepository.save(asociados));
		} catch (Exception e) {
			respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			respuesta.setDescripcion("No se pudo agregar el asociado");
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
			Optional<Asociados> asociados = asociadosRepository.findById(id);
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se elimino un asociado");
			respuesta.setData("");
			asociados.get().setActivo(0);
			asociadosRepository.save(asociados.get());
			//asociadosRepository.deleteById(id);
			//userRepository.deleteByDni(asociados.get().getDocumento());
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudo eliminar el asociado");
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
			respuesta.setDescripcion("Se muestran todos los asociados");
			respuesta.setData(asociadosRepository.findAll());
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudieron mostrar los asociados");
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
			respuesta.setDescripcion("Datos del asociado");
			respuesta.setData(asociadosRepository.findById(id));
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudieron mostrar los datos del asociado");
			respuesta.setData(e.getMessage());
		}
		return respuesta;
	}

	@Override
	public Respuesta findByUserAndReg() {
		respuesta = new Respuesta();
		respuesta.setCodigo("200");
		respuesta.setStatus("Ok");
		respuesta.setDescripcion("Se devuelve los datos del asociado");
		respuesta.setData(convertToDTO(asociadosRepository.findByUserAndReg()));
		return respuesta;
	}

	private List<AsociadoDTO> convertToDTO(List<Object[]> asociados) {
		AsociadoDTO asociadoDTO;
		List<AsociadoDTO> listaAsociadoDTO = new ArrayList<>();
		for (Object[] asociado : asociados) {
			asociadoDTO = new AsociadoDTO();
			if(asociado[0] != null) asociadoDTO.setActivo(Integer.parseInt(asociado[0].toString()));
			if(asociado[1] != null) asociadoDTO.setApellido(asociado[1].toString());
			if(asociado[2] != null) asociadoDTO.setDocumento(asociado[2].toString());
			if(asociado[3] != null) asociadoDTO.setNombre(asociado[3].toString());
			if(asociado[4] != null) asociadoDTO.setLegajo(asociado[4].toString());
			if(asociado[5] != null) asociadoDTO.setTelefono(asociado[5].toString());
			if(asociado[6] != null) asociadoDTO.setId_firma(asociado[6].toString());
			if(asociado[7] != null) asociadoDTO.setCodigo(asociado[7].toString());
			if(asociado[8] != null) asociadoDTO.setFecha_hora(Timestamp.valueOf(asociado[8].toString()));
			listaAsociadoDTO.add(asociadoDTO);
		}
		return listaAsociadoDTO;
	}

	@Override
	public ResponseEntity<Object> update(Asociados asociados, BindingResult bindingResult) throws BindException {
		respuesta = new Respuesta();
		try {
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se modificaron los datos del asociado");
			respuesta.setData(asociadosRepository.save(asociados));
		} catch (Exception e) {
			respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			respuesta.setDescripcion("No se pudo modificar el asociado");
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
	
	public Asociados convertToAsociando(AsociadoRequestDTO dto) {
		Asociados asociado = new Asociados();
		asociado.setId(dto.getId());
        asociado.setNombre(dto.getNombre());
        asociado.setApellido(dto.getApellido());
        asociado.setDocumento(dto.getDocumento());
        asociado.setLegajo(dto.getLegajo());
        asociado.setTelefono(dto.getTelefono());
        asociado.setActivo(dto.getActivo());
        // Convertir IDs a entidades
		System.out.println(categoriaRepository.findById(Long.valueOf(dto.getCategorias().get(0))));
        Set<Categoria> categorias = dto.getCategorias().stream()
                .map(id -> categoriaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id)))
                .collect(Collectors.toSet());

        asociado.setCategorias(categorias);
        
        return asociado;
	}
	
	private void crearUser(Asociados asociado) throws Exception {
		Optional<User> userOptional = userRepository.findByDni(asociado.getDocumento());
		if (userRepository.findByDni(asociado.getDocumento()).isPresent()) {
			User user = new User();
			user.setId(userOptional.get().getId());
			user.setCodigo(asociado.getLegajo());
			user.setDni(asociado.getDocumento());
			userRepository.save(user);
			throw new Exception("Ya existe el documento en la base de datos. Se actualizaron los datos del user");
		}else {
			User user = new User();
			user.setCodigo(asociado.getLegajo());
			user.setDni(asociado.getDocumento());
			userRepository.save(user);
		}
			
		
	}

	@Override
	public Respuesta activeAsociado(Long id) {
		respuesta = new Respuesta();
		try {
			Optional<Asociados> asociados = asociadosRepository.findById(id);
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se activo un asociado");
			respuesta.setData("");
			asociados.get().setActivo(1);
			asociadosRepository.save(asociados.get());
			//asociadosRepository.deleteById(id);
			//userRepository.deleteByDni(asociados.get().getDocumento());
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudo activar el asociado");
			respuesta.setData(e.getMessage());
		}
		return respuesta;
	}
}
