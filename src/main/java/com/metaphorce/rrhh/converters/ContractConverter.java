package com.metaphorce.rrhh.converters;

import com.metaphorce.rrhh.models.*;
import com.metaphorce.rrhh.repositories.*;
import com.metaphorce.rrhh.DTOs.ContractDTO;
import com.metaphorce.rrhh.exceptions.NonExistException;
import org.springframework.beans.factory.annotation.Autowired;

public class ContractConverter extends AbstractConverters<Contract, ContractDTO> {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ContractsTypesRepository contractsTypesRepository;

    @Override
    public ContractDTO fromEntity(Contract entity) {
        return ContractDTO.builder()
            .contractId(entity.getContractId())
            .employeeId(entity.getEmployeeId().getEmployeeId())
            .contractTypeId(entity.getContractTypeId().getContractTypeId())
            .dateFrom(entity.getDateFrom())
            .dateTo(entity.getDateTo())
            .salaryPerDay(entity.getSalaryPerDay())
            .isActive(entity.getIsActive())
            .dateCreated(entity.getDateCreated())
            .build();
    }

    @Override
    public Contract fromDTO(ContractDTO dto) {

        Employee employee = employeesRepository.findById(dto.getEmployeeId())
            .orElseThrow(() -> new NonExistException("The contract employee does not exist."));

        ContractType contractType = contractsTypesRepository.findById(dto.getContractTypeId())
            .orElseThrow(() -> new NonExistException("The type of contract does not exist."));

        return Contract.builder()
            .contractId(dto.getContractId())
            .employeeId(employee)
            .contractTypeId(contractType)
            .dateFrom(dto.getDateFrom())
            .dateTo(dto.getDateTo())
            .salaryPerDay(dto.getSalaryPerDay())
            .isActive(dto.getIsActive())
            .dateCreated(dto.getDateCreated())
            .build();
    }
}
