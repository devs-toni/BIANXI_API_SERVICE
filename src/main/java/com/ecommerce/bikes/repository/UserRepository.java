package com.ecommerce.bikes.repository;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.ecommerce.bikes.entity.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDAO, Long> {
	
	Optional<UserDAO> findByEmail(String email) throws NoSuchElementException;
	
	Optional<UserDAO> findById(long userId) throws NoSuchElementException;

}
