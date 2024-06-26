package org.spring.sping_js.controllers;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.dto.JwtRequest;
import org.spring.sping_js.dto.JwtResponse;
import org.spring.sping_js.exceptions.JsFrontAppError;
import org.spring.sping_js.services.UserService;
import org.spring.sping_js.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/auth")
    public ResponseEntity<?> getAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new JsFrontAppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"),
                    HttpStatus.UNAUTHORIZED
            );
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
