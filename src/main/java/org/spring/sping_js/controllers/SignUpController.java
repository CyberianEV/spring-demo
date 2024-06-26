package org.spring.sping_js.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.converters.UserConverter;
import org.spring.sping_js.dto.UserDto;
import org.spring.sping_js.entities.User;
import org.spring.sping_js.services.UserService;
import org.spring.sping_js.validators.UserValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final UserValidator userValidator;
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping("/signup")
    public ResponseEntity<?> registerNewUser(@RequestBody UserDto userDto) {
        userValidator.validateFields(userDto);
        userValidator.validateUniqueness(userDto);
        User user = userService.addNewDefaultUser(userConverter.dtoToEntity(userDto));
        return ResponseEntity.ok(userConverter.entityToDTO(user));
    }
}
