package com.algebra.parcijalni_ispit.enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> fetchAll();
    Enrollment findById(long id);
    Enrollment create(CreateEnrollmentDto createEnrollmentDto);
    Enrollment update(long id,UpdateEnrollmentDto updateEnrollmentDto);
    void delete(long id);
}
