package org.spring.jpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.spring.jpademo.*")
public class JpaDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApp.class);
    }
}
