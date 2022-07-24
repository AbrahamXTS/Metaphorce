package com.metaphorce.rrhh.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.metaphorce.rrhh.models.Contract;
import com.metaphorce.rrhh.models.Employee;

@Repository
public interface ContractsRepository extends JpaRepository<Contract, Integer> {

    Contract findByEmployeeId(Employee employeeId);
}
