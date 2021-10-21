package com.training.microservices.birthday;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BirthdayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BirthdayApplication.class, args);
    }

}
