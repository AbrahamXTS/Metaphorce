package com.metaphorce.rrhh.models;

import lombok.Data;
import java.sql.Timestamp;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Contract")
public class Contract {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    @OneToOne()
    @JoinColumn(name = "EmployeeId", nullable = false)
    private Employee employeeId;

    @ManyToOne
    @JoinColumn(name = "ContractTypeId", nullable = false)
    private ContractType contractTypeId;

    @Column(name = "DateFrom", nullable = false)
    private Timestamp dateFrom;

    @Column(name = "DateTo", nullable = false)
    private Timestamp dateTo;

    @Column(name = "SalaryPerDay", nullable = false)
    private BigDecimal salaryPerDay;

    @Column(name = "IsActive", nullable = false)
    private Boolean isActive;

    @Column(name = "DateCreated", nullable = false)
    private Timestamp DateCreated;
}
