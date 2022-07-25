package com.metaphorce.rrhh.DTOs;

import lombok.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

@Data
@Builder
public class ContractDTO implements Serializable {

    private Integer contractId;
    
    private Integer employeeId;
    
    private Short contractTypeId;

    private Timestamp dateFrom;

    @Builder.Default()
    private Timestamp dateTo = new Timestamp(System.currentTimeMillis());

    private BigDecimal salaryPerDay;

    private Boolean isActive;

    @Builder.Default()
    private Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
}
