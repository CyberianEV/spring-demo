package org.spring.sping_js.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
