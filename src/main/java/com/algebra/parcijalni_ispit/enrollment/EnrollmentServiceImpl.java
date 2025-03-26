package com.algebra.parcijalni_ispit.enrollment;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;
    @Override
    public List<Enrollment> fetchAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment findById(long id) {
        return enrollmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Enrollment Not Found"));
    }

    @Override
    public Enrollment create(CreateEnrollmentDto createEnrollmentDto) {
        Enrollment enrollment = new Enrollment(createEnrollmentDto.getStudentId(),createEnrollmentDto.getEducationProgramId());
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment update(long id, UpdateEnrollmentDto updateEnrollmentDto) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        if (enrollment.isEmpty())throw new EntityNotFoundException("No Enrollment found");
        Enrollment updatedEnrollment =  enrollment.get();
        updatedEnrollment.setStudentId(updatedEnrollment.getStudentId());
        updatedEnrollment.setEducationProgramId(updatedEnrollment.getEducationProgramId());
        return enrollmentRepository.save(updatedEnrollment);
    }

    @Override
    public void delete(long id) {
        enrollmentRepository
                .findById(id)
                .ifPresentOrElse(enrollmentRepository::delete,()-> { throw new EntityNotFoundException("Enrollment not found");});
    }
}
