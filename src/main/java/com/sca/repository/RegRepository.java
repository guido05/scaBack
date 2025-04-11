package com.sca.repository;

import com.sca.model.Reg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegRepository extends JpaRepository<Reg, Long> {
}
