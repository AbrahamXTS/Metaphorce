package com.metaphorce.rrhh.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.metaphorce.rrhh.DTOs.ContractDTO;
import com.metaphorce.rrhh.models.Contract;
import com.metaphorce.rrhh.services.ContractsService;
import com.metaphorce.rrhh.utilities.WrapperResponse;

import io.swagger.v3.oas.annotations.tags.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController()
// @SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Contracts", description = "Route for create a contract.")
public class ContractController {

    @Autowired
    private ContractsService contractsService;

    @Operation(summary = "Create a new contract.", security = { @SecurityRequirement(name = "JWT Token")})
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Contract created.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Some data is invalid.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "401", description = "Please generate an authorization token in the login route.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
    })
    @PostMapping(value = "/contracts")
    public ResponseEntity<WrapperResponse<Contract>> createNewContract(@RequestBody ContractDTO newContract) {
        return new ResponseEntity<WrapperResponse<Contract>>(contractsService.createNewContract(newContract), HttpStatus.CREATED);
    }
}
