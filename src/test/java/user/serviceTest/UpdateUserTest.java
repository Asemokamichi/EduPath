package user.serviceTest;

import com.asemokamichi.kz.edupath.EduPathApplication;
import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFound;
import com.asemokamichi.kz.edupath.repository.UserRepository;
import com.asemokamichi.kz.edupath.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = EduPathApplication.class)
public class UpdateUserTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Успешное обновление пользователя")
    public void testUpdateUser_Success() throws Exception {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDENT");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User(userDTO)));
        when(userRepository.save(any(User.class))).thenReturn(new User(userDTO));

        User getUser = userService.updateUser(1l, userDTO);

        assertEquals(getUser.getUsername(), userDTO.getUsername());
        assertEquals(getUser.getPassword(), userDTO.getPassword());
        assertEquals(getUser.getEmail(), userDTO.getEmail());
        assertEquals(getUser.getRole().toString(), userDTO.getRole());
    }

    @Test
    @DisplayName("Пользователь не найден при обновлении")
    public void testUpdateUser_ResourceNotFound()throws Exception {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> userService.updateUser(1L, new UserDTO()));
    }
}
