package com.sketch.productionfeatures.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DriverDto {

    private Long id;

    private String name;


    private String team;


    private Integer age;

    //@Pattern(regexp = "^(DRIVER|ENGINEER)$", message = "role can be either DRIVER or ENGINEER")

    private String role;

    private LocalDate dateOfJoining;

    private Boolean isActive;
}

