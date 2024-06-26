package org.spring.sping_js.validators;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.dto.UserDto;
import org.spring.sping_js.entities.User;
import org.spring.sping_js.exceptions.ValidationException;
import org.spring.sping_js.services.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserService userService;

    public void validateFields(UserDto userDto) {
        List<String> errors = new ArrayList<>();
        if (userDto.getUsername() == null || userDto.getUsername().isBlank()) {
            errors.add("Username cannot be empty");
        }
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            errors.add("E-mail address cannot be empty");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isBlank()) {
            errors.add("Password cannot be empty");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public void validateUniqueness(UserDto userDto) {
        List<String> errors = new ArrayList<>();
        String username = userDto.getUsername();
        String email = userDto.getEmail();
        if (userService.findUserByName(username).isPresent()) {
            errors.add(String.format("User with username %s already exists", username));
        }
        if (userService.findUserByEmail(email).isPresent()) {
            errors.add(String.format("User with e-mail address %s already exists", email));
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
