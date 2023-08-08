package com.ecommerce.bikes.repository;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.ecommerce.bikes.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email) throws NoSuchElementException;
	
	Optional<UserEntity> findById(long userId) throws NoSuchElementException;

}
