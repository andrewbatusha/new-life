package com.course.project.businessmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequestDTO {
    private String email;
    private String password;

}

