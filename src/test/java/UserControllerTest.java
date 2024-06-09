import com.asemokamichi.kz.edupath.controller.UserController;
import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<UserDTO> userDTOs;

    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        userDTOs = objectMapper.readValue(new File("user_create_test.json"), new TypeReference<List<UserDTO>>() {});
    }

    @Test
    void testCreateUser_Success() throws Exception {
        for (UserDTO userDTO : userDTOs) {
            User user = new User(userDTO);  // Assuming you have a constructor in User that accepts UserDTO
            when(userService.createUser(userDTO)).thenReturn(user);

            mockMvc.perform(post("/users/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(userDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.username").value(userDTO.getUsername()))
                    .andExpect(jsonPath("$.email").value(userDTO.getEmail()))
                    .andExpect(jsonPath("$.role").value(userDTO.getRole()));
        }
    }
}

