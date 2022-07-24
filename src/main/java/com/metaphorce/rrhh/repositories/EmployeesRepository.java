package com.metaphorce.rrhh.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.metaphorce.rrhh.models.Employee;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {

    Employee findByTaxIdNumber(String taxIdNumber);
}
