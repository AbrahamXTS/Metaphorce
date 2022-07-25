package com.metaphorce.rrhh.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.metaphorce.rrhh.models.Employee;
import com.metaphorce.rrhh.DTOs.EmployeesDTO;
import com.metaphorce.rrhh.services.EmployeesService;
import com.metaphorce.rrhh.utilities.WrapperResponse;

import io.swagger.v3.oas.annotations.tags.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController()
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Employees", description = "Routes for create and update an employee.")
public class EmployeeController {

    @Autowired
    private EmployeesService employeesService;

    @Operation(summary = "Get all active employees.", security = { @SecurityRequirement(name = "JWT Token")})
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "List of employees.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "400", description = "There are no active employees", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "401", description = "Please generate an authorization token in the login route.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
    })
    @GetMapping(value = "/employees")
    public ResponseEntity<WrapperResponse<List<EmployeesDTO>>> getAllActiveEmployees() {
        return new ResponseEntity<WrapperResponse<List<EmployeesDTO>>>(employeesService.getAllActiveEmployees(), HttpStatus.OK);
    }

    @Operation(summary = "Create a new employee.", security = { @SecurityRequirement(name = "JWT Token")})
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Employee created.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Some data is invalid.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "401", description = "Please generate an authorization token in the login route.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
    })
    @PostMapping(value = "/employees")
    public ResponseEntity<WrapperResponse<Employee>> createNewEmployee(@RequestBody Employee newEmployee) {
        return new ResponseEntity<WrapperResponse<Employee>>(employeesService.createNewEmployee(newEmployee), HttpStatus.CREATED);
    }
    
    @Operation(summary = "Update an employee by id.", security = { @SecurityRequirement(name = "JWT Token")})
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Employee was updated.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Some data is invalid.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "401", description = "Please generate an authorization token in the login route.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
    })
    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<WrapperResponse<Employee>> modifyEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return new ResponseEntity<WrapperResponse<Employee>>(employeesService.modifyEmployee(id, employee), HttpStatus.OK);
    }

    @Operation(summary = "Delete an employee's contract by id.", security = { @SecurityRequirement(name = "JWT Token")})
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Employee's contract was deleted.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "400", description = "The employee doesn't have a current contract or does not exist.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "401", description = "Please generate an authorization token in the login route.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
    })
    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<WrapperResponse<String>> deleteAContract(@PathVariable Integer id) {
        return new ResponseEntity<WrapperResponse<String>>(employeesService.deleteAContract(id), HttpStatus.OK);
    }
}