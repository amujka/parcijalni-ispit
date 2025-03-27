package com.algebra.parcijalni_ispit.educationProgram;


import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EduProgramServiceImplTest {
    @InjectMocks
    private EduProgramServiceImpl eduProgramServiceImplMock;

    @Mock
    private EduProgramRepository eduProgramRepositoryMock;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAll_Success(){
        List<EducationProgram> eduProgramsMock = new ArrayList<>();
        eduProgramsMock.add(new EducationProgram("1",3));
        eduProgramsMock.add(new EducationProgram("2",4));
        eduProgramsMock.add(new EducationProgram("3",5));


        when(eduProgramRepositoryMock.findAll()).thenReturn(eduProgramsMock);
        List<EducationProgram> eduProgramResult = eduProgramServiceImplMock.fetchAll();
        verify(eduProgramRepositoryMock,times(1)).findAll();

        assertEquals(3, eduProgramResult.size()); // There should be 3 students
        assertEquals("1", eduProgramResult.get(0).getName());
        assertEquals("2", eduProgramResult.get(1).getName());
        assertEquals("3", eduProgramResult.get(2).getName());
    }

    @Test
    public void testFindById_Success(){
        EducationProgram eduProgramMock = new EducationProgram(1,"1",3);

        when(eduProgramRepositoryMock.findById(eduProgramMock.getId())).thenReturn(Optional.of(eduProgramMock));
        EducationProgram eduProgramResult = eduProgramServiceImplMock.findById(eduProgramMock.getId());
        assertNotNull(eduProgramResult);
        assertEquals(eduProgramMock.getId(), eduProgramResult.getId());
        assertEquals(eduProgramResult.getName(),eduProgramMock.getName());
        assertEquals(eduProgramResult.getCSVET(),eduProgramMock.getCSVET());
        verify(eduProgramRepositoryMock,times(1)).findById(eduProgramMock.getId());
    }

    @Test
    public void testFindById_Fail(){
        assertThrows(EntityNotFoundException.class,()-> eduProgramServiceImplMock.findById(1));
    }

    @Test
    public void testCreateEduProgram_Success(){
        CreateEduProgramDto createEduProgramDtoMock = new CreateEduProgramDto("1",4);
        EducationProgram eduProgramMock = new EducationProgram(createEduProgramDtoMock.getName(),createEduProgramDtoMock.getCSVET());

        when(eduProgramRepositoryMock.save(eduProgramMock)).thenReturn(eduProgramMock);
        EducationProgram eduProgramResult = eduProgramServiceImplMock.create(createEduProgramDtoMock);

        assertNotNull(eduProgramResult);
        assertEquals(createEduProgramDtoMock.getName(),eduProgramResult.getName());
        assertEquals(createEduProgramDtoMock.getCSVET(),eduProgramResult.getCSVET());
    }

    @Test
    public void testUpdateEduProgram_Success(){
        UpdateEduProgramDto updateEduProgramDtoMock = new UpdateEduProgramDto("1",4);
        EducationProgram eduProgramMock = new EducationProgram(updateEduProgramDtoMock.getName(),updateEduProgramDtoMock.getCSVET());

        when(eduProgramRepositoryMock.findById(eduProgramMock.getId())).thenReturn(Optional.of(eduProgramMock));
        when(eduProgramRepositoryMock.save(eduProgramMock)).thenReturn(eduProgramMock);

        EducationProgram eduProgramResult = eduProgramServiceImplMock.update(eduProgramMock.getId(),updateEduProgramDtoMock);

        assertNotNull(eduProgramResult);
        assertEquals(eduProgramResult.getName(), updateEduProgramDtoMock.getName());
        assertEquals(updateEduProgramDtoMock.getCSVET(), eduProgramResult.getCSVET());
        assertEquals(eduProgramResult.getId(), eduProgramMock.getId());

        verify(eduProgramRepositoryMock,times(1)).save(eduProgramMock);
        verify(eduProgramRepositoryMock,times(1)).findById(eduProgramMock.getId());
    }

    @Test
    public void testDelete_Success(){
        EducationProgram eduProgramMock = new EducationProgram(1,"1",3);
        when(eduProgramRepositoryMock.findById(eduProgramMock.getId())).thenReturn(Optional.of(eduProgramMock));
        eduProgramServiceImplMock.delete(1);

        verify(eduProgramRepositoryMock,times(1)).delete(eduProgramMock);
    }

    @Test
    public void testDelete_Fail(){
        assertThrows(EntityNotFoundException.class,()-> eduProgramServiceImplMock.delete(1));
    }



}