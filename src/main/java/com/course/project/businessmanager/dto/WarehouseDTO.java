package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Building;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
public class WarehouseDTO {

    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 35, message = "Name must be between 2 and 35 characters long")
    private String name;

    @Positive(message = "quantity must be positive")
    @Min(value = 1, message = "the lowest value for quantity field is 1")
    private Long quantity;

    @NotBlank(message = "unit of measurement must not be blank")
    @Size(min = 2, max = 10, message = "unit of measurement must be between 2 and 10 characters long")
    private String unitOfMeasurement;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Building building;
}

