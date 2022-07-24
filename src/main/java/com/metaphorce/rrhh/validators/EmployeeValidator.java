package com.metaphorce.rrhh.validators;

import java.util.regex.*;

import com.metaphorce.rrhh.models.Employee;
import com.metaphorce.rrhh.exceptions.ValidateServiceException;

public class EmployeeValidator {

    public static void checkRFC(String taxIdNumber) {
        String regexRFC = "^[A-Z&Ñ]{3,4}[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[A-Z0-9]{2}[0-9A]$";

        if (!Pattern.matches(regexRFC, taxIdNumber)) {
            throw new ValidateServiceException("The tax number is invalid.");
        }
    }

    public static void checkNull(Employee employee) {

        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new ValidateServiceException("The field name is required");
        }

        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            throw new ValidateServiceException("The field lastname is required");
        }

        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            throw new ValidateServiceException("The field email is required");
        }

        if (employee.getCellPhone() == null || employee.getCellPhone().trim().isEmpty()) {
            throw new ValidateServiceException("The field cellphone is required");
        }

        checkRFC(employee.getTaxIdNumber());
    }
}
