package com.ecommerce.bikes.repository;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email) throws NoSuchElementException;
	
	Optional<User> findById(long userId) throws NoSuchElementException;
	

}
