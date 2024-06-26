package org.spring.sping_js.services;

import lombok.RequiredArgsConstructor;
import org.spring.sping_js.entities.Role;
import org.spring.sping_js.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
