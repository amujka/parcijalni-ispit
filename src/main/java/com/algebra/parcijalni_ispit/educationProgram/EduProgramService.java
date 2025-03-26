package com.algebra.parcijalni_ispit.educationProgram;

import java.util.List;

public interface EduProgramService {
    List<EducationProgram> fetchAll();
    EducationProgram findById(long id);
    EducationProgram create(CreateEduProgramDto createEduProgramDto);
    EducationProgram update(long id, UpdateEduProgramDto updateEduProgramDto);
    void delete(long id);
}
