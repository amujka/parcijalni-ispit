package com.algebra.parcijalni_ispit.student;

import com.algebra.parcijalni_ispit.Auth.AuthController;
import com.algebra.parcijalni_ispit.Auth.dto.LoginDto;
import com.algebra.parcijalni_ispit.Auth.dto.LoginResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentService articleServiceMock;

    @Autowired
    private StudentController studentControllerMock;

    @Autowired
    private StudentRepository studentRepositoryMock;

    @Autowired
    private AuthController authController;

    private  String accessToken;

    private final CreateStudentDto createStudentDtoMock = new CreateStudentDto("NewFirstName","NewLastName");
    private final UpdateStudentDto updateStudentDtoMock = new UpdateStudentDto("UpdatedFirstName","UpdatedLastName");
    private final Student studentMock = new Student(1,"Ivo","Ivic");

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
        mockMvc.perform(get("/students")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(4))));
    }

    @Test
    @Order(2)
    public void testCreateNewStudent() throws Exception{
        mockMvc.perform(post("/students")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createStudentDtoMock)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void testUpdateStudent() throws Exception{
        mockMvc.perform(put("/students/{id}",studentMock.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateStudentDtoMock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(updateStudentDtoMock.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updateStudentDtoMock.getLastName())));
    }

    @Test
    @Order(4)
    public void testDeleteStudent() throws Exception{
        mockMvc.perform(delete("/students/{id}",studentMock.getId())
                        .header("Authorization","Bearer " + accessToken)
                )
                .andExpect(status().isNoContent());
    }
}