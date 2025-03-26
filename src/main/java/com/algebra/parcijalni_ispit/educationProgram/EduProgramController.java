package com.algebra.parcijalni_ispit.educationProgram;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu-programs")
@RequiredArgsConstructor
public class EduProgramController {
    private final EduProgramService eduProgramService;

    @GetMapping
    public ResponseEntity<List<EducationProgram>> fetchAll(){
        return ResponseEntity.status(200).body(eduProgramService.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationProgram> findById(@RequestParam long id){
        return ResponseEntity.ok(eduProgramService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EducationProgram>create(@Valid @RequestBody CreateEduProgramDto createEduProgramDto){
        EducationProgram educationProgram = eduProgramService.create(createEduProgramDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(educationProgram);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationProgram>create(@Valid @RequestParam long id, @RequestBody UpdateEduProgramDto updateEduProgramDto){
        EducationProgram educationProgram = eduProgramService.update(id, updateEduProgramDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(educationProgram);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(long id){
        eduProgramService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
