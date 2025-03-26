package com.algebra.parcijalni_ispit.enrollment;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEnrollmentDto {
    @Min(1)
    private long studentId;
    @Min(1)
    private long educationProgramId;
}
