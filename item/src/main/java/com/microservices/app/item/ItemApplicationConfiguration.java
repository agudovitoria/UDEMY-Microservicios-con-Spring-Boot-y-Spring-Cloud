package com.microservices.app.item;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ItemApplicationConfiguration {
  @Bean
  RestTemplate restTemplateRegister(RestTemplateBuilder builder) {
    return builder.build();
  }
}
