package com.ecommerce.bikes.repositories;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.ecommerce.bikes.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email) throws NoSuchElementException;
	
	Optional<UserEntity> findById(long userId) throws NoSuchElementException;

}
