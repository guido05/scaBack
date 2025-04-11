package com.sca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sca.model.Asistencia;
import com.sca.model.Dia;

import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository <Asistencia, Long> {

    String asociados = "SELECT a.activo,a.apellido,a.documento," +
            "a.nombre,a.legajo,a.telefono ,a.id_firma," +
            "u.codigo,asi.fecha,asi.hora_entrada,asi.hora_salida,r.fecha_hora, c.descripcion,c.sigla from";
    @Query(value = asociados + " user as u LEFT join reg as r on u.codigo = r.codigo LEFT join asociado as a on u.dni = a.documento LEFT join asistencia as asi on asi.id_asociado = a.id LEFT JOIN Condicion as c on asi.id_condicion = c.id ", nativeQuery = true)
    List<Object[]> findByUserAndRegAndAsociado();
}
