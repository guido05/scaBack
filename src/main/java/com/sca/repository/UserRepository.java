package com.sca.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sca.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByDni(String dni);
	
	@Transactional
	@Modifying
	void deleteByDni(String dni);
}
