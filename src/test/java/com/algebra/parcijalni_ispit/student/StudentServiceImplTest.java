package com.algebra.parcijalni_ispit.student;

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


class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAll_Success(){
        List<Student>studentsMock = new ArrayList<>();
        studentsMock.add(new Student("Ivo","Ivic"));
        studentsMock.add(new Student("Ana","Anic"));
        studentsMock.add(new Student("Marko","Markic"));

        when(studentRepository.findAll()).thenReturn(studentsMock);
        List<Student> studentsResult = studentService.fetchAll();
        verify(studentRepository,times(1)).findAll();

        assertEquals(3, studentsResult.size()); // There should be 3 students
        assertEquals("Ivo", studentsResult.get(0).getFirstName());
        assertEquals("Ana", studentsResult.get(1).getFirstName());
        assertEquals("Marko", studentsResult.get(2).getFirstName());
    }

    @Test
    public void testFindById_Success(){
        Student studentMock = new Student(1,"Ivo","Ivic");

        when(studentRepository.findById(studentMock.getId())).thenReturn(Optional.of(studentMock));
        Student studentResult = studentService.findById(studentMock.getId());
        assertNotNull(studentResult);
        assertEquals(studentMock.getId(), studentResult.getId());
        assertEquals(studentResult.getFirstName(),studentMock.getFirstName());
        assertEquals(studentResult.getLastName(),studentMock.getLastName());
        verify(studentRepository,times(1)).findById(studentMock.getId());
    }

    @Test
    public void testFindById_Fail(){
        assertThrows(EntityNotFoundException.class,()-> studentService.findById(1));
    }

    @Test
    public void testCreateStudent_Success(){
        CreateStudentDto createStudentDtoMock = new CreateStudentDto("NewFirstName","NewLastName");
        Student studentMock = new Student(createStudentDtoMock.getFirstName(),createStudentDtoMock.getLastName());

        when(studentRepository.save(studentMock)).thenReturn(studentMock);
        Student studentResult = studentService.create(createStudentDtoMock);

        assertNotNull(studentResult);
        assertEquals(createStudentDtoMock.getFirstName(),studentResult.getFirstName());
        assertEquals(createStudentDtoMock.getLastName(),studentResult.getLastName());
    }

    @Test
    public void testUpdateStudent_Success(){
        UpdateStudentDto updateStudentDtoMock = new UpdateStudentDto("UpdatedFirstName","UpdatedLastName");
        Student studentMock = new Student(1,updateStudentDtoMock.getFirstName(),updateStudentDtoMock.getLastName());

        when(studentRepository.findById(studentMock.getId())).thenReturn(Optional.of(studentMock));
        when(studentRepository.save(studentMock)).thenReturn(studentMock);

        Student studentResult = studentService.update(studentMock.getId(),updateStudentDtoMock);

        assertNotNull(studentResult);
        assertEquals(studentResult.getFirstName(), updateStudentDtoMock.getFirstName());
        assertEquals(updateStudentDtoMock.getLastName(), studentResult.getLastName());
        assertEquals(studentResult.getId(), studentMock.getId());

        verify(studentRepository,times(1)).save(studentMock);
        verify(studentRepository,times(1)).findById(studentMock.getId());
    }

    @Test
    public void testDelete_Success(){
        Student studentMock = new Student(1,"Ivo","Ivic");
        when(studentRepository.findById(studentMock.getId())).thenReturn(Optional.of(studentMock));
        studentService.delete(1);

        verify(studentRepository,times(1)).delete(studentMock);
    }

    @Test
    public void testDelete_Fail(){
        assertThrows(EntityNotFoundException.class,()-> studentService.delete(1));
    }

}