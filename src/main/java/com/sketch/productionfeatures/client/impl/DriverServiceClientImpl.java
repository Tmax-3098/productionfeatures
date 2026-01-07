package com.sketch.productionfeatures.client.impl;

import com.sketch.productionfeatures.advice.ApiResponse;
import com.sketch.productionfeatures.client.DriverServiceClient;
import com.sketch.productionfeatures.config.RestClientConfig;
import com.sketch.productionfeatures.dto.DriverDto;
import com.sketch.productionfeatures.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverServiceClientImpl implements DriverServiceClient {

    private final RestClient restClient;
    Logger log = LoggerFactory.getLogger(DriverServiceClientImpl.class);

    @Override
    public ApiResponse<List<DriverDto>> getAllDriver() {
//        log.info("info log");
//        log.error("error log");
//        log.warn("warn log");
//        log.debug("debug log");
//        log.trace("trace log");

        try{
            ApiResponse<List<DriverDto>> driverList = restClient.get()
                    .uri("getDrivers")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.info("Successfully retrieved the employees");
            return driverList;

        }catch(Exception e){
            log.error("Exception occurred in get all employees", e);
            throw new RuntimeException(e);
        }


    }

    @Override
    public ApiResponse<DriverDto> getDriverById(Long id) {
        try{
            ApiResponse<DriverDto> driver= restClient.get()
                    .uri("driver/{id}",id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return driver;

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ApiResponse<DriverDto> createDriver(DriverDto driver) {
        try{
            return restClient.post()
                    .uri("createDriver")
                    .body(driver)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->{
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("counld not create resource");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
