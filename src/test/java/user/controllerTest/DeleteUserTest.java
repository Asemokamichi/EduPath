package user.controllerTest;

import com.asemokamichi.kz.edupath.EduPathApplication;
import com.asemokamichi.kz.edupath.controller.UserController;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = EduPathApplication.class)
public class DeleteUserTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Пользователь успешно удален")
    public void testdeleteUser_Success()throws Exception {
        String username = "Assem";
        when(userService.deleteUser(any(Long.class))).thenReturn(username);

        mockMvc.perform(delete("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$")
                        .value(String.format("Пользователь %s удален", username)))
                .andReturn();
    }

    @Test
    @DisplayName("Пользователь не найден при удалении")
    public void testdeleteUser_ResourceNotFound()throws Exception {
        when(userService.deleteUser(any(Long.class)))
                .thenThrow(new ResourceNotFound(ErrorMessage.RESOURCE_NOT_FOUND.getMessage()));

        mockMvc.perform(delete("/users/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(new ResourceNotFound(ErrorMessage.RESOURCE_NOT_FOUND.getMessage()).getMessage()))
                .andReturn();
    }
}
