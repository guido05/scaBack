package com.sca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sca.model.Firma;

@Repository
public interface FirmaRepository extends JpaRepository <Firma, Long>{

}
