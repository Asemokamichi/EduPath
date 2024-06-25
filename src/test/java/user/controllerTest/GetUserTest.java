package user.controllerTest;

import com.asemokamichi.kz.edupath.EduPathApplication;
import com.asemokamichi.kz.edupath.controller.UserController;
import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFound;
import com.asemokamichi.kz.edupath.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import user.ErrorMessage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = EduPathApplication.class)
@WebMvcTest(UserController.class)
public class GetUserTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Test
    @DisplayName("Успешное получение пользователя по ID")
    public void testGetUser_Success() throws Exception {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDENT");

        when(userService.findById(any(Long.class))).thenReturn(new User(userDTO));

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userDTO.getUsername()))
                .andExpect(jsonPath("$.password").value(userDTO.getPassword()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()))
                .andExpect(jsonPath("$.role").value(userDTO.getRole()))
                .andReturn();
    }

    @Test
    @DisplayName("Пользователь не найден по ID")
    public void testGetUser_ResourceNotFound() throws Exception {
        when(userService.findById(any(Long.class))).thenThrow(new ResourceNotFound(ErrorMessage.RESOURCE_NOT_FOUND.getMessage()));

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No user found with the provided ID."))
                .andReturn();
    }
}
