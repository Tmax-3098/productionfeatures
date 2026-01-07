package com.sketch.productionfeatures.client;

import com.sketch.productionfeatures.advice.ApiResponse;
import com.sketch.productionfeatures.dto.DriverDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverServiceClient {

    ApiResponse<List<DriverDto>> getAllDriver();

    ApiResponse<DriverDto> getDriverById(Long id);

    ApiResponse<DriverDto> createDriver(DriverDto driver);
}
