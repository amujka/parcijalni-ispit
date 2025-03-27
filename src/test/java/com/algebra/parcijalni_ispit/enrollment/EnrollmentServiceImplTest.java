package com.algebra.parcijalni_ispit.enrollment;

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

class EnrollmentServiceImplTest {

    @InjectMocks
    private EnrollmentServiceImpl enrollmentServiceImplMock;

    @Mock
    private EnrollmentRepository enrollmentRepositoryMock;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchAll_Success(){
        List<Enrollment> enrollmentsMock = new ArrayList<>();
        enrollmentsMock.add(new Enrollment(1,1));
        enrollmentsMock.add(new Enrollment(2,2));
        enrollmentsMock.add(new Enrollment(3,3));
        enrollmentsMock.add(new Enrollment(4,3));



        when(enrollmentRepositoryMock.findAll()).thenReturn(enrollmentsMock);
        List<Enrollment> enrollmentResult = enrollmentServiceImplMock.fetchAll();
        verify(enrollmentRepositoryMock,times(1)).findAll();

        assertEquals(4, enrollmentResult.size());
        assertEquals(1, enrollmentResult.get(0).getStudentId());
        assertEquals(2, enrollmentResult.get(1).getStudentId());
        assertEquals(3, enrollmentResult.get(2).getStudentId());
        assertEquals(4, enrollmentResult.get(3).getStudentId());
    }

    @Test
    public void testFindById_Success(){
        Enrollment enrollmentMock = new Enrollment(1,1);

        when(enrollmentRepositoryMock.findById(enrollmentMock.getId())).thenReturn(Optional.of(enrollmentMock));
        Enrollment enrollmentResult = enrollmentServiceImplMock.findById(enrollmentMock.getId());
        assertNotNull(enrollmentResult);
        assertEquals(enrollmentMock.getId(), enrollmentResult.getId());
        assertEquals(enrollmentResult.getStudentId(),enrollmentMock.getStudentId());
        assertEquals(enrollmentResult.getEducationProgramId(),enrollmentMock.getEducationProgramId());
        verify(enrollmentRepositoryMock,times(1)).findById(enrollmentMock.getId());
    }

    @Test
    public void testFindById_Fail(){
        assertThrows(EntityNotFoundException.class,()-> enrollmentServiceImplMock.findById(1));
    }

    @Test
    public void testCreateEnrollment_Success(){
        CreateEnrollmentDto createEnrollmentDtoMock = new CreateEnrollmentDto(1,4);
        Enrollment enrollmentMock = new Enrollment(createEnrollmentDtoMock.getStudentId(),createEnrollmentDtoMock.getEducationProgramId());

        when(enrollmentRepositoryMock.save(enrollmentMock)).thenReturn(enrollmentMock);
        Enrollment enrollmentResult = enrollmentServiceImplMock.create(createEnrollmentDtoMock);

        assertNotNull(enrollmentResult);
        assertEquals(createEnrollmentDtoMock.getStudentId(),enrollmentResult.getStudentId());
        assertEquals(createEnrollmentDtoMock.getEducationProgramId(),enrollmentResult.getEducationProgramId());
    }

    @Test
    public void testUpdateEnrollment_Success(){
        UpdateEnrollmentDto updateEnrollmentDtoMock = new UpdateEnrollmentDto(1,4);
        Enrollment enrollmentMock = new Enrollment(updateEnrollmentDtoMock.getStudentId(),updateEnrollmentDtoMock.getEducationProgramId());

        when(enrollmentRepositoryMock.findById(enrollmentMock.getId())).thenReturn(Optional.of(enrollmentMock));
        when(enrollmentRepositoryMock.save(enrollmentMock)).thenReturn(enrollmentMock);

        Enrollment enrollmentResult = enrollmentServiceImplMock.update(enrollmentMock.getId(),updateEnrollmentDtoMock);

        assertNotNull(enrollmentResult);
        assertEquals(enrollmentResult.getStudentId(), updateEnrollmentDtoMock.getStudentId());
        assertEquals(updateEnrollmentDtoMock.getEducationProgramId(), enrollmentResult.getEducationProgramId());
        assertEquals(enrollmentResult.getId(), enrollmentMock.getId());

        verify(enrollmentRepositoryMock,times(1)).save(enrollmentMock);
        verify(enrollmentRepositoryMock,times(1)).findById(enrollmentMock.getId());
    }

    @Test
    public void testDelete_Success(){
        Enrollment enrollmentMock = new Enrollment(1,1,3);
        when(enrollmentRepositoryMock.findById(enrollmentMock.getId())).thenReturn(Optional.of(enrollmentMock));
        enrollmentServiceImplMock.delete(1);

        verify(enrollmentRepositoryMock,times(1)).delete(enrollmentMock);
    }

    @Test
    public void testDelete_Fail(){
        assertThrows(EntityNotFoundException.class,()-> enrollmentServiceImplMock.delete(1));
    }

}