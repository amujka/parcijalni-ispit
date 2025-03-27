package com.algebra.parcijalni_ispit.educationProgram;

import com.algebra.parcijalni_ispit.Auth.AuthController;
import com.algebra.parcijalni_ispit.Auth.dto.LoginDto;
import com.algebra.parcijalni_ispit.Auth.dto.LoginResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
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
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EduProgramControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EduProgramService eduProgramServiceMock;

    @Autowired
    private EduProgramController eduProgramControllerMock;

    @Autowired
    private EduProgramRepository eduProgramRepositoryMock;

    @Autowired
    private AuthController authController;

    private  String accessToken;

    private final CreateEduProgramDto createEduProgramDtoMock = new CreateEduProgramDto("1",3);
    private final UpdateEduProgramDto updateEduProgramDtoMOck = new UpdateEduProgramDto("2",5);
    private final EducationProgram educationProgramMock = new EducationProgram(1,"2",3);

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
        mockMvc.perform(get("/edu-programs")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))));
    }

    @Test
    @Order(2)
    public void testCreateNewEduProgram() throws Exception{
        mockMvc.perform(post("/edu-programs")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createEduProgramDtoMock)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void testUpdateEduProgram() throws Exception{
        mockMvc.perform(put("/edu-programs/{id}",educationProgramMock.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateEduProgramDtoMOck)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(updateEduProgramDtoMOck.getName())))
                .andExpect(jsonPath("$.lastName", is(updateEduProgramDtoMOck.getCSVET())));
    }

    @Test
    @Order(4)
    public void testDeleteEduProgram() throws Exception{
        mockMvc.perform(delete("/edu-programs/{id}",educationProgramMock.getId())
                        .header("Authorization","Bearer " + accessToken)
                )
                .andExpect(status().isNoContent());
    }
}