package br.com.futurodev.api.primeiraapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.TimeZone;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication //(exclude = { SecurityAutoConfiguration.class }) //para o springboot security
public class Application {
    public static void main(String[] args) {
        //linha principal que roda o projeto java spring boot
       //TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Application.class, args);
    }
}
