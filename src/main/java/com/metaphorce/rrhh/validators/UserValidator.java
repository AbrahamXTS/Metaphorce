package com.metaphorce.rrhh.validators;

import com.metaphorce.rrhh.models.User;
import com.metaphorce.rrhh.exceptions.ValidatePropertieException;

public class UserValidator {
    
    public static void checkNull(User user) {

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
			throw new ValidatePropertieException("The username is required");
		}

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new ValidatePropertieException("The user password is required");
		}
    }
}
