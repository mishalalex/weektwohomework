package com.office.scranton.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.office.scranton.annotation.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private Long id;

    @Size(min = 3, max = 25, message = "Firstname length should be between 3 to 25")
    @NotBlank(message = "firstname cannot be blank")
    private String firstname;

    @Size(min = 3, max = 25, message = "Lastname length should be between 3 to 25")
    @NotBlank(message = "lastname cannot be blank")
    private String lastname;

    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    @NotBlank(message = "Role should not be blank")
    @EmployeeRoleValidation
    private String role;

    @Positive(message = "Salary should be a positive non zero number")
    @NotNull(message = "Salary of Employee should be not null")
    @Digits(integer = 5, fraction = 2, message = "Invalid Salary Format")
    private Double salary;

    @JsonProperty("isActive")
    private boolean active;
}
