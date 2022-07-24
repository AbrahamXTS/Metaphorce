package com.metaphorce.rrhh.models;

import lombok.*;
import java.sql.Timestamp;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    
    @Builder.Default()
    @Column(name = "DateTo", nullable = false)
    private Timestamp dateTo = new Timestamp(System.currentTimeMillis());

    @Column(name = "SalaryPerDay", nullable = false)
    private BigDecimal salaryPerDay;

    @Column(name = "IsActive", nullable = false)
    private Boolean isActive;

    @Builder.Default()
    @Column(name = "DateCreated", nullable = false)
    private Timestamp dateCreated = new Timestamp(System.currentTimeMillis());
}
