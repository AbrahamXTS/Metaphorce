package com.metaphorce.rrhh.services;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import com.metaphorce.rrhh.exceptions.*;
import com.metaphorce.rrhh.models.Employee;
import com.metaphorce.rrhh.validators.EmployeeValidator;
import com.metaphorce.rrhh.repositories.EmployeesRepository;

@Service
public class EmployeesService {
    
    @Autowired
    private EmployeesRepository employeesRepository;

    @Transactional
    public Employee createNewEmployee(Employee newEmployee) {

        EmployeeValidator.checkNull(newEmployee); 

        if (employeesRepository.findByTaxIdNumber(newEmployee.getTaxIdNumber()) == null) {
            return employeesRepository.save(newEmployee);
        }

        throw new AlreadyExistException("The employee already exists.");
    }

    @Transactional
    public Employee modifyEmployee(Integer id, Employee employee) {

        Optional<Employee> beforeUpdateEmployee = employeesRepository.findById(id);

        if (beforeUpdateEmployee == null) {
            throw new NonExistException("The employee non exists.");
        }

        EmployeeValidator.checkNull(employee); 
        
        if (!beforeUpdateEmployee.get().getTaxIdNumber().equals(employee.getTaxIdNumber())) {
            if (employeesRepository.findByTaxIdNumber(employee.getTaxIdNumber()) != null) {
                throw new AlreadyExistException("The new tax number already exists.");
            }
        }

        beforeUpdateEmployee.get().setTaxIdNumber(employee.getTaxIdNumber());
        beforeUpdateEmployee.get().setName(employee.getName());
        beforeUpdateEmployee.get().setLastName(employee.getLastName());
        beforeUpdateEmployee.get().setBirthDate(employee.getBirthDate());
        beforeUpdateEmployee.get().setEmail((employee.getEmail()));
        beforeUpdateEmployee.get().setCellPhone(employee.getCellPhone());
        beforeUpdateEmployee.get().setIsActive(employee.getIsActive());
        beforeUpdateEmployee.get().setDateCreated(employee.getDateCreated());

        return employeesRepository.save(beforeUpdateEmployee.get());
    }
}