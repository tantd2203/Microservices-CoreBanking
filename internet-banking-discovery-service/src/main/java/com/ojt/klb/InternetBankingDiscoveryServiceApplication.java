package com.ojt.klb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class InternetBankingDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternetBankingDiscoveryServiceApplication.class, args);
    }

}
