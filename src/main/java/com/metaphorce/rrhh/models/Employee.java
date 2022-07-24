package com.metaphorce.rrhh.models;

import lombok.*;
import java.sql.*;
import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(name = "TaxIdNumber", nullable = false, unique = true, length = 13)
    private String taxIdNumber;
    
    @Column(name = "Name", nullable = false, length = 60)
    private String name;
    
    @Column(name = "LastName", nullable = false, length = 120)
    private String lastName;
    
    @Column(name = "BirthDate", nullable = false)
    private Date birthDate;
    
    @Column(name = "Email", nullable = false, length = 60)
    private String email;
    
    @Column(name = "CellPhone", nullable = false, length = 20)
    private String cellPhone;
    
    @Column(name = "IsActive", nullable = false)
    private Boolean isActive;
    
    @Column(name = "DateCreated", nullable = false)
    private Timestamp dateCreated;
}
