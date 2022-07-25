package com.metaphorce.rrhh.DTOs;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

	private String username;
	
	private String password;
}
