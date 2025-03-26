package com.algebra.parcijalni_ispit.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateStudentDto {
    @NotBlank(message = "firstname is required")
    @Size(min = 3, max = 10)
    private String firstName;
    @NotBlank(message = "lastName is required")
    @Size(min = 3, max = 20)
    private String lastName;
}
