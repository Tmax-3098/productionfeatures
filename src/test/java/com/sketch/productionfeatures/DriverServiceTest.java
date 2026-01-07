package com.sketch.productionfeatures;

import com.sketch.productionfeatures.client.impl.DriverServiceClientImpl;
import com.sketch.productionfeatures.dto.DriverDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class DriverServiceTest {

    @Autowired
    private DriverServiceClientImpl driverServiceClient;


    @Test
    void getAllDriversTest1(){
        System.out.println(driverServiceClient.getAllDriver());
    }

    @Test
    void getDriverByIdTest(){
        System.out.println(driverServiceClient.getDriverById(1L));
    }

    @Test
    void createDriverTest(){
        DriverDto driver = DriverDto.builder().name("alex albon").team("williams racing").role("driver").age(27).dateOfJoining(LocalDate.of(2019,12,12)).isActive(true).build();
        System.out.println(driverServiceClient.createDriver(driver));
    }
}
