package com.metaphorce.rrhh.services;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import com.metaphorce.rrhh.exceptions.*;
import com.metaphorce.rrhh.models.Employee;
import com.metaphorce.rrhh.validators.EmployeeValidator;
import com.metaphorce.rrhh.repositories.EmployeesRepository;
import com.metaphorce.rrhh.utilities.WrapperResponse;

@Service
public class EmployeesService {
    
    @Autowired
    private EmployeesRepository employeesRepository;

    @Transactional
    public WrapperResponse<Employee> createNewEmployee(Employee newEmployee) {

        EmployeeValidator.checkNull(newEmployee); 

        if (employeesRepository.findByTaxIdNumber(newEmployee.getTaxIdNumber()) == null) {
            WrapperResponse<Employee> response = new WrapperResponse<Employee>(true, "Success", employeesRepository.save(newEmployee));
            return response;
        }

        throw new AlreadyExistException("The employee already exists.");
    }

    @Transactional
    public WrapperResponse<Employee> modifyEmployee(Integer id, Employee employee) {

        Employee beforeUpdateEmployee = employeesRepository.findById(id)
                .orElseThrow(() -> new NonExistException("The employee non exists."));

        EmployeeValidator.checkNull(employee); 
        
        if (!beforeUpdateEmployee.getTaxIdNumber().equals(employee.getTaxIdNumber())) {
            if (employeesRepository.findByTaxIdNumber(employee.getTaxIdNumber()) != null) {
                throw new AlreadyExistException("The new tax number already exists.");
            }
        }

        beforeUpdateEmployee.setTaxIdNumber(employee.getTaxIdNumber());
        beforeUpdateEmployee.setName(employee.getName());
        beforeUpdateEmployee.setLastName(employee.getLastName());
        beforeUpdateEmployee.setBirthDate(employee.getBirthDate());
        beforeUpdateEmployee.setEmail((employee.getEmail()));
        beforeUpdateEmployee.setCellPhone(employee.getCellPhone());
        beforeUpdateEmployee.setIsActive(employee.getIsActive());
        beforeUpdateEmployee.setDateCreated(employee.getDateCreated());

        WrapperResponse<Employee> response = new WrapperResponse<Employee>(true, "Success", employeesRepository.save(beforeUpdateEmployee));
        return response;
    }
}