package com.algebra.parcijalni_ispit.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>>fetchAll(){
        return ResponseEntity.status(200).body(studentService.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@RequestParam long id){
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Student>create(@RequestBody CreateStudentDto createStudentDto){
        Student student = studentService.create(createStudentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student>create(@RequestParam long id, @RequestBody UpdateStudentDto updateStudentDto){
        Student student = studentService.update(id, updateStudentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(long id){
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
