package com.metaphorce.rrhh.services;

import java.sql.Timestamp;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.metaphorce.rrhh.models.*;
import com.metaphorce.rrhh.repositories.*;
import com.metaphorce.rrhh.DTOs.ContractDTO;
import com.metaphorce.rrhh.utilities.WrapperResponse;
import com.metaphorce.rrhh.exceptions.NonExistException;
import com.metaphorce.rrhh.validators.ContractValidator;

@Service
public class ContractsService {

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ContractsTypesRepository contractsTypesRepository;

    @Transactional
    public WrapperResponse<Contract> createNewContract(ContractDTO contract) {

        Employee employee = employeesRepository.findById(contract.getEmployeeId())
            .orElseThrow(() -> new NonExistException("The contract employee does not exist."));

        ContractType contractType = contractsTypesRepository.findById(contract.getContractTypeId())
            .orElseThrow(() -> new NonExistException("The type of contract does not exist."));

        Contract newContract = Contract.builder()
            .contractId(contract.getContractId())
            .employeeId(employee)
            .contractTypeId(contractType)
            .dateFrom(contract.getDateFrom())
            .dateTo(contract.getDateTo())
            .salaryPerDay(contract.getSalaryPerDay())
            .isActive(contract.getIsActive())
            .dateCreated(contract.getDateCreated())
            .build();
        
        ContractValidator.checkNull(newContract);

        // If exist a current contract for the employee, we will update the date.
        Optional<Contract> actualContract = contractsRepository.findByEmployeeId(newContract.getEmployeeId());

        if (actualContract.isPresent()) {
            actualContract.get().setEmployeeId(null);
            actualContract.get().setIsActive(false);
            actualContract.get().setDateTo(new Timestamp(System.currentTimeMillis()));
            contractsRepository.save(actualContract.get());
        }

        // Else or after update the current contract, we create the new contract
        return new WrapperResponse<Contract>(true, "Success", contractsRepository.save(newContract));
    }
}
