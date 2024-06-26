package org.spring.sping_js.controllers;


import lombok.extern.slf4j.Slf4j;
import org.spring.sping_js.dto.ProfileDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @GetMapping
    public ProfileDto getCurrentUsername(Principal principal) {
        return new ProfileDto(principal.getName());
    }
}
