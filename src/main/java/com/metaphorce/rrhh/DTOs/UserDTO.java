package com.metaphorce.rrhh.DTOs;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Integer id;

	private String username;
}