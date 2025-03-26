package com.algebra.parcijalni_ispit.educationProgram;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEduProgramDto {
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "CSVET is required")
    private int CSVET;
}
