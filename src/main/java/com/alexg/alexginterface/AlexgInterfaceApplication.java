package com.alexg.alexginterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.alexg.alexginterface","com.alexg.alexgclientsdk"})
public class AlexgInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlexgInterfaceApplication.class, args);
    }

}
