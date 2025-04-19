package com.sca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sca.model.Asociados;

@Repository
public interface AsociadosRepository extends JpaRepository<Asociados, Long> {

    String asociados = "SELECT a.activo,a.apellido,a.documento," +
            "a.nombre,a.legajo,a.telefono ,a.id_firma," +
            "u.codigo,r.fecha_hora from";
    @Query(value = asociados + " user u LEFT JOIN asociado a ON a.documento = u.dni LEFT JOIN reg r ON u.codigo = r.codigo", nativeQuery = true)
    List<Object[]> findByUserAndReg();
}
