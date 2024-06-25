package user.controllerTest;

import com.asemokamichi.kz.edupath.EduPathApplication;
import com.asemokamichi.kz.edupath.controller.UserController;
import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFound;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = EduPathApplication.class)
@WebMvcTest(UserController.class)
public class UpdateUserTest {
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
    @DisplayName("Успешное обновление пользователя")
    public void testUpdateUser_Success() throws Exception {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDENT");

        when(userService.updateUser(any(Long.class), any(UserDTO.class))).thenReturn(new User(userDTO));

        mockMvc.perform(put("/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userDTO.getUsername()))
                .andExpect(jsonPath("$.password").value(userDTO.getPassword()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()))
                .andExpect(jsonPath("$.role").value(userDTO.getRole()))
                .andReturn();
    }

    @Test
    @DisplayName("Пользователь не найден при обновлении")
    public void testUpdateUser_ResourceNotFound()throws Exception {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDENT");

        when(userService.updateUser(any(Long.class), any(UserDTO.class)))
                .thenThrow(new ResourceNotFound(ErrorMessage.RESOURCE_NOT_FOUND.getMessage()));

        mockMvc.perform(put("/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(new ResourceNotFound(ErrorMessage.RESOURCE_NOT_FOUND.getMessage()).getMessage()))
                .andReturn();
    }
}
