package user.serviceTest;

import com.asemokamichi.kz.edupath.EduPathApplication;
import com.asemokamichi.kz.edupath.Enum.Role;
import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.asemokamichi.kz.edupath.exceptions.UserAlreadyExists;
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
import user.ErrorMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = EduPathApplication.class)
public class CreateUserTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private ErrorMessage err;

    @Test
    @DisplayName("Успешное создание нового пользователя")
    public void testCreateUser_Success() {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDENT");

        when(userRepository.existsByUsernameOrEmail(anyString(), anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(new User(userDTO));

        User savedUser = userService.createUser(userDTO);

        verify(userRepository, times(1)).save(any(User.class));

        assertEquals(userDTO.getUsername(), savedUser.getUsername());
        assertEquals(userDTO.getEmail(), savedUser.getEmail());
        assertEquals(userDTO.getRole(), savedUser.getRole().toString());
    }

    @Test
    @DisplayName("Создание пользователя с неверными данными")
    public void testCreateUser_InvalidData() {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDEN");

        assertThrows(InvalidRequest.class, () -> userService.createUser(userDTO));
    }

    @Test
    @DisplayName("Конфликт при создании пользователя")
    public void testCreateUser_Conflict()  {
        UserDTO userDTO = new UserDTO(
                "john_doe",
                "password123",
                "john.doe@example.com",
                "STUDENT");

        when(userRepository.existsByUsernameOrEmail(anyString(), anyString())).thenReturn(true);
        assertThrows(UserAlreadyExists.class, () -> userService.createUser(userDTO));
    }
}
