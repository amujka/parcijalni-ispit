package com.algebra.parcijalni_ispit.educationProgram;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EduProgramServiceImpl implements EduProgramService{
    private final EduProgramRepository eduProgramRepository;

    @Override
    public List<EducationProgram> fetchAll() {
        return eduProgramRepository.findAll();
    }

    @Override
    public EducationProgram findById(long id) {
        return eduProgramRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Program Not Found"));
    }

    @Override
    public EducationProgram create(CreateEduProgramDto createEduProgramDto) {
        EducationProgram educationProgram = new EducationProgram(createEduProgramDto.getName(),createEduProgramDto.getCSVET());
        return eduProgramRepository.save(educationProgram);
    }

    @Override
    public EducationProgram update(long id, UpdateEduProgramDto updateEduProgramDto) {
        Optional<EducationProgram> educationProgram = eduProgramRepository.findById(id);
        if (educationProgram.isEmpty())throw new EntityNotFoundException("No Program found");
        EducationProgram updatedEducationProgram =  educationProgram.get();
        updatedEducationProgram.setName(updatedEducationProgram.getName());
        updatedEducationProgram.setCSVET(updatedEducationProgram.getCSVET());
        return eduProgramRepository.save(updatedEducationProgram);
    }

    @Override
    public void delete(long id) {
        eduProgramRepository
                .findById(id)
                .ifPresentOrElse(eduProgramRepository::delete,()-> { throw new EntityNotFoundException("Program not found");});
    }

}
