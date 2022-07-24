package com.metaphorce.rrhh.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.metaphorce.rrhh.models.Employee;
import com.metaphorce.rrhh.services.EmployeesService;
import com.metaphorce.rrhh.utilities.WrapperResponse;

import io.swagger.v3.oas.annotations.tags.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;

@RestController()
@Tag(name = "Employees", description = "Routes")
public class EmployeeController {

    @Autowired
    private EmployeesService employeesService;

    @Operation(summary = "Create a new employee.")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Employee created.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
        @ApiResponse(responseCode = "400", description = "Some data is invalid.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
    })
    @PostMapping(value = "/employees")
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee newEmployee) {
        return new ResponseEntity<Employee>(employeesService.createNewEmployee(newEmployee), HttpStatus.OK);
    }
    
    @Operation(summary = "Update an employee by id.")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Employee was updated.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
        @ApiResponse(responseCode = "400", description = "Some data is invalid.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
    })
    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeesService.modifyEmployee(id, employee), HttpStatus.OK);
    }
}