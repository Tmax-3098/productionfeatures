package com.sketch.productionfeatures.client.impl;

import com.sketch.productionfeatures.advice.ApiResponse;
import com.sketch.productionfeatures.client.DriverServiceClient;
import com.sketch.productionfeatures.config.RestClientConfig;
import com.sketch.productionfeatures.dto.DriverDto;
import com.sketch.productionfeatures.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
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

    @Override
    public ApiResponse<List<DriverDto>> getAllDriver() {
        try{
            ApiResponse<List<DriverDto>> driverList = restClient.get()
                    .uri("getDrivers")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return driverList;
        }catch(Exception e){
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
