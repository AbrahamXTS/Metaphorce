package com.metaphorce.rrhh.DTOs;

import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class EmployeesDTO implements Serializable {

    private String fullName;

    private String taxIdNumber;

    private String email;

    @Builder.Default()
    private String contractType = null;

    @Builder.Default()
    private String dateFrom = null;

    @Builder.Default()
    private String dateTo = null;

    @Builder.Default()
    private BigDecimal salaryPerDay = null;
}
