package com.metaphorce.rrhh.DTOs;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDTO {

	private String username;
	
	private String password;
}
