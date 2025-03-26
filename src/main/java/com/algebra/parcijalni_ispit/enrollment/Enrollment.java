package com.algebra.parcijalni_ispit.enrollment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Min(1)
    private long studentId;
    @Min(1)
    private long educationProgramId;

    public Enrollment(long studentId, long educationProgramId) {
        this.studentId = studentId;
        this.educationProgramId = educationProgramId;
    }
}
