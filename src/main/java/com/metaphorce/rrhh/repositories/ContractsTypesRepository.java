package com.metaphorce.rrhh.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.metaphorce.rrhh.models.ContractType;

@Repository
public interface ContractsTypesRepository extends JpaRepository<ContractType, Short> {}
