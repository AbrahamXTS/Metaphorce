package com.metaphorce.rrhh.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.metaphorce.rrhh.models.Contract;

@Repository
public interface ContractsRepository extends JpaRepository<Contract, Integer> {}
