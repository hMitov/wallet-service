package com.paymentsystem.restcontroller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.paymentsystem.domain", "com.paymentsystem.restcontroller"})
@EnableJpaRepositories("com.paymentsystem.domain.repository")
@EntityScan("com.paymentsystem.domain.entity")
public class WalletServiceApplication {

    @Bean("billing")
    public WebClient getWebClientBuilderBilling() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082/api")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean("customers")
    public WebClient getWebClientBuilderCustomers() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081/api")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }



    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }

}
