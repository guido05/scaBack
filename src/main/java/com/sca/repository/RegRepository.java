package com.sca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sca.model.Reg;

@Repository
public interface RegRepository extends JpaRepository<Reg, Long> {
}
