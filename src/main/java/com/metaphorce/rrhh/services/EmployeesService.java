package com.metaphorce.rrhh.services;

import java.util.*;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import com.metaphorce.rrhh.models.*;
import com.metaphorce.rrhh.exceptions.*;
import com.metaphorce.rrhh.repositories.*;
import com.metaphorce.rrhh.DTOs.EmployeesDTO;
import com.metaphorce.rrhh.utilities.WrapperResponse;
import com.metaphorce.rrhh.validators.EmployeeValidator;

@Service
public class EmployeesService {
    
    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ContractsRepository contractsRepository;

    public WrapperResponse<List<EmployeesDTO>> getAllActiveEmployees() {
        
        List<Employee> employees = employeesRepository.findAll();

        List<EmployeesDTO> response = new ArrayList<EmployeesDTO>();
        
        for (Employee employee : employees) {

            Contract contract = contractsRepository.findByEmployeeId(employee).orElse(null);

            EmployeesDTO dto = EmployeesDTO.builder()
                    .fullName(employee.getName() + " " + employee.getLastName())
                    .taxIdNumber(employee.getTaxIdNumber())
                    .email(employee.getEmail())
                    .contractType(contract != null ? contract.getContractTypeId().getName() : null)
                    .dateFrom(contract != null ? contract.getDateFrom().toString() : null)
                    .dateTo(contract != null ? contract.getDateTo().toString() : null)
                    .salaryPerDay(contract != null ? contract.getSalaryPerDay() : null)
                    .build();
                    
            response.add(dto);
        }

        return new WrapperResponse<List<EmployeesDTO>>(true, "Success", response);
    }

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

    @Transactional
    public WrapperResponse<String> deleteAContract(Integer id) {

        Employee employee = employeesRepository.findById(id)
                .orElseThrow(() -> new NonExistException("The requested employee doesn't exist"));

        Contract actualContract = contractsRepository.findByEmployeeId(employee)
                .orElseThrow(() -> new NonExistException("The requested employee doesn't have a current contract"));

        actualContract.setEmployeeId(null);
        actualContract.setIsActive(false);
        actualContract.setDateTo(new Timestamp(System.currentTimeMillis()));
        contractsRepository.save(actualContract);

        WrapperResponse<String> response = new WrapperResponse<String>(true, "Success", "Contract deleted successfully");
        return response;
    }
}