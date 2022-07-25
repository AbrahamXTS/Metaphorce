package com.metaphorce.rrhh.repositories;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.metaphorce.rrhh.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByUsername(String username);
}