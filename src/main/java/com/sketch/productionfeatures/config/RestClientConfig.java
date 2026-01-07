package com.sketch.productionfeatures.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

    @Value("${driver.service.url}")
    private String DRIVER_URL;

    @Bean
    @Qualifier("getDriverRestClient")
    RestClient getDriverRestClient(){
        return RestClient.builder()
                .baseUrl(DRIVER_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res)->{
                    throw new RuntimeException("Server error occured");
                })
                .build();
    }
}
