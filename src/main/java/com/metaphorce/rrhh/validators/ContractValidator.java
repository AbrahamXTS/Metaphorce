package com.metaphorce.rrhh.validators;

import com.metaphorce.rrhh.models.Contract;
import com.metaphorce.rrhh.exceptions.ValidatePropertieException;

public class ContractValidator {

    public static void checkNull(Contract contract) {

        if (contract.getEmployeeId() == null) {
            throw new ValidatePropertieException("The user id is required");
        }

        if (contract.getContractTypeId() == null) {
            throw new ValidatePropertieException("The contract type is required");
        }

        if (contract.getDateFrom() == null) {
            throw new ValidatePropertieException("The field email is required");
        }

        if (contract.getDateTo() == null) {
            throw new ValidatePropertieException("The field cellphone is required");
        }

        if (contract.getSalaryPerDay() == null) {
            throw new ValidatePropertieException("The field cellphone is required");
        }

        if (contract.getIsActive() == null) {
            throw new ValidatePropertieException("The field cellphone is required");
        }

        if (contract.getDateCreated() == null) {
            throw new ValidatePropertieException("The field cellphone is required");
        }
    }
}
