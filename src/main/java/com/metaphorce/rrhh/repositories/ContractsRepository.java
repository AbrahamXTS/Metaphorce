package com.metaphorce.rrhh.repositories;

import java.util.Optional;
import com.metaphorce.rrhh.models.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ContractsRepository extends JpaRepository<Contract, Integer> {

    Optional<Contract> findByEmployeeId(Employee employeeId);
}
