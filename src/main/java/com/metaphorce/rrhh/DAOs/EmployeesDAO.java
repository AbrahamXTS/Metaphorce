package com.metaphorce.rrhh.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metaphorce.rrhh.models.Employee;

public interface EmployeesDAO extends JpaRepository<Employee, Integer> {}
