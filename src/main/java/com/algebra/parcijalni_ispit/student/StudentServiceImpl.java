package com.algebra.parcijalni_ispit.student;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    @Override
    public List<Student> fetchAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("StudentNotFound"));
    }

    @Override
    public Student create(CreateStudentDto createStudentDto) {
        Student student = new Student(createStudentDto.getFirstName(),createStudentDto.getLastName());
        return studentRepository.save(student);
    }

    @Override
    public Student update(long id, UpdateStudentDto updateStudentDto) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty())throw new EntityNotFoundException("No Student found");
        Student updatedStudent =  student.get();
        updatedStudent.setFirstName(updatedStudent.getFirstName());
        updatedStudent.setLastName(updatedStudent.getLastName());
        return studentRepository.save(updatedStudent);
    }

    @Override
    public void delete(long id) {
        studentRepository
                .findById(id)
                .ifPresentOrElse(studentRepository::delete,()-> { throw new EntityNotFoundException("Student not found");});
    }
}
