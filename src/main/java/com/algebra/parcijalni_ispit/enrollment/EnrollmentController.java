package com.algebra.parcijalni_ispit.enrollment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<Enrollment>> fetchAll(){
        return ResponseEntity.status(200).body(enrollmentService.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> findById(@RequestParam long id){
        return ResponseEntity.ok(enrollmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Enrollment>create(@Valid @RequestBody CreateEnrollmentDto createEnrollmentDto){
        Enrollment enrollment = enrollmentService.create(createEnrollmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment>create(@Valid @RequestParam long id, @RequestBody UpdateEnrollmentDto updateEnrollmentDto){
        Enrollment enrollment = enrollmentService.update(id, updateEnrollmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(long id){
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
