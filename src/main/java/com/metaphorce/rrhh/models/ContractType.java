package com.metaphorce.rrhh.models;

import lombok.Data;
import java.sql.Timestamp;
import javax.persistence.*;

@Data
@Entity
@Table(name = "ContractType")
public class ContractType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short contractTypeId;

    @Column(name = "Name", nullable = false, length = 80)
    private String name;

    @Column(name = "Description", nullable = false, length = 255)
    private String description;

    @Column(name = "IsActive", nullable = false)
    private Boolean isActive;

    @Column(name = "DateCreated", nullable = false)
    private Timestamp dateCreated;
}
