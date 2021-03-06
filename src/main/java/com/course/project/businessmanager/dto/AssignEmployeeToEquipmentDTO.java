package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class AssignEmployeeToEquipmentDTO {
    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 35, message = "Name must be between 2 and 35 characters long")
    private String name;

    @Positive(message = "quantity must be positive")
    @Min(value = 1, message = "the lowest value for quantity field is 1")
    private Long quantity;

    @Positive(message = "price must be positive")
    @Min(value = 1, message = "the lowest value for price field is 1")
    private Long price;

    private Employee employee;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Building building;

    @Email(message = "input email must be a well-formed")
    @Size(min = 5, max = 40, message = "Email must be between 2 and 35 characters long")
    @NotBlank(message = "Email cannot be empty")
    private String email;
}
