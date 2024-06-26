package org.spring.sping_js.converters;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.dto.UserDto;
import org.spring.sping_js.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final BCryptPasswordEncoder passwordEncoder;

    public User dtoToEntity(UserDto userDto) {
       User user = new User();
       user.setUsername(userDto.getUsername());
       user.setEmail(userDto.getEmail());
       user.setPassword(passwordEncoder.encode(userDto.getPassword()));
       return user;
    }

    public UserDto entityToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
