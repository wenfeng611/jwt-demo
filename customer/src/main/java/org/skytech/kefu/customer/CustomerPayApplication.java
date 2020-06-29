package org.skytech.kefu.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories(basePackages = {"org.skytech.kefu"})
@ComponentScan(basePackages = {"org.skytech.kefu.common","org.skytech.kefu.customer"})
@EntityScan(basePackages = {"org.skytech.kefu.common.entity"})
public class CustomerPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerPayApplication.class, args);
    }
}
