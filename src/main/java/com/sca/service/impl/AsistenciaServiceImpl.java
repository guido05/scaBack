package com.sca.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

import com.sca.model.Asistencia;
import com.sca.model.Respuesta;
import com.sca.model.DTO.AsistenciaDTO;
import com.sca.model.DTO.AsistenciaRequestDTO;
import com.sca.repository.AsistenciaRepository;
import com.sca.repository.AsociadosRepository;
import com.sca.repository.CondicionRepository;
import com.sca.service.AsistenciaService;
import com.sca.utils.DateUtil;

@Service
public class AsistenciaServiceImpl extends ResponseEntityExceptionHandler implements AsistenciaService {

	Logger log = LoggerFactory.getLogger(String.class);

	@Autowired
	AsistenciaRepository asistenciaRepository;
	
	@Autowired
	AsociadosRepository asociadosRepository;
	
	@Autowired
	CondicionRepository condicionRepository;

	Respuesta respuesta;

	String resp = "";

	// El ExceptionHandler me sirve para recuperar o en viar el status del error del
	// pedido
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
		List<AsistenciaDTO> listaAsistencia = convertToDTO(asistenciaRepository.findByUserAndRegAndAsociado()); 
		guardarAsistencia(listaAsistencia);
		respuesta.setData(listaAsistencia);
		return respuesta;
	}

	private List<AsistenciaDTO> convertToDTO(List<Object[]> asociados) {
		AsistenciaDTO asistenciaDTO;
		List<AsistenciaDTO> listaAsistenciaDTO = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm:ss");
		for (Object[] asociado : asociados) {
			asistenciaDTO = new AsistenciaDTO();
			if (asociado[2] != null) {
				if (asociado[0] != null)
					asistenciaDTO.setActivo(Integer.parseInt(asociado[0].toString()));
				if (asociado[1] != null)
					asistenciaDTO.setApellido(asociado[1].toString());
				if (asociado[2] != null)
					asistenciaDTO.setDocumento(asociado[2].toString());
				if (asociado[3] != null)
					asistenciaDTO.setNombre(asociado[3].toString());
				if (asociado[4] != null)
					asistenciaDTO.setLegajo(asociado[4].toString());
				if (asociado[5] != null)
					asistenciaDTO.setTelefono(asociado[5].toString());
				if (asociado[6] != null)
					asistenciaDTO.setId_firma(asociado[6].toString());
				if (asociado[7] != null)
					asistenciaDTO.setCodigo(asociado[7].toString());
				
				if(asociado[8] == null && asociado[11] != null) {
					asistenciaDTO.setFecha( Timestamp.valueOf(asociado[11].toString())
							.toLocalDateTime().toLocalDate().format(formatter));
				}else {
					if(asociado[8] != null)
						asistenciaDTO.setFecha(asociado[8].toString());
				}
				
				if(asociado[9] == null && asociado[11] != null) {
					asistenciaDTO
							.setHora_entrada(Timestamp.valueOf(asociado[11].toString())
									.toLocalDateTime().toLocalTime().format(formatterHour));
				}else {
					if(asociado[9] != null)
						asistenciaDTO.setHora_entrada(asociado[9].toString());
				}
				
				if (asociado[10] != null)
					asistenciaDTO.setHora_salida(asociado[10].toString());
				if (asociado[11] != null)
					asistenciaDTO
							.setFecha_hora(Timestamp.valueOf(asociado[11].toString()).toLocalDateTime().toLocalDate());
				if (asociado[12] != null)
					asistenciaDTO.setCondicion(
							asociado[12].toString().concat("(").concat(asociado[13].toString()).concat(")"));
				if(asociado[14] !=null)
					asistenciaDTO.setId_condicion(asociado[14].toString());
				else 
					asistenciaDTO.setId_condicion("4");
				if(asociado[15] !=null)
					asistenciaDTO.setId_asociado(asociado[15].toString());
				if(asociado[16] !=null)
					asistenciaDTO.setId_asistencia(asociado[16].toString());

				listaAsistenciaDTO.add(asistenciaDTO);
			}
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
	
	private void guardarAsistencia(List<AsistenciaDTO> listaAsistencia) {
		if (listaAsistencia.isEmpty()) return;
		LocalDate hoy = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		List<Asistencia> lista = asistenciaRepository.findAll();
		List<AsistenciaDTO> asistenciaDelDia = listaAsistencia.stream().filter(a -> a.getFecha_hora() != null).filter(a -> a.getFecha_hora().isEqual(hoy)).collect(Collectors.toList());
		for (AsistenciaDTO asistenciaDTO : asistenciaDelDia) {
			boolean yaExiste = lista.stream().anyMatch(existe -> existe.getId_asociado().getDocumento().equalsIgnoreCase(asistenciaDTO.getDocumento()) && existe.getFecha().isEqual(asistenciaDTO.getFecha_hora()));
			
			if(!yaExiste) {
				Asistencia asistencia = Asistencia.convertToAasistencia(asistenciaDTO, asociadosRepository.findById(Long.valueOf(asistenciaDTO.getId_asociado())).get(),condicionRepository.findById(Long.valueOf(asistenciaDTO.getId_condicion())).get());
				asistenciaRepository.save(asistencia);
			}
		}
	}
	
	public Asistencia convertToAsistencia(AsistenciaRequestDTO request) {
		Asistencia asistencia = new Asistencia();
		asistencia.setFecha(DateUtil.parseFecha(request.getFecha()));
		asistencia.setHoraEntrada(request.getHoraEntrada());
		asistencia.setHoraSalida(request.getHoraSalida());
		if(request.getId() != null && !request.getId().isEmpty())
		asistencia.setId(Long.valueOf(request.getId()));
		asistencia.setId_asociado(asociadosRepository.findById(Long.valueOf(request.getId_asociado())).get());
		asistencia.setId_condicion(condicionRepository.findById(Long.valueOf(request.getId_condicion())).get());
		asistencia.setObservacion(request.getObservacion());
		return asistencia;
	}
}
