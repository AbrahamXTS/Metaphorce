package com.metaphorce.rrhh.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.metaphorce.rrhh.DTOs.*;
import com.metaphorce.rrhh.models.User;
import com.metaphorce.rrhh.services.UsersService;
import com.metaphorce.rrhh.utilities.WrapperResponse;

import io.swagger.v3.oas.annotations.tags.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;

@RestController()
@Tag(name = "Session", description = "Routes to handle the session.")
public class UserController {
	
	@Autowired
	private UsersService usersService;

	@Operation(summary = "Register.")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "A new user has been registered.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Some data is invalid.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
    })
	@PostMapping(value = "/signup")
	public ResponseEntity<WrapperResponse<User>> createNewUser(@RequestBody SignupRequestDTO newUser) {
		return new ResponseEntity<WrapperResponse<User>>(usersService.createUser(newUser), HttpStatus.CREATED);
	}

	@Operation(summary = "Login.")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Correct user access.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Some data is invalid.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WrapperResponse.class))}),
    })
	@PostMapping(value = "/login")
	public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO user) {
		return new ResponseEntity<WrapperResponse<LoginResponseDTO>>(usersService.login(user), HttpStatus.OK);
	}
}