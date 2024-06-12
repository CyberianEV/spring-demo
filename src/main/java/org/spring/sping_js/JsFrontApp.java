package org.spring.sping_js;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@ComponentScan("org.spring.sping_js")
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class JsFrontApp {
    public static void main(String[] args) {
        SpringApplication.run(JsFrontApp.class, args);
    }
}
