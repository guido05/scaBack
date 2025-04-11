package com.sca.service.impl;

import com.sca.model.DTO.AsistenciaDTO;
import com.sca.model.DTO.AsociadoDTO;
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

import com.sca.model.Asistencia;
import com.sca.model.Categoria;
import com.sca.model.Respuesta;
import com.sca.repository.AsistenciaRepository;
import com.sca.repository.CategoriaRepository;
import com.sca.service.AsistenciaService;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsistenciaServiceImpl extends ResponseEntityExceptionHandler implements AsistenciaService{

Logger log = LoggerFactory.getLogger(String.class);
	
	@Autowired
	AsistenciaRepository asistenciaRepository;

	Respuesta respuesta;

	String resp = "";

	//El ExceptionHandler me sirve para recuperar o en viar el status del error del pedido
	@ExceptionHandler(BindException.class)
	@Override
	public ResponseEntity<Object> save(Asistencia asistencia, BindingResult bindingResult) throws BindException {
		respuesta = new Respuesta();
		try {
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se agrego una asistencia");
			respuesta.setData(asistenciaRepository.save(asistencia));
		} catch (Exception e) {
			respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			respuesta.setDescripcion("No se pudo agregar el asistencia");
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
		System.out.println(id);
		try {
			Asistencia asistencia = asistenciaRepository.findById(id).get();
			asistenciaRepository.deleteById(id);
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se elimino una asistencia");
			respuesta.setData(asistencia);
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudo eliminar la asistencia");
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
			respuesta.setDescripcion("Se muestran todos los asistencias");
			respuesta.setData(asistenciaRepository.findAll());
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudieron mostrar los asistencia");
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
			respuesta.setDescripcion("Datos de la Categoria");
			respuesta.setData(asistenciaRepository.findById(id));
		} catch (Exception e) {
			respuesta.setCodigo("400");
			respuesta.setStatus("Error");
			respuesta.setDescripcion("No se pudieron mostrar los datos del asistencia");
			respuesta.setData(e.getMessage());
		}
		return respuesta;
	}

	@Override
	public Respuesta findByUserAndRegAndAsociado() {
		respuesta = new Respuesta();
		respuesta.setCodigo("200");
		respuesta.setStatus("Ok");
		respuesta.setDescripcion("Se devuelve los datos del asociado");

		respuesta.setData(convertToDTO(asistenciaRepository.findByUserAndRegAndAsociado()));
		return respuesta;
	}

	private List<AsistenciaDTO> convertToDTO(List<Object[]> asociados) {
		AsistenciaDTO asistenciaDTO;
		List<AsistenciaDTO> listaAsistenciaDTO = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm:ss");
		for (Object[] asociado : asociados) {
			asistenciaDTO = new AsistenciaDTO();
			if(asociado[0] != null) asistenciaDTO.setActivo(Integer.parseInt(asociado[0].toString()));
			if(asociado[1] != null) asistenciaDTO.setApellido(asociado[1].toString());
			if(asociado[2] != null) asistenciaDTO.setDocumento(asociado[2].toString());
			if(asociado[3] != null) asistenciaDTO.setNombre(asociado[3].toString());
			if(asociado[4] != null) asistenciaDTO.setLegajo(asociado[4].toString());
			if(asociado[5] != null) asistenciaDTO.setTelefono(asociado[5].toString());
			if(asociado[6] != null) asistenciaDTO.setId_firma(asociado[6].toString());
			if(asociado[7] != null) asistenciaDTO.setCodigo(asociado[7].toString());
			if(asociado[8] != null) asistenciaDTO.setFecha(asociado[8].toString() == null ? Timestamp.valueOf(asociado[11].toString()).toLocalDateTime().toLocalDate().format(formatter) : asociado[8].toString());
			if(asociado[9] != null) asistenciaDTO.setHora_entrada(asociado[9].toString() == null ? Timestamp.valueOf(asociado[11].toString()).toLocalDateTime().toLocalTime().format(formatterHour) : asociado[9].toString());
			if(asociado[10] != null) asistenciaDTO.setHora_salida(asociado[10].toString());
			if(asociado[11] != null) asistenciaDTO.setFecha_hora(Timestamp.valueOf(asociado[11].toString()).toLocalDateTime().toLocalDate().format(formatter).concat(" ").concat(Timestamp.valueOf(asociado[11].toString()).toLocalDateTime().toLocalTime().format(formatterHour)));
			if(asociado[12] != null) asistenciaDTO.setCondicion(asociado[12].toString().concat("(").concat(asociado[13].toString()).concat(")"));

			listaAsistenciaDTO.add(asistenciaDTO);
		}
		return listaAsistenciaDTO;
	}

	@Override
	public ResponseEntity<Object> update(Asistencia asistencia, BindingResult bindingResult) throws BindException {
		respuesta = new Respuesta();
		try {
			respuesta.setCodigo("200");
			respuesta.setStatus("Ok");
			respuesta.setDescripcion("Se modificaron los datos del asistencia");
			respuesta.setData(asistenciaRepository.save(asistencia));
		} catch (Exception e) {
			respuesta.setCodigo(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			respuesta.setDescripcion("No se pudo modificar la asistencia");
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
