package com.algebra.parcijalni_ispit.student;

import java.util.List;

public interface StudentService {
    List<Student> fetchAll();
    Student findById(long id);
    Student create(CreateStudentDto createStudentDto);
    Student update(long id,UpdateStudentDto updateStudentDto);
    void delete(long id);
}
