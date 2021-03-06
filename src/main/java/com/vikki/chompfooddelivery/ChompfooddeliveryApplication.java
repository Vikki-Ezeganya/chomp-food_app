package com.vikki.chompfooddelivery;

import com.vikki.chompfooddelivery.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ChompfooddeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChompfooddeliveryApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContext springApplicationContext() {
        return  new SpringApplicationContext();
    }

    @Bean(name = "AppProperties")
    public AppProperties appProperties() {
        return  new AppProperties();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}


