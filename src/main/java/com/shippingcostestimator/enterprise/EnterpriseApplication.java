package com.shippingcostestimator.enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

// Starts up Spring Boot instance

@SpringBootApplication
@EnableCaching
public class EnterpriseApplication {

    /***
     * Main entry point for the program.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EnterpriseApplication.class, args);
    }
}