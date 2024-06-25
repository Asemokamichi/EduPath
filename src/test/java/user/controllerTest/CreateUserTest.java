package user.controllerTest;

import com.asemokamichi.kz.edupath.EduPathApplication;
import com.asemokamichi.kz.edupath.controller.UserController;
import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.asemokamichi.kz.edupath.exceptions.UserAlreadyExists;
import com.asemokamichi.kz.edupath.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import user.ErrorMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = EduPathApplication.class)
@WebMvcTest(UserController.class)
public class CreateUserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper;
    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Успешное создание нового пользователя")
    public void testCreateUser_Success() throws Exception {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDENT");

        when(userService.createUser(any(UserDTO.class))).thenReturn(new User(userDTO));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(userDTO.getUsername()))
                .andExpect(jsonPath("$.password").value(userDTO.getPassword()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()))
                .andExpect(jsonPath("$.role").value(userDTO.getRole()))
                .andReturn();
    }

    @Test
    @DisplayName("Создание пользователя с неверными данными")
    public void testCreateUser_InvalidData() throws Exception {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDEN");

        when(userService.createUser(any(UserDTO.class))).thenThrow(new InvalidRequest(ErrorMessage.INVALID_REQUEST.getMessage()));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value(new InvalidRequest(ErrorMessage.INVALID_REQUEST.getMessage()).getMessage()))
                .andReturn();
    }

    @Test
    @DisplayName("Конфликт при создании пользователя")
    public void testCreateUser_Conflict() throws Exception {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDENT");

        when(userService.createUser(any(UserDTO.class))).thenThrow(new UserAlreadyExists(userDTO.getUsername()));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(new UserAlreadyExists(userDTO.getUsername()).getMessage()))
                .andReturn();
    }
}
