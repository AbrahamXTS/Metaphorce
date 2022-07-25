package com.metaphorce.rrhh.services;

import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;                  
import org.springframework.security.crypto.password.PasswordEncoder;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import com.metaphorce.rrhh.DTOs.*;
import com.metaphorce.rrhh.models.User;
import com.metaphorce.rrhh.validators.UserValidator;

import com.metaphorce.rrhh.utilities.WrapperResponse;
import com.metaphorce.rrhh.repositories.UsersRepository;
import com.metaphorce.rrhh.exceptions.ValidatePropertieException;

@Slf4j
@Service
public class UsersService {

	@Value("${jwt.password}")
	private String JWTSecret;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public WrapperResponse<User> createUser(SignupRequestDTO user) {

		User newUser = User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build();
        
        UserValidator.checkNull(newUser);
                
		User existUser = usersRepository.findByUsername(newUser.getUsername())
				.orElse(null);
		
		if (existUser != null) {
			throw new ValidatePropertieException("The username already exist");
		}

		String encoder = passwordEncoder.encode(user.getPassword());
		newUser.setPassword(encoder);
		
		WrapperResponse<User> response = new WrapperResponse<User>(true, "Success", usersRepository.save(newUser));
        return response;
	}
	
	public WrapperResponse<LoginResponseDTO> login(LoginRequestDTO user) {
		
		User userDB = usersRepository.findByUsername(user.getUsername())
				.orElseThrow(() -> new ValidatePropertieException("Wrong access, please check your info."));
		
		if(!passwordEncoder.matches(user.getPassword(), userDB.getPassword())) {
			throw new ValidatePropertieException("Wrong access, please check your info.");
		}

		String token = createToken(userDB);

		UserDTO userLoggedIn = UserDTO.builder()
				.id(userDB.getId())
				.username(userDB.getUsername())
				.build();
		
		WrapperResponse<LoginResponseDTO> response = new WrapperResponse<LoginResponseDTO>(true, "Success", new LoginResponseDTO(userLoggedIn, token));
		return response;
	}

	public String createToken(User user) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + (1000 * 60 * 60 * 8));
		
		return Jwts.builder()
			.setSubject(user.getUsername())
			.setIssuedAt(now)
			.setExpiration(expiryDate)
			.signWith(SignatureAlgorithm.HS512, JWTSecret)
			.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWTSecret).parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e) {
			log.error("JWT in a particular format/configuration that does not match the format expected");
		} catch (MalformedJwtException e) {
			log.error(" JWT was not correctly constructed and should be rejected");
		} catch (SignatureException e) {
			log.error("Signature or verifying an existing signature of a JWT failed");
		} catch (ExpiredJwtException e) {
			log.error("JWT was accepted after it expired and must be rejected");
		}

		return false;
	}
	
	public String getUsernameFromToken(String jwt) {
		try {
			return Jwts.parser().setSigningKey(JWTSecret).parseClaimsJws(jwt).getBody().getSubject();	
		} catch (Exception e) {
			throw new ValidatePropertieException("Invalid Token");
		}	
	}
}