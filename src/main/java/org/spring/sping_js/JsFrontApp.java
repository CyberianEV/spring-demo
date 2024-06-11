package org.spring.sping_js;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.spring.sping_js")
public class JsFrontApp {
    public static void main(String[] args) {
        SpringApplication.run(JsFrontApp.class, args);
    }
}
