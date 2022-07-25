package com.metaphorce.rrhh.DTOs;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
	
	private UserDTO user;
	
	private String token;
}
