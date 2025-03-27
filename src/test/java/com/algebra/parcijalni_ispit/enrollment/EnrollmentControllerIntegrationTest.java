package com.algebra.parcijalni_ispit.enrollment;

import com.algebra.parcijalni_ispit.Auth.AuthController;
import com.algebra.parcijalni_ispit.Auth.dto.LoginDto;
import com.algebra.parcijalni_ispit.Auth.dto.LoginResponseDto;
import com.algebra.parcijalni_ispit.educationProgram.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EnrollmentControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EnrollmentService enrollmentServiceMock;

    @Autowired
    private EduProgramController enrolEduProgramControllerMock;

    @Autowired
    private EnrollmentRepository enrollmentRepositoryMock;

    @Autowired
    private AuthController authController;

    private  String accessToken;

private final CreateEnrollmentDto createEnrollmentDtoMock = new CreateEnrollmentDto(1,2);
public final UpdateEnrollmentDto updateEnrollmentDtoMock = new UpdateEnrollmentDto(2,4);
public Enrollment enrollmentMock = new Enrollment(1,2,3);

    @BeforeEach
    void init(){
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("password");
        LoginResponseDto loginResponseDto = authController.login(loginDto).getBody();
        accessToken = loginResponseDto.getAccessToken();
    }

    @Test
    @Order(1)
    void testFetchAll() throws Exception {
        mockMvc.perform(get("/enrollments")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))));
    }

    @Test
    @Order(2)
    public void testCreateNewEduProgram() throws Exception{
        mockMvc.perform(post("/enrollments")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createEnrollmentDtoMock)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void testUpdateEduProgram() throws Exception{
        mockMvc.perform(put("/enrollments/{id}",enrollmentMock.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateEnrollmentDtoMock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(updateEnrollmentDtoMock.getStudentId())))
                .andExpect(jsonPath("$.lastName", is(updateEnrollmentDtoMock.getEducationProgramId())));
    }

    @Test
    @Order(4)
    public void testDeleteEduProgram() throws Exception{
        mockMvc.perform(delete("/enrollments/{id}",enrollmentMock.getId())
                        .header("Authorization","Bearer " + accessToken)
                )
                .andExpect(status().isNoContent());
    }
}